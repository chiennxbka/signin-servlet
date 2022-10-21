package org.kai.academy.signinservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.kai.academy.signinservlet.model.Employee;
import org.kai.academy.signinservlet.utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SigninServlet", value = "/signin")
public class SigninServlet extends HttpServlet {
    Session session = null;

    @Override
    public void init() throws ServletException {
        // ket noi co so du lieu voi hibernate
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head>                                                                                                                                         ");
        out.println("    <meta charset=\"UTF-8\">                                                                                                                     ");
        out.println("    <title>Signin</title>                                                                                                                      ");
        out.println("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\">                                  ");
        out.println("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\">                                     ");
        out.println("    <style>                                                                                                                                    ");
        out.println("        body {                                                                                                                                 ");
        out.println("            background: #007bff;                                                                                                               ");
        out.println("            background: linear-gradient(to right, #0062E6, #33AEFF);                                                                           ");
        out.println("        }                                                                                                                                      ");
        out.println("                                                                                                                                               ");
        out.println("        .btn-login {                                                                                                                           ");
        out.println("            font-size: 0.9rem;                                                                                                                 ");
        out.println("            letter-spacing: 0.05rem;                                                                                                           ");
        out.println("            padding: 0.75rem 1rem;                                                                                                             ");
        out.println("        }                                                                                                                                      ");
        out.println("    </style>                                                                                                                                   ");
        out.println("</head>                                                                                                                                        ");
        out.println("<body>                                                                                                                                         ");
        out.println("    <div class=\"container\">                                                                                                                    ");
        out.println("        <div class=\"row\">                                                                                                                      ");
        out.println("            <div class=\"col-sm-9 col-md-7 col-lg-5 mx-auto\">                                                                                   ");
        out.println("                <div class=\"card border-0 shadow rounded-3 my-5\">                                                                              ");
        out.println("                    <div class=\"card-body p-4 p-sm-5\">                                                                                         ");
        out.println("                        <h5 class=\"card-title text-center mb-5 fw-light fs-5\">Sign In</h5>                                                     ");
        out.println("                        <form method=\"post\">                                                                                                   ");
        out.println("                            <div class=\"form-floating mb-3\">                                                                                   ");
        out.println("                                <input type=\"text\" class=\"form-control\" id=\"floatingInput\" name=\"username\" placeholder=\"User name\"> ");
        out.println("                                <label for=\"floatingInput\">Username</label>                                                                    ");
        out.println("                            </div>                                                                                                             ");
        out.println("                            <div class=\"form-floating mb-3\">                                                                                   ");
        out.println("                                <input type=\"password\" class=\"form-control\" id=\"floatingPassword\" name=\"password\" placeholder=\"Password\">      ");
        out.println("                                <label for=\"floatingPassword\">Password</label>                                                                 ");
        out.println("                            </div>                                                                                                             ");
        out.println("                            <div class=\"d-grid\">                                                                                               ");
        out.println("                                <button class=\"btn btn-primary btn-login text-uppercase fw-bold\" type=\"submit\">Sign in</button>                ");
        out.println("                            </div>                                                                                                             ");
        out.println("                            <hr class=\"my-4\">                                                                                                  ");
        out.println("                            <div class=\"d-grid mb-2\">                                                                                          ");
        out.println("                                <a href=\"/signin_servlet_war/signup\">Signup</a>                                                                                   ");
        out.println("                            </div>                                                                                                             ");
        out.println("                        </form>                                                                                                                ");
        out.println("                    </div>                                                                                                                     ");
        out.println("                </div>                                                                                                                         ");
        out.println("            </div>                                                                                                                             ");
        out.println("        </div>                                                                                                                                 ");
        out.println("    </div>                                                                                                                                     ");
        out.println("</body>                                                                                                                                        ");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

//        dung session de query csdl
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(builder.and(builder.equal(root.get("email"), email), builder.equal(root.get("password"), password)));
        Employee employee = session.createQuery(criteriaQuery).getSingleResult();
        if (employee != null){
            response.sendRedirect("/products");
        } else {
         response.sendError(404, "Unauthorized");
        }
    }
}
