package com.modelshop.servlet.User;

import com.modelshop.dao.DAO;
import com.modelshop.dao.UserDAO;
import com.modelshop.dao.implementsDAO.UserDAOimplements;
import com.modelshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/listusers")
public class ListUserServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    DAO dao = (DAO) session.getAttribute("com/modelshop/dao");
    UserDAO userDAO = new UserDAOimplements(dao);
    List<User> users = userDAO.getUsers();
    session.setAttribute("users", users);
    session.setAttribute("userDAO", userDAO);
    req.getRequestDispatcher("ListUsers.jsp").forward(req, resp);
  }
}
