package main.com.bsuir.autoservice.servlet;

import main.com.bsuir.autoservice.dao.controller.UserDaoController;
import main.com.bsuir.autoservice.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "BeanServlet", urlPatterns = "/bean/user")
public class BeanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF8");
            Writer writer = response.getWriter();
            StringBuilder res = new StringBuilder();
            List<User> users = new UserDaoController().getAll();
            writer.write("<html><style>table,td{margin:auto;border: 1px solid black;width:90%; table-layout: fixed;}</style><body><table>");
            for (User user : users) {
                writer.write("<tr>");
                for (String field: user.getAllFields()) {
                    writer.write("<td>" + field + "</td>");
                }
                writer.write("</tr>");
            }
            writer.write("</table></body></html>");
            writer.flush();
            writer.close();
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }
}