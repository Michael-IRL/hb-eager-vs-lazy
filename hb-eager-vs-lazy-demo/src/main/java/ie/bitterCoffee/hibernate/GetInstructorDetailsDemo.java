package ie.bitterCoffee.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ie.bitterCoffee.hibernate.entity.Instructor;
import ie.bitterCoffee.hibernate.entity.InstructorDetail;

public class GetInstructorDetailsDemo
{

	public static void main(String[] args)
	{
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//Create session
		Session session = factory.getCurrentSession();
		
		try
		{			
			//start a transaction
			session.beginTransaction();
			
			//get the insturctor detail object
			int theId = 4;
			
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			//print the insturctor detail
			System.out.println("Instructor Detail: "+instructorDetail);
			
			//print the associated instructor
			System.out.println("Associated Instructor: "+instructorDetail.getInstructor());
			
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
