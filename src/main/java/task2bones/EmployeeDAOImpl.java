package task2bones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    final private String url;
    final private String username;
    final private String password;

    public EmployeeDAOImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sqlMessage = "insert into employee (id, first_name, last_name, gender, age, city_id) values " +
                "(?, ?, ?, ?, ?, (select city.city_id from city where city_name = ?));";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setInt(5, employee.getAge());
            preparedStatement.setString(6, employee.getCity().getCityName());
            preparedStatement.execute();
        }
    }

    @Override
    public Employee getEmployeeByID(Integer id) throws SQLException {
        String sqlMessage = "select * from employee where id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            City city = getCityByEmployeeID(resultSet.getInt(1));
            return new Employee(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    city);
        }
    }

    @Override
    public void removeEmployeeByID(Integer id) throws SQLException {
        String sqlMessage = "delete from employee where id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    @Override
    public List<Employee> getEmployeeList() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sqlMessage = "select * from employee;";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                City city = getCityByEmployeeID(resultSet.getInt(1));
                Employee employee = new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        city);
                list.add(employee);
            }
            return list;
        }
    }

    @Override
    public void changeFirstNameByID(Integer id, String firstName) throws SQLException {
        updateStringColumnByID(id, "first_name", firstName);
    }

    @Override
    public void changeLastNameByID(Integer id, String lastName) throws SQLException {
        updateStringColumnByID(id, "last_name", lastName);
    }

    @Override
    public void changeGenderByID(Integer id, String gender) throws SQLException {
        updateStringColumnByID(id, "gender", gender);
    }


    @Override
    public void changeAgeByID(Integer id, Integer age) throws SQLException {
        String sqlMessage = "update employee set age = ? where id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }
    }

    public City getCityByEmployeeID(Integer id) throws SQLException {
        String sqlMessage = "select city_name from city join employee on city.city_id = employee.city_id where employee.id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? new City(resultSet.getString(1)) : null;
        }
    }

    @Override
    public void changeCityByID(Integer id, City city) throws SQLException {
        String sqlMessage = "update employee set city_id = (select city.city_id from city where city_name = ?) where employee.id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }
    }

    private PreparedStatement getPreparedStatement(String statement) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection.prepareStatement(statement);
    }

    private void updateStringColumnByID(Integer id, String column, String newValue) throws SQLException {
        String sqlMessage = "update employee set " + column + " = ? where id = ?;";
        try (PreparedStatement preparedStatement = getPreparedStatement(sqlMessage)) {
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }

    }

}
