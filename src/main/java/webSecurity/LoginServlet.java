package webSecurity;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
        //String user = request.getParameter("user");
        //String pwd = request.getParameter("pwd");

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        String user = sb.toString().substring(5, sb.toString().indexOf('&'));
        String pwd = sb.toString().substring(sb.toString().lastIndexOf('=') + 1, sb.length() - 1);

        Account account = accountDao.findByName(user);
        
        try {
            if (BCrypt.checkpw(pwd, account.getPassword())) {
                /*HttpSession session = request.getSession();
			session.setAttribute("user", "Pankaj");
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(30*60);
			response.addCookie(userName);*/
                
                if (account.getRol().equals(Account.Rol.klant)) {
                    Cookie cookieRol = new Cookie("rol", "klant");
                    cookieRol.setMaxAge(30 * 60);
                    response.addCookie(cookieRol);

                    Cookie cookieAccountId = new Cookie("id", "" + account.getId());
                    cookieAccountId.setPath("/workshopLOClient/view/klantpage.html");
                    response.addCookie(cookieAccountId);

                    Map<String, String> data = new HashMap<>();
                    data.put("redirect", "http://localhost:8080/workshopLOClient/view/klantpage.html");
                    
                    String json = new Gson().toJson(data);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);

                } else {
                    Cookie cookieRol = new Cookie("rol", "geenKlant");
                    cookieRol.setMaxAge(30 * 60);
                    response.addCookie(cookieRol);
                    
                    Map<String, String> data = new HashMap<>();
                    data.put("redirect", "http://localhost:8080/workshopLOClient/view/mainpage.html");
                    
                    String json = new Gson().toJson(data);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }

                //RequestDispatcher rs = request.getRequestDispatcher("Welcome");
                //rs.forward(request, response);
            } else {
                /*RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);*/

 /*PrintWriter out = response.getWriter();
                out.println("<font color=red>username and/or password incorrect</font>");
                RequestDispatcher rs = request.getRequestDispatcher("http://localhost:8080/workshopLOClient/view/login.html");
                rs.include(request, response);*/
            }
        } catch (NullPointerException e) {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>username and/or password incorrect</font>");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        }

    }

}
