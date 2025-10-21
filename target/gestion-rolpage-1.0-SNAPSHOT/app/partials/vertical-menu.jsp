<%@ page session="true" %>
<ul class="sidebar list-unstyled">
  <li class="brand">
    <span class="text"><strong>Admin</strong></span>
  </li>

  <li>
    <a class="nav-link" href="${pageContext.request.contextPath}/app/home.jsp">
      <span class="icon"><i class="bi bi-house-door-fill"></i></span>
      <span class="text">Inicio</span>
    </a>
  </li>

  <li>
    <a class="nav-link" href="${pageContext.request.contextPath}/admin/usuarios" data-bs-toggle="tooltip" data-bs-placement="right" title="Gestión de usuarios">
      <span class="icon"><i class="bi bi-people-fill"></i></span>
      <span class="text">Usuarios</span>
    </a>
  </li>

  <li>
    <a class="nav-link" href="${pageContext.request.contextPath}/admin/equipo" data-bs-toggle="tooltip" data-bs-placement="right" title="Equipos">
      <span class="icon"><i class="bi bi-hdd-network-fill"></i></span>
      <span class="text">Equipos</span>
    </a>
  </li>

  <li class="mt-3 px-2">
    <small class="text-white-50">Cuenta</small>
    <div class="d-flex align-items-center mt-2">
      <span class="badge-role me-2"><i class="bi bi-person-fill"></i></span>
      <div class="text-truncate">
        <div class="fw-bold">${pageContext.request.session.getAttribute("authName")}</div>
        <div class="small">${pageContext.request.session.getAttribute("authRole")}</div>
      </div>
    </div>
  </li>
</ul>
