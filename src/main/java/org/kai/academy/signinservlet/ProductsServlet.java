package org.kai.academy.signinservlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.kai.academy.signinservlet.model.Product;
import org.kai.academy.signinservlet.model.Users;
import org.kai.academy.signinservlet.utils.HibernateUtil;
import org.kai.academy.signinservlet.utils.MD5Util;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "ProductsServlet", value = "/products")
public class ProductsServlet extends HttpServlet {

    Session session;

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
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        try {
            criteriaQuery.select(root);
            List<Product> products = session.createQuery(criteriaQuery).getResultList();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("    <head>                                                                                                                                                                       ");
            out.println("        <meta charset=\"utf-8\" />                                                                                                                                               ");
            out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />                                                                             ");
            out.println("        <meta name=\"description\" content=\"\" />                                                                                                                               ");
            out.println("        <meta name=\"author\" content=\"\" />                                                                                                                                    ");
            out.println("        <title>Shop Homepage - Start Bootstrap Template</title>                                                                                                                  ");
            out.println("        <!-- Favicon-->                                                                                                                                                          ");
            out.println("        <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />                                                                                                  ");
            out.println("        <!-- Bootstrap icons-->                                                                                                                                                  ");
            out.println("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\" rel=\"stylesheet\" />                                                         ");
            out.println("        <!-- Core theme CSS (includes Bootstrap)-->                                                                                                                              ");
            out.println("        <link href=\"https://twitter-storage.s3.ap-northeast-2.amazonaws.com/styles.css\" rel=\"stylesheet\" />                                                                  ");
            out.println("    </head>                                                                                                                                                                      ");
            out.println("    <body>                                                                                                                                                                       ");
            out.println("        <!-- Navigation-->                                                                                                                                                       ");
            out.println("        <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">                                                                                                            ");
            out.println("            <div class=\"container px-4 px-lg-5\">                                                                                                                               ");
            out.println("                <a class=\"navbar-brand\" href=\"#!\">Start Bootstrap</a>                                                                                                        ");
            out.println("                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\"                                          ");
            out.println("				aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>            ");
            out.println("                <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">                                                                                           ");
            out.println("                    <ul class=\"navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4\">                                                                                                       ");
            out.println("                        <li class=\"nav-item\"><a class=\"nav-link active\" aria-current=\"page\" href=\"#!\">Home</a></li>                                                      ");
            out.println("                        <li class=\"nav-item\"><a class=\"nav-link\" href=\"#!\">About</a></li>                                                                                  ");
            out.println("                        <li class=\"nav-item dropdown\">                                                                                                                         ");
            out.println("                            <a class=\"nav-link dropdown-toggle\" id=\"navbarDropdown\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">Shop</a>  ");
            out.println("                            <ul class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">                                                                                      ");
            out.println("                                <li><a class=\"dropdown-item\" href=\"#!\">All Products</a></li>                                                                                 ");
            out.println("                                <li><hr class=\"dropdown-divider\" /></li>                                                                                                       ");
            out.println("                                <li><a class=\"dropdown-item\" href=\"#!\">Popular Items</a></li>                                                                                ");
            out.println("                                <li><a class=\"dropdown-item\" href=\"#!\">New Arrivals</a></li>                                                                                 ");
            out.println("                            </ul>                                                                                                                                                ");
            out.println("                        </li>                                                                                                                                                    ");
            out.println("                    </ul>                                                                                                                                                        ");
            out.println("                    <form class=\"d-flex\">                                                                                                                                      ");
            out.println("                        <button class=\"btn btn-outline-dark\" type=\"submit\">                                                                                                  ");
            out.println("                            <i class=\"bi-cart-fill me-1\"></i>                                                                                                                  ");
            out.println("                            Cart                                                                                                                                                 ");
            out.println("                            <span class=\"badge bg-dark text-white ms-1 rounded-pill\">0</span>                                                                                  ");
            out.println("                        </button>                                                                                                                                                ");
            out.println("                    </form>                                                                                                                                                      ");
            out.println("                </div>                                                                                                                                                           ");
            out.println("            </div>                                                                                                                                                               ");
            out.println("        </nav>                                                                                                                                                                   ");
            out.println("        <!-- Header-->                                                                                                                                                           ");
            out.println("        <header class=\"bg-dark py-5\">                                                                                                                                          ");
            out.println("            <div class=\"container px-4 px-lg-5 my-5\">                                                                                                                          ");
            out.println("                <div class=\"text-center text-white\">                                                                                                                           ");
            out.println("                    <h1 class=\"display-4 fw-bolder\">Shop in style</h1>                                                                                                         ");
            out.println("                    <p class=\"lead fw-normal text-white-50 mb-0\">With this shop hompeage template</p>                                                                          ");
            out.println("                </div>                                                                                                                                                           ");
            out.println("            </div>                                                                                                                                                               ");
            out.println("        </header>                                                                                                                                                                ");
            out.println("        <!-- Section-->                                                                                                                                                          ");
            out.println("        <section class=\"py-5\">                                                                                                                                                 ");
            out.println("            <div class=\"container px-4 px-lg-5 mt-5\">                                                                                                                          ");
            out.println("                <div class=\"row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center\">                                                                   ");
            for (Product pro : products) {
                out.println("                    <div class=\"col mb-5\">                                                                                                                                     ");
                out.println("                        <div class=\"card h-100\">                                                                                                                               ");
                out.println("                            <!-- Product image-->                                                                                                                                ");
                out.println("                            <img class=\"card-img-top\" src=\""+ pro.getProductImageLink() +"\" alt=\"...\" />                                                  ");
                out.println("                            <!-- Product details-->                                                                                                                              ");
                out.println("                            <div class=\"card-body p-4\">                                                                                                                        ");
                out.println("                                <div class=\"text-center\">                                                                                                                      ");
                out.println("                                    <!-- Product name-->                                                                                                                         ");
                out.println("                                    <h5 class=\"fw-bolder\">" + pro.getProductName() + "</h5>                                                                                                   ");
                out.println("                                    <!-- Product price-->                                                                                                                        ");
                out.println("                                    $" + pro.getBuyPrice() + "                                                                                                                              ");
                out.println("                                </div>                                                                                                                                           ");
                out.println("                            </div>                                                                                                                                               ");
                out.println("                            <!-- Product actions-->                                                                                                                              ");
                out.println("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">                                                                                     ");
                out.println("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\">View options</a></div>                                           ");
                out.println("                            </div>                                                                                                                                               ");
                out.println("                        </div>                                                                                                                                                   ");
                out.println("                    </div>                                                                                                                                                       ");
            }
            out.println("                </div>                                                                                                                                                           ");
            out.println("            </div>                                                                                                                                                               ");
            out.println("        </section>                                                                                                                                                               ");
            out.println("        <!-- Footer-->                                                                                                                                                           ");
            out.println("        <footer class=\"py-5 bg-dark\">                                                                                                                                          ");
            out.println("            <div class=\"container\"><p class=\"m-0 text-center text-white\">Copyright &copy; Your Website 2022</p></div>                                                        ");
            out.println("        </footer>                                                                                                                                                                ");
            out.println("        <!-- Bootstrap core JS-->                                                                                                                                                ");
            out.println("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>                                                                   ");
            out.println("        <!-- Core theme JS-->                                                                                                                                                    ");
            out.println("    </body>                                                                                                                                                                      ");
        } catch (NoResultException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
