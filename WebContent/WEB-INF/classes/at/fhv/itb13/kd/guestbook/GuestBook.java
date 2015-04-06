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
 * Servlet implementation class GuestBook
 */
@WebServlet("/GuestBook")
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestBook() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HTML Ausgabe
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			
			// Parameter auslesen
			String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String comment = request.getParameter("comment");
	        
	        // Werte in DB speichern
	        if(name!=null && email!=null && comment!=null){
	        	addEntry(0, name, email, comment, new Date());
	        	out.println("<p><b>Comment saved!</b></p>");
	        }
			
	        // Hibernate instanziieren
			Configuration configuration = new Configuration();
			configuration.configure();
			configuration.addAnnotatedClass(GuestBookEntry.class);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			factory = configuration.buildSessionFactory(serviceRegistry);
			
			// bisherige Einträge		
	        for(GuestBookEntry entry : getAllEntries()){
	        	out.println("<b><a href='mailto:" + entry.getEmail() + "'>" + entry.getName() + "</a></b> - <i>" + entry.getDate() + "</i><br />");
	        	out.println(entry.getComment() + "<br /><br />");
	        }
	        out.println("</body></html>");
		}
		
		private int addEntry(int id, String name, String email, String comment, Date date){
			Session session = factory.openSession();
			Transaction ta = null;
			Integer savedId = null;
			
			try{
				ta = session.beginTransaction();
				GuestBookEntry entry = new GuestBookEntry(id, name, email, comment, date);
				savedId = (Integer) session.save(entry);
				ta.commit();
			} catch (HibernateException e) {
				if(ta != null){
					ta.rollback();
				}
				e.printStackTrace();
			} finally {
				session.close();
			}
			
			return savedId;
		}
		
		@SuppressWarnings("unchecked")
		private List<GuestBookEntry> getAllEntries(){
			Session session = factory.openSession();
			Transaction ta = null;
			List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
			try{
				ta = session.beginTransaction();
				entries = (List<GuestBookEntry>) session.createQuery("FROM GuestBookEntry").list();
				ta.commit();
			} catch (HibernateException e) {
				if(ta != null){
					ta.rollback();
				}
				e.printStackTrace();
			} finally {
				session.close();
			}
			
			return entries;
			
	}

}
