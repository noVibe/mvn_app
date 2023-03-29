package task2bones;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {


    void addEmployee(Employee employee) throws SQLException;

    Employee getEmployeeByID(Integer id) throws SQLException;
    void removeEmployeeByID(Integer id) throws SQLException;

    List<Employee> getEmployeeList() throws SQLException;

    void changeFirstNameByID(Integer id, String firstName) throws SQLException;

    void changeLastNameByID(Integer id, String lastName) throws SQLException;
    void changeGenderByID(Integer id, String gender) throws SQLException;
    void changeAgeByID(Integer id, Integer age) throws SQLException;
    void changeCityByID(Integer id, City city) throws SQLException;
     City getCityByEmployeeID(Integer id) throws SQLException;


}
