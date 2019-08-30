package Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Session
 */
@WebServlet("/Session")
public class Session extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

		Enumeration params = request.getParameterNames();
		if (params.hasMoreElements()) {
			boolean destruction = Boolean.parseBoolean(request.getParameter("destruction"));
			if (destruction == true)
				session.invalidate();
			session = request.getSession();			
		}
		if (session.isNew() || session.getAttribute("count") == null) {
			int count = 0;
			session.setAttribute("count", 0);
		} else {
			int count = (Integer) session.getAttribute("count");
			count++;
			session.setAttribute("count", count++);
		}
		out.println("[<a href=\"Session\">Reload</a>] \r\n"
				+ "	 [<a href=\"Session?destruction=true\">Destroy Session</a>]\r\n");
		out.println("<h2>Session Info:</h2>");
		out.println("New Session: " + session.isNew() + "<br/>");
		out.println("Session ID: " + session.getId() + "<br/>");
		out.println("Creation Time: " + dateFormat.format(session.getCreationTime()) + "<br/>");
		out.println("Last Accessed Time: " + dateFormat.format(session.getLastAccessedTime()) + "<br/>");
		out.println("Number of Accesses: " + session.getAttribute("count"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);


	}

}
