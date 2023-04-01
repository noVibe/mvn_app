package dao;

import model.City;
import model.Employee;

import java.util.List;

public interface EmployeeDAO {


    void addEmployee(Employee employee);

    Employee getEmployeeByID(Integer id);
    void removeEmployee(Employee employee);

    List<Employee> getEmployeeList();

    void changeFirstName(Employee employee, String newValue);
    void changeLastName(Employee employee, String newValue);
    void changeGender(Employee employee, String newValue);
    void changeAge(Employee employee, Integer newValue);
    void changeCity(Employee employee, City newValue);
    void removeEmployeeByID(Integer id);

}
