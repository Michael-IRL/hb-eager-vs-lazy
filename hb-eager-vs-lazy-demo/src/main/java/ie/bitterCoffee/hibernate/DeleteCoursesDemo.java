package ie.bitterCoffee.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ie.bitterCoffee.hibernate.entity.Course;
import ie.bitterCoffee.hibernate.entity.Instructor;
import ie.bitterCoffee.hibernate.entity.InstructorDetail;

public class DeleteCoursesDemo
{

	public static void main(String[] args)
	{
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//Create session
		Session session = factory.getCurrentSession();
		
		try
		{						
			//start a transaction
			session.beginTransaction();
			
			int theId = 10;
			
			Course course = session.get(Course.class, theId);
			
			session.delete(course);			
					
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
			factory.close();
		}			
	}

}
