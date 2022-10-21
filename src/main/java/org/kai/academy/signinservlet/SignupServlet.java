package org.kai.academy.signinservlet;

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.kai.academy.signinservlet.model.Employee;
import org.kai.academy.signinservlet.utils.AESUtil;
import org.kai.academy.signinservlet.utils.HibernateUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import static org.kai.academy.signinservlet.utils.AESUtil.encrypt;

@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {
    Session session = null;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head>                                                                                                                         ");
        out.println("    <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">    ");
        out.println("    <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>                                    ");
        out.println("    <script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>                                      ");
        out.println("    <style>                                                                                                                    ");
        out.println("        .register{                                                                                                             ");
        out.println("            background: -webkit-linear-gradient(left, #3931af, #00c6ff);                                                       ");
        out.println("            margin-top: 3%;                                                                                                    ");
        out.println("            padding: 3%;                                                                                                       ");
        out.println("        }                                                                                                                      ");
        out.println("        .register-left input{                                                                                                  ");
        out.println("            border: none;                                                                                                      ");
        out.println("            border-radius: 1.5rem;                                                                                             ");
        out.println("            padding: 2%;                                                                                                       ");
        out.println("            width: 60%;                                                                                                        ");
        out.println("            background: #f8f9fa;                                                                                               ");
        out.println("            font-weight: bold;                                                                                                 ");
        out.println("            color: #383d41;                                                                                                    ");
        out.println("            margin-top: 30%;                                                                                                   ");
        out.println("            margin-bottom: 3%;                                                                                                 ");
        out.println("            cursor: pointer;                                                                                                   ");
        out.println("        }                                                                                                                      ");
        out.println("        .register-right{                                                                                                       ");
        out.println("            background: #f8f9fa;                                                                                               ");
        out.println("            border-top-left-radius: 10% 50%;                                                                                   ");
        out.println("            border-bottom-left-radius: 10% 50%;                                                                                ");
        out.println("        }                                                                                                                      ");
        out.println("        .register-left img{                                                                                                    ");
        out.println("            margin-top: 15%;                                                                                                   ");
        out.println("            margin-bottom: 5%;                                                                                                 ");
        out.println("            width: 25%;                                                                                                        ");
        out.println("            -webkit-animation: mover 2s infinite  alternate;                                                                   ");
        out.println("            animation: mover 1s infinite  alternate;                                                                           ");
        out.println("        }                                                                                                                      ");
        out.println("        @-webkit-keyframes mover {                                                                                             ");
        out.println("            0% { transform: translateY(0); }                                                                                   ");
        out.println("            100% { transform: translateY(-20px); }                                                                             ");
        out.println("        }                                                                                                                      ");
        out.println("        @keyframes mover {                                                                                                     ");
        out.println("            0% { transform: translateY(0); }                                                                                   ");
        out.println("            100% { transform: translateY(-20px); }                                                                             ");
        out.println("        }                                                                                                                      ");
        out.println("        .register-left p{                                                                                                      ");
        out.println("            font-weight: lighter;                                                                                              ");
        out.println("            padding: 12%;                                                                                                      ");
        out.println("            margin-top: -9%;                                                                                                   ");
        out.println("        }                                                                                                                      ");
        out.println("        .register .register-form{                                                                                              ");
        out.println("            padding: 10%;                                                                                                      ");
        out.println("            margin-top: 10%;                                                                                                   ");
        out.println("        }                                                                                                                      ");
        out.println("        .btnRegister{                                                                                                          ");
        out.println("            float: right;                                                                                                      ");
        out.println("            margin-top: 10%;                                                                                                   ");
        out.println("            border: none;                                                                                                      ");
        out.println("            border-radius: 1.5rem;                                                                                             ");
        out.println("            padding: 2%;                                                                                                       ");
        out.println("            background: #0062cc;                                                                                               ");
        out.println("            color: #fff;                                                                                                       ");
        out.println("            font-weight: 600;                                                                                                  ");
        out.println("            width: 50%;                                                                                                        ");
        out.println("            cursor: pointer;                                                                                                   ");
        out.println("        }                                                                                                                      ");
        out.println("        .register-heading{                                                                                                     ");
        out.println("            text-align: center;                                                                                                ");
        out.println("            margin-top: 8%;                                                                                                    ");
        out.println("            margin-bottom: -15%;                                                                                               ");
        out.println("            color: #495057;                                                                                                    ");
        out.println("        }                                                                                                                      ");
        out.println("    </style>                                                                                                                   ");
        out.println("</head>                                                                                                                        ");
        out.println("<body>                                                                                                                         ");
        out.println("<div class=\"container register\">                                                                                             ");
        out.println("    <div class=\"row\">                                                                                                        ");
        out.println("        <div class=\"col-md-9 register-right\">                                                                                ");
        out.println("            <div class=\"tab-content\" id=\"myTabContent\">                                                                    ");
        out.println("                <div class=\"tab-pane fade show active\" id=\"home\" role=\"tabpanel\" aria-labelledby=\"home-tab\">           ");
        out.println("                    <h3 class=\"register-heading\">Signup as a Employee</h3>                                                   ");
        out.println("                    <form method=\"post\" class=\"row register-form\">                                                         ");
        out.println("                        <div class=\"col-md-6\">                                                                               ");
        out.println("                            <div class=\"form-group\">                                                                         ");
        out.println("                                <input type=\"text\" class=\"form-control\" name=\"firstName\" placeholder=\"First Name *\" /> ");
        out.println("                            </div>                                                                                             ");
        out.println("                            <div class=\"form-group\">                                                                         ");
        out.println("                                <input type=\"text\" class=\"form-control\" name=\"lastName\" placeholder=\"Last Name *\" />   ");
        out.println("                            </div>                                                                                             ");
        out.println("                            <div class=\"form-group\">                                                                         ");
        out.println("                                <input type=\"email\" class=\"form-control\" name=\"email\" placeholder=\"Your Email *\" />    ");
        out.println("                            </div>                                                                                             ");
        out.println("                                                                                                                               ");
        out.println("                        </div>                                                                                                 ");
        out.println("                        <div class=\"col-md-6\">                                                                               ");
        out.println("                            <div class=\"form-group\">                                                                         ");
        out.println("                                <input type=\"password\" class=\"form-control\" name=\"password\" placeholder=\"Password *\" />");
        out.println("                            </div>                                                                                             ");
        out.println("                            <div class=\"form-group\">                                                                         ");
        out.println("                                <input type=\"password\" class=\"form-control\" placeholder=\"Confirm Password *\" />          ");
        out.println("                            </div>                                                                                             ");
        out.println("                            <input type=\"submit\" class=\"btnRegister\"  value=\"Register\"/>                                 ");
        out.println("                        </div>                                                                                                 ");
        out.println("                    </form>                                                                                                    ");
        out.println("                </div>                                                                                                         ");
        out.println("            </div>                                                                                                             ");
        out.println("        </div>                                                                                                                 ");
        out.println("    </div>                                                                                                                     ");
        out.println("</div>                                                                                                                         ");
        out.println("</body>                                                                                                                        ");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        try {
            SecretKey key = AESUtil.generateKey(128);
            password = encrypt(algorithm, password, key, ivParameterSpec);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
        Employee employee = new Employee(1703, lastName, firstName, email, password);
        session.save(employee);
        if (session.isOpen())
            session.close();
        response.sendRedirect("/signin_servlet_war/signin");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
