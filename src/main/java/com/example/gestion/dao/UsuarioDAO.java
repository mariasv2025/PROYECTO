package com.example.gestion.dao;
import com.example.gestion.model.Usuario; import com.example.gestion.util.DbUtil;
import java.sql.*; import java.util.*;
public class UsuarioDAO {
    public Usuario findByCorreo(String correo) throws SQLException {
        String sql = "SELECT idUsuario,nombre,correo,password,area,rol FROM usuarios WHERE correo=?";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setPassword(rs.getString("password"));
                    u.setArea(rs.getString("area"));
                    u.setRol(rs.getString("rol"));
                    return u;
                }
            }
        }
        return null;
    }

    public List<Usuario> list(int offset, int limit, String q) throws SQLException {
        List<Usuario> list = new ArrayList<>();
        String base = "SELECT idUsuario,nombre,correo,area,rol FROM usuarios";
        String where = "";
        if (q != null && !q.trim().isEmpty()) {
            where = " WHERE nombre LIKE ? OR correo LIKE ?";
        }
        String sql = base + where + " ORDER BY idUsuario DESC LIMIT ? OFFSET ?";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            int idx = 1;
            if (where.length()>0) { ps.setString(idx++, "%" + q + "%"); ps.setString(idx++, "%" + q + "%"); }
            ps.setInt(idx++, limit); ps.setInt(idx++, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setArea(rs.getString("area"));
                    u.setRol(rs.getString("rol"));
                    list.add(u);
                }
            }
        }
        return list;
    }

    public int count(String q) throws SQLException {
        String base = "SELECT COUNT(*) FROM usuarios";
        String where = "";
        if (q != null && !q.trim().isEmpty()) where = " WHERE nombre LIKE ? OR correo LIKE ?";
        String sql = base + where;
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            if (where.length()>0) { ps.setString(1, "%" + q + "%"); ps.setString(2, "%" + q + "%"); }
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getInt(1); }
        }
        return 0;
    }

public Usuario findById(int id) throws SQLException {
    String sql = "SELECT idUsuario, nombre, correo, password, area, rol FROM usuarios WHERE idUsuario=?";
    try (Connection c = DbUtil.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setPassword(rs.getString("password"));  // ✅ aquí está la diferencia
                u.setArea(rs.getString("area"));
                u.setRol(rs.getString("rol"));
                return u;
            }
        }
    }
    return null;
}


    public void insert(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, correo, password, area, rol) VALUES (?,?,?,?,?)";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getNombre()); ps.setString(2, u.getCorreo()); ps.setString(3, u.getPassword()); ps.setString(4, u.getArea()); ps.setString(5, u.getRol());
            ps.executeUpdate();
        }
    }

    public void update(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, correo=?, password=?, area=?, rol=? WHERE idUsuario=?";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getNombre()); ps.setString(2, u.getCorreo()); ps.setString(3, u.getPassword()); ps.setString(4, u.getArea()); ps.setString(5, u.getRol());
            ps.setInt(6, u.getIdUsuario());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE idUsuario=?";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        }
    }
}
