package dao;

import dao.EmployeeDAO;
import model.City;
import model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    EntityManager em;

    public EmployeeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addEmployee(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
    }

    @Override
    public Employee getEmployeeByID(Integer id) {
        return em.find(Employee.class, id);
    }
    public void removeEmployeeByID(Integer id) {
        removeEmployee(getEmployeeByID(id));
    }

    @Override
    public void removeEmployee(Employee employee) {
        em.getTransaction().begin();
        try {
            em.remove(employee);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Employee> getEmployeeList() {
        String jpqlMessage = "from Employee";
        TypedQuery<Employee> tq = em.createQuery(jpqlMessage, Employee.class);
        return tq.getResultList();
    }

    @Override
    public void changeFirstName(Employee employee, String firstName) {
        em.getTransaction().begin();
        employee.setFirstName(firstName);
        em.getTransaction().commit();
    }

    @Override
    public void changeLastName(Employee employee, String newValue) {
        em.getTransaction().begin();
        employee.setLastName(newValue);
        em.getTransaction().commit();

    }

    @Override
    public void changeGender(Employee employee, String newValue) {
        em.getTransaction().begin();
        employee.setGender(newValue);
        em.getTransaction().commit();

    }

    @Override
    public void changeAge(Employee employee, Integer newValue) {
        em.getTransaction().begin();
        employee.setAge(newValue);
        em.getTransaction().commit();
    }

    @Override
    public void changeCity(Employee employee, City newValue) {
        em.getTransaction().begin();
        employee.setCity(newValue);
        em.getTransaction().commit();
    }
}
