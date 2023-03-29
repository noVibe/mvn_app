package task2bones;

public class Employee {
    final private Integer id;
    final private String first_name;
    final private String last_name;
    final private String gender;
    final private Integer age;
    final private City city;

    public Employee(Integer id, String first_name, String last_name, String gender, Integer age, City city) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ". " +first_name + " " + last_name +
                ", " + gender +
                ", " + age +
                ", from " + city;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public City getCity() {
        return city;
    }
}
