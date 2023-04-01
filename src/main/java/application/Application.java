package application;

import model.City;
import dao.CityDAOImpl;
import model.Employee;
import dao.EmployeeDAOImpl;
import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args)  {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emf1");
        EntityManager manager = emf.createEntityManager();

        EmployeeDAO employeeDAO = new EmployeeDAOImpl(manager);
        CityDAO cityDAO = new CityDAOImpl(manager);
        City city = new City("MILAN");
        cityDAO.addCity(city);
        Employee employee = new Employee("Kate", "Sour", "female", 30, city);
        employeeDAO.changeAge(employee, 30); // запись в бд не появится без предварительного добавления:
        employeeDAO.addEmployee(employee);
        employeeDAO.addEmployee(employee); // повторно не добавит
        employeeDAO.changeFirstName(employee, "Liz");
        employeeDAO.changeFirstName(employeeDAO.getEmployeeByID(9), "Rob");
        System.out.println(employeeDAO.getEmployeeList());
        employeeDAO.removeEmployeeByID(8);
        System.out.println(employee.getCity());
        employeeDAO.removeEmployeeByID(3);
        System.out.println(city);
        city = cityDAO.getCityByID(4);

        cityDAO.removeCityByID(4);
        System.out.println(city);

        manager.close();
        emf.close();
        }
    }

