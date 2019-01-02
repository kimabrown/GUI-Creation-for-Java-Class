//Kima Brown
//Java OO - Project 3
//Page one - titled as such pageone.html
//Page two - welcome.jsp
//Page three - userinput.jsp
//ServerRunner.java - code
//Two car images included and a colored page on page two.

package pages;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project1.Car;
import project1.Predictor;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/ServerRunner")
public class ServerRunner extends HttpServlet {

	@SuppressWarnings("unused")
	private Predictor prediction;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServerRunner() {
		super();

		prediction = new Predictor();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("enter") != null) {
			if (request.getParameter("password").equals("test")) {
				String name = request.getParameter("username");
				String responseLabel = "servletResponse";
				String responseLabelValue = "Welcome to KimaBlueBook.com " + name;
				request.setAttribute(responseLabel, responseLabelValue);

				RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
				rd.forward(request, response); // forwarded to welcome.jsp
			} else {
				String responseLabel = "servletResponse";
				String responseLabelValue = "Your password is not correct please reenter";
				request.setAttribute(responseLabel, responseLabelValue);

				RequestDispatcher rd = request.getRequestDispatcher("/pageone.html");
				rd.forward(request, response); // forwarded to pageone.html
			}
		}

		if (request.getParameter("Submit") != null) {
            
			String Maintenance = request.getParameter("Maintenance");
			String Cost = request.getParameter("Cost");
			int Doors = Integer.parseInt(request.getParameter("Doors"));
			int Persons = Integer.parseInt(request.getParameter("Persons"));
			String Safety = request.getParameter("Safety");
			String TrunkSpace = request.getParameter("TrunkSpace");
			
			Car car0 = new Car(Maintenance, Cost, Doors, Persons, Safety, TrunkSpace);
			ArrayList<Car> list = prediction.getClosest(car0);
			
			String label1 = "carlist";
			String label1value = "<select name=\"cars\">";
			
			for (Car c : list) {
				String car = c.toString();
				label1value += "<option value= \"car\">"+car+"</option>";
			}
			
			request.setAttribute(label1, label1value);
		
			RequestDispatcher rd = request.getRequestDispatcher("/userinput.jsp");
			rd.forward(request, response);
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
