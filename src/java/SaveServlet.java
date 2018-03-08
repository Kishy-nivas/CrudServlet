

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
                boolean ok = true;
               System.out.println(name.isEmpty());
		if(name.isEmpty()==true || password.isEmpty()== true || email.isEmpty()==true||
                        country.isEmpty()==true){
                    out.print("<p class = alert alert-warning>Please fill all the fields </p>");
                    request.getRequestDispatcher("index.html").include(request, response);
                    ok =false;
                }
                System.out.println(ok);
                if(ok==true){
                    Emp e=new Emp();
                    e.setName(name);
                    e.setPassword(password);
                    e.setEmail(email);
                    e.setCountry(country);

                    int status=EmpDao.save(e);
                    if(status>0){
                            out.print("<p>Record saved successfully!</p>");
                            request.getRequestDispatcher("index.html").include(request, response);
                    }else{
                            out.println("Sorry! unable to save record");
                    }

                    
                }
                out.close();
	}

}
