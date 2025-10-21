package com.example.gestion.model;
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String password;
    private String area;
    private String rol; // ADMIN, TECNICO, USUARIO
    public Usuario() {}
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
