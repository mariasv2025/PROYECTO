package com.example.gestion.controller;
import com.example.gestion.dao.UsuarioDAO; import com.example.gestion.model.Usuario; import com.example.gestion.util.PasswordUtil;
import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import javax.servlet.ServletException; import java.io.IOException;
@WebServlet(name="AuthController", urlPatterns={"/login"})
public class AuthController extends javax.servlet.http.HttpServlet {
    private UsuarioDAO dao = new UsuarioDAO();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo"); String password = req.getParameter("password");
        try {
            Usuario u = dao.findByCorreo(correo);
            if (u != null && PasswordUtil.check(password, u.getPassword())) {
                HttpSession s = req.getSession(); s.setAttribute("authUser", u.getCorreo()); s.setAttribute("authRole", u.getRol()); s.setAttribute("authName", u.getNombre());
                resp.sendRedirect(req.getContextPath()+"/app/home.jsp"); return;
            } else { req.setAttribute("error","Usuario o contraseña incorrectos"); }
        } catch (Exception e) { req.setAttribute("error","Error: "+e.getMessage()); }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
