package application;

import task2bones.City;
import task2bones.Employee;
import task2bones.EmployeeDAO;
import task2bones.EmployeeDAOImpl;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final String username = "postgres";
        final String password = "postgres";

        // =========TASK 1===========

        Integer id = 1;
        String sqlCommand = "select all (last_name, first_name, gender, city_name) " +
                "from employee " +
                "join city on city.city_id = employee.city_id " +
                "where id = ?;";

        String anotherCommand = "select * from employee where id = ?;";
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString(1));

            PreparedStatement another = connection.prepareStatement(anotherCommand);
            another.setInt(1, id);
            resultSet = another.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("first_name"));

            //ПОЧЕМУ JOIN ВСЕ КЛАДЕТ В ОДНУ СТРОКУ, А НЕ ПО КОЛОНКАМ?

            //========TASK 2==========

            EmployeeDAO dao = new EmployeeDAOImpl(url, username, password);
            System.out.println(dao.getEmployeeList());
            System.out.println(dao.getEmployeeByID(2));
            dao.changeAgeByID(2, 45);
            dao.changeFirstNameByID(2, "Todd");
            dao.changeLastNameByID(2, "Packer" );
            dao.changeGenderByID(2, "male");
            City c = dao.getCityByEmployeeID(1);
            Employee e = new Employee(20, "Jim", "Carr", "male", 35, c);
            dao.addEmployee(e);
            dao.changeCityByID(2, c);
            System.out.println(dao.getEmployeeByID(2));
            System.out.println(dao.getEmployeeByID(20));

        }
    }

}
