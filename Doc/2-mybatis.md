# Mybatis框架

Mybatis框架主要用于解决对数据库的JDBC的封装，简化操作数据库的代码。  

在Maven配置中配置Mybatis的jar包：  

```
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.0</version>
    </dependency>
```

Mabatis通过反射提供了动态代理的方式管理sql语句，只需要配置和xml文件和mapper接口，就可以将sql和数据库操作交给Mybatis管理。  

用一个外部配置文件配置数据库连接的相关信息 jdbc.properties  
```
jdbc.driver=com.mysql.cj.jdbc.Driver    (mysql 8.0 加cj，5.0不用)
jdbc.url=jdbc:mysql://localhost:3306/mybatis
jdbc.username=数据库用户名
jdbc.password=数据库密码
```

Mybatis的配置文件mybatis-config.xml:  

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">


<configuration>

    <!--通过properties标签加载外部properties文件-->
    <properties resource="jdbc.properties"></properties>

    <!--自定义别名-->
    <typeAliases>
        <typeAlias type="Test.HelloMybatis.User" alias="user"></typeAlias>
    </typeAliases>

    <!-- 环境配置 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!-- 数据库连接相关配置 ,这里动态获取config.properties文件中的内容-->
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}?characterEncoding=UTF-8"/>  <!-- 这里设置了使用数据库时的编码用UTF-8 -->
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>
    </mappers>

</configuration>

```
其中的 Mappers 标签配置了sql语句，在编写sql之前，首先编写一个model用于提供数据的交互，再编写一个接口，接口用于给Sql调用
```
public interface UserMapper {

    public List<User> findByCondition(User user);

    public List<User> findByIds(List<Integer> ids);
}

```

在UserMapper.xml配置文件中，配置所使用的接口，和查询的sql语句，实现动态代理：  
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- mapper为映射的根节点，namespace指定Dao接口的完整类名
    mybatis会依据这个接口动态创建一个实现类去实现这个接口，
    而这个实现类是一个Mapper对象-->
<mapper namespace="Test.HelloMybatis.UserMapper">

    <sql id="selectUser">select * from t_user</sql>


    <!--id ="接口中的方法名"  　　parameterType="传入的参数类型"　　resultType = "返回实体类对象，使用包.类名"-->
    <select id="findByCondition" parameterType="user" resultType="user">
        <include refid="selectUser"></include>
        <where>
            <if test="id!=0">
                and id=#{id}
            </if>
            <if test="name!=null">
                and name=#{name}
            </if>
            <if test="age!=null">
                and age=#{age}
            </if>
        </where>
    </select>

    <select id="findByIds" parameterType="list" resultType="user">
        <include refid="selectUser"></include>
        <where>
            <foreach collection="list" open="id in(" close=")" item="id" separator=",">
                #{id}
            </foreach>
        </where>
    </select>

</mapper>

```
注意里面使用的名称需要和model里的名称对应，否则找不到数据。这里提供了两个sql，第一个是根据属性查询user对象，其中的属性可适当缺省，第二个是根据提供的一个id列表查询user对象。

在测试中使用一下：
```
    public void DynamicProxy() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //模拟条件user
        User condition = new User();
        condition.setName("张三");
//        condition.setId(1);
//        condition.setAge(20);

        //List<User> userList = mapper.findByCondition(condition);

        //模拟ids的数据
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);

        List<User> userList = mapper.findByIds(ids);
        System.out.println(userList);
    }
```

效果是杆进又胃绳，肥肠滴淫性化啊
