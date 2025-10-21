package com.example.gestion.controller;

import com.example.gestion.dao.UsuarioDAO;
import com.example.gestion.model.Usuario;
import com.example.gestion.util.PasswordUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsuarioController", urlPatterns = {
        "/admin/usuarios",
        "/admin/usuarios-new",
        "/admin/usuarios-edit",
        "/admin/usuarios-save",
        "/admin/usuarios-delete"
})
public class UsuarioController extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();
    private static final int PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getServletPath();

        try {
            switch (path) {
                case "/admin/usuarios": {
                    String q = req.getParameter("q");
                    int page = 1;
                    try {
                        page = Integer.parseInt(req.getParameter("page"));
                    } catch (Exception ignored) {
                    }

                    int offset = (page - 1) * PAGE_SIZE;
                    List<Usuario> list = dao.list(offset, PAGE_SIZE, q);
                    int total = dao.count(q);
                    int pages = (int) Math.ceil((double) total / PAGE_SIZE);

                    req.setAttribute("usuarios", list);
                    req.setAttribute("q", q);
                    req.setAttribute("page", page);
                    req.setAttribute("pages", pages);
                    req.getRequestDispatcher("/app/admin/usuarios.jsp").forward(req, resp);
                    break;
                }

                case "/admin/usuarios-new":
                    req.setAttribute("usuario", new Usuario());
                    req.getRequestDispatcher("/app/admin/usuarios-form.jsp").forward(req, resp);
                    break;

                case "/admin/usuarios-edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    Usuario u = dao.findById(id);
                    req.setAttribute("usuario", u);
                    req.getRequestDispatcher("/app/admin/usuarios-form.jsp").forward(req, resp);
                    break;

                case "/admin/usuarios-delete":
                    int did = Integer.parseInt(req.getParameter("id"));
                    dao.delete(did);
                    resp.sendRedirect(req.getContextPath() + "/admin/usuarios");
                    break;

                default:
                    resp.sendRedirect(req.getContextPath() + "/admin/usuarios");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String sid = req.getParameter("idUsuario");
            System.out.println("DEBUG idUsuario recibido: " + sid); // 🔍 Verifica si llega vacío o null

            Usuario u = new Usuario();
            u.setNombre(req.getParameter("nombre"));
            u.setCorreo(req.getParameter("correo"));
            u.setArea(req.getParameter("area"));
            u.setRol(req.getParameter("rol"));

            String pw = req.getParameter("password");

            if (sid == null || sid.trim().isEmpty() || sid.equals("0")) {
                // Crear nuevo usuario
                if (pw == null || pw.trim().isEmpty()) {
                    throw new ServletException("La contraseña no puede estar vacía al crear un nuevo usuario.");
                }
                u.setPassword(PasswordUtil.hash(pw));
                dao.insert(u);
                System.out.println("DEBUG nuevo usuario creado correctamente.");
            } else {
                // Actualizar usuario existente
                int idParsed = Integer.parseInt(sid);
                System.out.println("DEBUG buscando usuario con id=" + idParsed);

                Usuario existing = dao.findById(idParsed);
                if (existing == null) {
                    throw new ServletException("Usuario no encontrado para actualizar (id=" + idParsed + ")");
                }

                if (pw == null || pw.trim().isEmpty()) {
                    u.setPassword(existing.getPassword()); // Mantiene contraseña anterior
                } else {
                    u.setPassword(PasswordUtil.hash(pw));
                }

                u.setIdUsuario(idParsed);
                dao.update(u);
                System.out.println("DEBUG usuario actualizado correctamente (id=" + idParsed + ")");
            }

            resp.sendRedirect(req.getContextPath() + "/admin/usuarios");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
