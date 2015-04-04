package at.fhv.itb13.kd.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Servlet implementation class Guestbook
 */
@WebServlet("/Guestbook")
public class Guestbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory _factory;
	private static ServiceRegistry _serviceRegistry;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Guestbook() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//for HTML
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		//read Param
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String comment = request.getParameter("comment");
		
		//Values in Database
		if(name != null && email != null && comment != null)
		{
			addEntry(0, name, email, comment, new Date());
			out.println("<p>Comment in Database.</p>");
		}
		
		//Hibernate
		Configuration config = new Configuration();
		config.configure();
		config.addAnnotatedClass(GuestbookEntry.class);
		_serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		_factory = config.buildSessionFactory(_serviceRegistry);
		
		//alle Einträge der DB
		for(GuestbookEntry entry : getEntries())
		{
			out.println("<p>--------------------------------------------------</p>");
			out.println("<p><i>"+ entry.getDate()+"</i>  -  <b>"+ entry.getName()+"</b><br>" + 
			entry.getComment()+"</p><br>");
		}
		out.println("</body></html>");
	}
	
	//Eintrag machen
	private int addEntry(int id, String name, String email, String comment, Date date)
	{
		Session session = _factory.openSession();
		Transaction transaction = null;
		Integer ids = null;
		
		try
		{
			transaction = session.beginTransaction();
			GuestbookEntry gbe = new GuestbookEntry(id, name, email, comment, date);
			ids = (Integer) session.save(gbe);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			if(transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return ids;
	}
	
	@SuppressWarnings("unchecked")
	private List<GuestbookEntry> getEntries()
	{
		Session session = _factory.openSession();
		Transaction transaction = null;
		List<GuestbookEntry> gbEntries = new ArrayList<GuestbookEntry>();
		
		try
		{
			transaction = session.beginTransaction();
			gbEntries = (List<GuestbookEntry>) session.createQuery("FROM GuestbookEntry").list();
			transaction.commit();
		}
		catch(HibernateException e)
		{
			if(transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return gbEntries;
	}
}
