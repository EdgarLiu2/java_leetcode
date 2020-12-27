package edgar.spring.learning1.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContext {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	private Map<String, Object> singletonObjectMap = new ConcurrentHashMap<>();
	private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
	

	public ApplicationContext(Class<?> configClass) {

		// 扫描类，解析类，加到beanDefinition
		scanClass(configClass);
		
		// 生成单例bean
		initializeSingletonBeans();
	}
	
	

	public Object getBean(String beanName) {
		
		if (!beanDefinitionMap.containsKey(beanName)) {
			return null;
		}
		
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		Object obj = null;
		
		switch(beanDefinition.getScope()) {
		case PROTOTYPE:
			obj = createBeanInstance(beanName, beanDefinition);
			break;
		case SINGLETON:
			if (singletonObjectMap.containsKey(beanName)) {
				obj = singletonObjectMap.get(beanName);
			} else {
				obj = createBeanInstance(beanName, beanDefinition);
				singletonObjectMap.put(beanName, obj);
			}
			break;
		default:
			obj = null;
		}
		
		return obj;
	}
	
	private Object createBeanInstance(String beanName, BeanDefinition beanDefinition) {
		Object bean = null;
		Class<?> beanClass = beanDefinition.getBeanClass();
		
		// 实例化bean
		try {
			bean = beanClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			logger.error(String.format("Fail to create instance of class: %s ", beanClass.getSimpleName()), e);
			return null;
		}
		
		// 填充属性
		Field[] fields = beanClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Autowired.class)) {
				String fieldName = field.getName();
				Object obj = getBean(fieldName);
				field.setAccessible(true);
				try {
					field.set(bean, obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					logger.error(String.format("Fail to set field %s in %s", fieldName, beanName), e);
				}
			}
		}
		
		
		// Aware
		if (bean instanceof BeanNameAware) {
			BeanNameAware beanNameAware = (BeanNameAware)bean;
			beanNameAware.setBeanName(beanName);
		}
		
		// ProcessBeforeInitialization
		for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
			beanPostProcessor.postProcessBeforeInitialization(beanClass, beanName);
		}
		
		// 初始化
		if (bean instanceof InitializingBean) {
			InitializingBean initializingBean = (InitializingBean)bean;
			initializingBean.afterPropertiesSet();
			logger.info("call afterPropertiesSet() for {}", beanName);
		}
		
		// ProcessAfterInitialization
		for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
			beanPostProcessor.postProcessAfterInitialization(beanClass, beanName);
		}
		
		logger.info("created beanInstance for {} class={} scope={}", beanName, beanClass.getName(), beanDefinition.getScope());
		
		return bean;
	}
	
	private void findAllClasses(File folder, List<String> files) {
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				String filePath = file.getAbsolutePath();
				
				if (filePath.endsWith(".class")) {
					files.add(filePath);
				}
			} else {
				findAllClasses(file, files);
			}
		}
	}
	
	private void initializeSingletonBeans() {
		
		beanDefinitionMap.forEach((beanName, beanDefinition) -> {
			if (!"".equals(beanName) && beanDefinition.getScope() == ScopeType.SINGLETON) {
				// 先预创建Singleton单例
				Object obj = getBean(beanName);
				singletonObjectMap.put(beanName, obj);
			}
		});

		logger.info("All Singleton Beans are initialized.");
	}
	
	private void scanClass(Class<?> configClass) {
	
		if (configClass.isAnnotationPresent(ComponentScan.class)) {
			ComponentScan annotation = configClass.getAnnotation(ComponentScan.class);
			
			// 扫描路径
			String scanPath =  annotation.value();
			scanPath = scanPath.replace(".", "/");
			
			// 扫描类
			ClassLoader classLoader = ApplicationContext.class.getClassLoader();
			URL resource = classLoader.getResource(scanPath);
			File rootFolder = new File(resource.getFile());
			List<String> filePathes = new ArrayList<>();
			findAllClasses(rootFolder, filePathes);
			
			
			// 指定目录下的文件列表
			for (String filePath : filePathes) {
				
				filePath = filePath.substring(filePath.indexOf("edgar"), filePath.indexOf(".class"));
				String className = filePath.replace("/", ".");
				
				try {
					Class<?> clazz = classLoader.loadClass(className);
					
					if (clazz.isAnnotationPresent(Component.class)) {
						// 找到有Component注解的类
						Component component = clazz.getAnnotation(Component.class);
						String beanName = component.value();
						
						// 判断类的Scope
						ScopeType scopeType = ScopeType.SINGLETON;
						if (clazz.isAnnotationPresent(Scope.class)) {
							Scope scope = clazz.getAnnotation(Scope.class);
							scopeType = scope.value();
						}
						
						if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
							addBeanPostProcessor(clazz);
						}
						
						// 生成beanDefinition对象
						BeanDefinition beanDefinition = new BeanDefinition(scopeType, clazz);
						this.beanDefinitionMap.put(beanName, beanDefinition);
						logger.info("add [{}] to beanDefinitionMap", clazz.getName());
					}
				} catch (ClassNotFoundException e) {
					logger.error(String.format("Fail to load class: %s ", className), e);
				}
			}
		}
		
	}



	private void addBeanPostProcessor(Class<?> clazz) {
		try {
			BeanPostProcessor postProcessor = (BeanPostProcessor)clazz.getDeclaredConstructor().newInstance();
			this.beanPostProcessorList.add(postProcessor);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			logger.error(String.format("Fail to create instance of class: %s ", clazz.getSimpleName()), e);
		}
	}
	
}
