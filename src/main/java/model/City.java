package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String cityName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Employee> employeeList;
    public City(String cityName) {
        this.cityName = cityName;
    }

    public City() {

    }

    public String getCityName() {
        return cityName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City: " + cityName;
    }

    public Integer getId() {
        return id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
