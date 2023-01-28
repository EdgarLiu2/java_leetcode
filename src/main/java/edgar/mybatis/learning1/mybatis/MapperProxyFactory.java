package edgar.mybatis.learning1.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Edgar.Liu on 2023/1/25
 */
@Slf4j
public class MapperProxyFactory {

    private final static Map<Class<?>, TypeHandler> typeHandlerMap = Map.of(
            String.class, new StringTypeHandler(),
            Integer.class, new IntegerTypeHandler(),
            Long.class, new LongTypeHandler()
    );

    static {
//        register(String.class, new StringTypeHandler());
//        register(Integer.class, new IntegerTypeHandler());
//        register(Long.class, new LongTypeHandler());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Can't find MySQL Driver", e);
            throw new RuntimeException(e);
        }
    }

//    private static <T> void register(Class<T> javaType, TypeHandler<? extends T> typeHandler) {
//        typeHandlerMap.put(javaType, typeHandler);
//    }

//    @SuppressWarnings("unchecked")
    public static <T> T getMapper(Class<T> mapper) {
        Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{mapper}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 解析SQL - 执行SQL - 结果封装

                // 1. 创建数据库连接
                Connection connection = getConnection();

                // 2. 通过@Select注解，获取SQL模板
                String rawSQL = getSQLFromSelect(method);

                // 3. 处理方法参数名和值的映射
                Map<String, Object> paramNameValueMapping = buildParamNameValueMapping(method, args);

                // 4. 解析SQL中的参数占位符
                ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
                String parsedSQL = parseRawSQL(rawSQL, tokenHandler);

                // 5. 构造PreparedStatement
                // SQL中第i个?对应的参数名称
                List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();
                PreparedStatement preparedStatement = connection.prepareStatement(parsedSQL);
                for (int i = 0; i < parameterMappings.size(); i++) {
                    // 第i个#{}变量的名称
                    String paramName = parameterMappings.get(i).getProperty();
                    // 第i个#{}变量的值
                    Object paramValue = paramNameValueMapping.get(paramName);
                    // 第i个#{}变量的值类型
                    Class<?> valueType = paramValue.getClass();
                    // 基于第i个#{}变量的类型，获取对应的typeHandler，然后向preparedStatement赋值
                    typeHandlerMap.get(valueType).setParameter(preparedStatement, i + 1, paramValue);
                }

                // 6. 执行PreparedStatement，获取结果集
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                // 存放返回值
                List<Object> result = new ArrayList<>();

                // 7. 获取当前方法的返回值类型
                Class<?> resultType = getResultTypeFromMethod(method);

                // 8. 结果集列信息的元数据
                List<String> columnNames = getColumnNameFromResultSet(resultSet);

                // 9. 获取 resultType 中的所有set方法，与属性名称构建成Map
                Map<String, Method> propertySetterMapping = buildPropertySetterMapping(resultType);

                // 10. 遍历查询结果
                while (resultSet.next()) {
                    // 利用反射，创建对象
                    Object obj = resultType.getDeclaredConstructor().newInstance();

                    for (String columnName : columnNames) {
                        if (!propertySetterMapping.containsKey(columnName)) {
                            log.debug("{} doesn't contain property {}, ignored", resultType.getName(), columnName);
                            continue;
                        }

                        Method setterMethod = propertySetterMapping.get(columnName);
                        Class<?>[] setterParameterTypes = setterMethod.getParameterTypes();
                        TypeHandler typeHandler = typeHandlerMap.get(setterParameterTypes[0]);
                        Object val = typeHandler.getColumnValue(resultSet, columnName);
                        setterMethod.invoke(obj, val);
                    }

                    result.add(obj);
                }

                if (connection != null) {
                    connection.close();
                }

                // 11. 结果返回
                if (method.getReturnType().equals(List.class)) {
                    // 返回值为数组类型
                    return result;
                } else {
                    // 返回值为单对象类型
                    return result.get(0);
                }

            }
        });

        return (T) proxyInstance;
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "123456");
        } catch (SQLException e) {
            log.error("Can't create MySQL Connection", e);
        }

        return connection;
    }

    private static String getSQLFromSelect(@NotNull Method method) {
        Select selectAnnotation = method.getAnnotation(Select.class);
        String rawSQL = selectAnnotation.value();
        log.info("Get raw sql from @Select on {}.{}(): sql={}", method.getDeclaringClass().getName(), method.getName(), rawSQL);
        return rawSQL;
    }

    @NotNull
    private static Map<String, Object> buildParamNameValueMapping(@NotNull Method method, Object[] args) {
        Map<String, Object> paramValueMap = new HashMap<>();
        Parameter[] parameters = method.getParameters();

        // 依次遍历mapper方法的每个参数
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            // 参数名称
            String parameterName = parameter.getName();
            // 参数上@Param注解里面的名称
            String paramAnnotationName = parameter.getAnnotation(Param.class).value();
            // 参数对应的值
            Object parameterValue = args[i];

            paramValueMap.put(parameterName, parameterValue);
            paramValueMap.put(paramAnnotationName, parameterValue);
            paramValueMap.put("param" + (i + 1), parameterValue);
        }

        log.info("Parsed parameters' name and value of {}.{}(): params={}", method.getDeclaringClass().getName(), method.getName(), paramValueMap);
        return paramValueMap;
    }

    private static String parseRawSQL(@NotNull String sql, @NotNull ParameterMappingTokenHandler tokenHandler) {
        GenericTokenParser parser = new GenericTokenParser(tokenHandler);
        String parsedSQL = parser.parse(sql);
        log.info("Parsed SQL={}", parsedSQL);
        return parsedSQL;
    }

    @Nullable
    private static Class<?> getResultTypeFromMethod(Method method) {
        Class<?> resultType = null;
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType instanceof Class) {
            // 返回值不是泛型
            resultType = (Class<?>) genericReturnType;
        } else if (genericReturnType instanceof ParameterizedType) {
            // 是泛型
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            resultType = (Class<?>)actualTypeArguments[0];
        }

        log.info("Analyzed resultType={}", resultType);
        return resultType;
    }

    private static List<String> getColumnNameFromResultSet(ResultSet resultSet) throws SQLException {
        List<String> result = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();

        for (int i = 0; i < metaData.getColumnCount(); i++) {
            result.add(metaData.getColumnName(i + 1));
        }

        log.info("Analyzed columns returned from SQL. columnNames={}", result);
        return result;
    }

    @NotNull
    private static Map<String, Method> buildPropertySetterMapping(Class<?> resultType) {
        Map<String, Method> propertySetterMapping = new HashMap<>();

        if (resultType != null) {
            for (Method declaredMethod : resultType.getDeclaredMethods()) {
                if (declaredMethod.getName().startsWith("set")) {
                    String propertyName = declaredMethod.getName().substring(3);
                    propertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
                    propertySetterMapping.put(propertyName, declaredMethod);
                }
            }
        }

        log.info("Analyzed all properties' setter methods and built a setterMap. setterMap={}", propertySetterMapping);
        return propertySetterMapping;
    }
}
