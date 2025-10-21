package com.example.gestion.util;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;
public class AuthFilter implements Filter {
    public void init(FilterConfig fc) {}
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request; HttpServletResponse resp=(HttpServletResponse)response;
        String path=req.getRequestURI().substring(req.getContextPath().length());
        if (path.startsWith("/static/") || path.startsWith("/login") || path.equals("/login.jsp") || path.equals("/")) { chain.doFilter(request,response); return; }
        HttpSession s = req.getSession(false); if (s==null || s.getAttribute("authUser")==null) { resp.sendRedirect(req.getContextPath()+"/login.jsp"); return; }
        String role = (String) s.getAttribute("authRole");
        if (path.startsWith("/admin/") && !"ADMIN".equals(role)) { resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado"); return; }
        if (path.startsWith("/tec/") && !("ADMIN".equals(role) || "TECNICO".equals(role))) { resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado"); return; }
        chain.doFilter(request,response);
    }
    public void destroy() {}
}
