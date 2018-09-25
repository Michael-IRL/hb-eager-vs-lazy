package ie.bitterCoffee.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ie.bitterCoffee.hibernate.entity.Instructor;
import ie.bitterCoffee.hibernate.entity.InstructorDetail;

public class CreateDemo
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
			//Create the objects
			Instructor instructor = new Instructor("John","Doe","johndoe@example.com");
			InstructorDetail instructorDetail = new InstructorDetail("youtube.com/johndoe","Writing detective stories");
			
			//Associate the objects
			instructor.setInstructorDetail(instructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save instructor
			//NOTE
			//This will also save the instructorDetail
			//because of cascade all
			System.out.println("Saving instructor"+instructor);
			session.save(instructor);			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally
		{
			factory.close();
		}		
	}

}
