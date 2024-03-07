package crudExample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MapDemo {

	private static SessionFactory factory;

	public static void main(String[] args) {

		// Creating sessionFactory.
		Configuration cnfg = new Configuration();
		cnfg.configure("Configuration.xml");
		factory = cnfg.buildSessionFactory();

		// CRUD Operation.
		Createbook();
		// Read();
		// update();
		delete();

		if (factory != null) {
			factory.close();
		}
	}

	public static void Createbook() {

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		// Adding data
		Bean bean = new Bean();
		bean.setTitle("Macbeth");
		bean.setAuthor("William Shakespeare");
		bean.setYear(2001);

		Bean bean2 = new Bean();
		bean2.setTitle("Mein Kamph	");
		bean2.setAuthor("Adolf Hitler");
		bean2.setYear(2004);

		Bean bean3 = new Bean();
		bean3.setTitle("Rich Dad Poor Dad");
		bean3.setAuthor("Robert T. Kiyosaki");
		bean3.setYear(1997);

		session.save(bean);
		session.save(bean2);
		session.save(bean3);
		tx.commit();

	}

	public static void Read() {
		Session session = factory.openSession();
		Bean book = session.get(Bean.class, 2L);
		if (book != null) {
			System.out.println("Title:" + book.getTitle());
			System.out.println("Author:" + book.getAuthor());
			System.out.println("Year:" + book.getYear());
		} else {
			System.out.println("Book not found.");
		}

	}

	public static void update() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Bean book = session.get(Bean.class, 1L);
		if (book != null) {
			book.setTitle("The Great Gatsby");
			book.setAuthor("F. Scott Fitzgerald");
			book.setYear(2010);
			session.update(book);
			tx.commit();
		} else {
			System.out.println("Book not found.");
		}
	}

	public static void delete() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Bean book = session.get(Bean.class, 1L);
		if (book != null) {
			session.delete(book);
			tx.commit();
		} else {
			System.out.println("Book not found.");
		}
	}

}
