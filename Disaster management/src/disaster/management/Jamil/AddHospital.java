package disaster.management.Jamil;



import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/AddHospital")
public class AddHospital extends HttpServlet {
	private static final long serialVersionid = 1L;
   
    public AddHospital() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
	
		//getting input values from jsp page
		int Id = Integer.parseInt(request.getParameter("Id"));
		String Name = request.getParameter("Name");
		int Available_Beds = Integer.parseInt(request.getParameter("Available_Beds"));
		int Total_Beds = Integer.parseInt(request.getParameter("Total_Beds"));
		String Location = request.getParameter("Location");
		String Contact = request.getParameter("Contact");


		Connection con = null;
 		String url = "jdbc:mysql://localhost:3306/Resources"; //MySQL URL and followed by the database name
 		String username = "Disaster"; //MySQL username
 		String password = "Pass@123"; //MySQL password
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
 		System.out.println("Printing connection object "+con);

		//Prepared Statement to add student data
		PreparedStatement st = con.prepareStatement("insert into hospital values(?,?,?,?,?,?)");
 		st.setInt(1,Id);
		st.setString(2,Name);
		st.setInt(3,Available_Beds);
		st.setInt(4,Total_Beds);
		st.setString(5,Location);
		st.setString(6,Contact);
		int result=st.executeUpdate();

		//Checks if insert is successful.If yes,then redirects to Result.jsp page 
		if(result>0)
		{
			
			RequestDispatcher rd = request.getRequestDispatcher("AddResult.jsp");
			rd.forward(request, response);
		}

		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}


}
