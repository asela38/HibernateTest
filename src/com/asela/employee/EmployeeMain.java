package com.asela.employee;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class EmployeeMain {
	
	public static Logger log = Logger.getLogger(EmployeeMain.class.getName());

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		createEmployee(sessionFactory, "Asela", "Illayapparachchi", 'M', 28);
		createEmployee(sessionFactory, "Asanka", "Illayapparachchi", 'M', 23);

		//listAllPeople(sessionFactory);
		log.info("Succefully Saved");
		
	}

	@SuppressWarnings("unused")
	private static void createEmployee(SessionFactory sessionFactory, String firstName, String lastName, char gender, int age) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setGender(gender);
		employee.setAge(age);
		
		session.persist(employee);		
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unused")
	private static void listAllPeople(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		
		Query query = session.createQuery("From Employee");
		List<Employee> list = query.list();
		for (Employee  employee  : list) {
			System.out.println(employee);
		}
		
		transaction.commit();
		session.close();
	}
	
	
}
