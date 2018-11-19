package webSecurity;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import sessions.AccountFacade;
import workshop3lo.domain.Account;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
        @EJB
        private AccountFacade accountDao;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
                Account account = accountDao.findByName(user);
		try {
                    if (BCrypt.checkpw(pwd, account.getPassword())){
			/*HttpSession session = request.getSession();
			session.setAttribute("user", "Pankaj");
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(30*60);
			response.addCookie(userName);*/
                        if (account.getRol().equals(Account.Rol.klant)){
                            Cookie cookieRol = new Cookie ("rol","klant");
                            cookieRol.setMaxAge(30*60);
                            response.addCookie(cookieRol);
                            
                            Cookie cookieAccountId = new Cookie ("id", ""+account.getId());
                            response.sendRedirect("http://localhost:8080/workshopLOClient/view/klantpage.html");
                        }
                        else {
                            Cookie cookieRol = new Cookie ("rol","geenKlant");
                            cookieRol.setMaxAge(30*60);
                            response.addCookie(cookieRol);
                            response.sendRedirect("http://localhost:8080/workshopLOClient/view/mainpage.html");
                        }
                        
                        
                        //RequestDispatcher rs = request.getRequestDispatcher("Welcome");
                        //rs.forward(request, response);
		}else{
			/*RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);*/
                        
                        PrintWriter out = response.getWriter();
                        out.println("<font color=red>username and/or password incorrect</font>");
                        RequestDispatcher rs = request.getRequestDispatcher("index.html");
                        rs.include(request, response);
                        
		}
                }
                catch (NullPointerException e){
                    PrintWriter out = response.getWriter();
                        out.println("<font color=red>username and/or password incorrect</font>");
                        RequestDispatcher rs = request.getRequestDispatcher("index.html");
                        rs.include(request, response);
                }

	}

}

