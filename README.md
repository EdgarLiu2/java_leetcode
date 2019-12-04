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

mvn archetype:generate -DgroupId=edgar.test -DartifactId=leetcode -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

# https://leetcode-cn.com/problemset/all/

