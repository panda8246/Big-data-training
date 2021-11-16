package Test.HelloMybatis;

public class User {

    private Integer Id;
    private String name;
    private Integer age;

    public User(Integer id, String name, Integer age) {
        Id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
        Id = 0;
        name = null;
        age = null;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
