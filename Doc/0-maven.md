# Maven项目配置

Maven项目中pom.xml用于配置Maven依赖  

Maven本地存在一个仓库，同时可以设置一个远程的CDN仓库，具体在：  

Maven的源目录/conf/setting.xml中配置

配置本地Maven仓库:

```
	<localRepository>E:\Maven\maven_repository</localRepository>
```

配置远程CDN仓库：

```
	 <mirror>
		<id>alimaven</id>
		<name>aliyun maven</name>
		<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
		<mirrorOf>central</mirrorOf>
	</mirror>
```

pom.xml中配置项目的jar包依赖，jar包的配置信息可以在Maven中心仓库查询到：
[https://mvnrepository.com/](https://mvnrepository.com/)  


配置参考： 
```
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.27</version>
    </dependency>

    <!--gson工具包-->
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.8.0</version>
    </dependency>

</dependencies>
```
