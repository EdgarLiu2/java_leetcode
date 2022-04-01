# Create Maven Project
```xml
	<localRepository>D:/workspace/projects/.m2/repository</localRepository>
	
	<!-- 阿里云仓库 -->
	<mirror>
		<id>alimaven</id>
		<mirrorOf>central</mirrorOf>
		<name>aliyun maven</name>
		<url>https://maven.aliyun.com/repository/central</url>
	</mirror>
```

```bash
# 创建maven项目
mvn archetype:generate -DgroupId=edgar.test -DartifactId=leetcode -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```


# https://leetcode-cn.com/problemset/all/

# Mybatis

- [【尚硅谷】2022版MyBatis教程](https://www.bilibili.com/video/BV1VP4y1c7j7?spm_id_from=333.999.0.0)
- [【尚硅谷】2022版MyBatisPlus教程](https://www.bilibili.com/video/BV12R4y157Be/?spm_id_from=autoNext)

```bash
# 逆向工程
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

