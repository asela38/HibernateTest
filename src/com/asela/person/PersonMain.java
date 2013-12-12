package com.asela.person;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class PersonMain {
	
	public static Logger log = Logger.getLogger(PersonMain.class.getName());

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//createPerson(sessionFactory, "Suthmina", "Perera", 'M', 18);
		//createPerson(sessionFactory, "Asanka", "Illayapparachchi", 'M', 23);

		listAllPeople(sessionFactory);
		log.info("Succefully Saved");
		
	}

	@SuppressWarnings("unused")
	private static void createPerson(SessionFactory sessionFactory, String firstName, String lastName, char gender, int age) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setGender(gender);
		person.setAge(age);
		
		session.persist(person);		
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unused")
	private static void listAllPeople(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		
		Query query = session.createQuery("From Person");
		List<Person> list = query.list();
		for (Person  person  : list) {
			System.out.println(person);
		}
		
		transaction.commit();
		session.close();
	}
	
	
}
