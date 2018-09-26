package ie.bitterCoffee.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ie.bitterCoffee.hibernate.entity.Course;
import ie.bitterCoffee.hibernate.entity.Instructor;
import ie.bitterCoffee.hibernate.entity.InstructorDetail;

public class EagerLazyDemo
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
			
			int theId = 1;
			
			Instructor instructor = session.get(Instructor.class,theId);
			
			System.out.println("The Instructor: "+instructor);			
								
			//commit transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			//This call happens after the session is close because we are using lazy loading
			//this will throw an exception unless there is work done before the session is closed
			//See OPTIONS above
			System.out.println("Courses: "+instructor.getCourses());
			
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
