package ie.bitterCoffee.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ie.bitterCoffee.hibernate.entity.Instructor;
import ie.bitterCoffee.hibernate.entity.InstructorDetail;

public class DeleteDemo
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
			
			int id = 1;
			
			Instructor instructor = session.get(Instructor.class, id);
			
			if(instructor != null)
			{
				System.out.println("Deleting Instructor : "+instructor);
				session.delete(instructor);
			}
			
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
