<%@ page session="true" %>
<%
  String name = (String) session.getAttribute("authName");
  String role = (String) session.getAttribute("authRole");
%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/styles.css" rel="stylesheet">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <div class="d-flex align-items-center">
      <!-- Desktop toggler -->
      <button id="sidebarToggle" class="btn btn-sm btn-outline-light me-2" title="Colapsar menú">
        <i class="bi bi-list"></i>
      </button>
      <!-- Mobile toggler -->
      <button id="sidebarToggleMobile" class="btn btn-sm btn-outline-light d-md-none me-2" title="Abrir menú">
        <i class="bi bi-list"></i>
      </button>

      <a class="navbar-brand" href="${pageContext.request.contextPath}/app/home.jsp">
        <span class="d-inline-block align-middle"><i class="bi bi-gear-fill"></i></span>
        <span class="ms-2 d-none d-sm-inline">Gestión</span>
      </a>
    </div>

    <div class="d-flex ms-auto">
      <span class="navbar-text text-white me-3"><%= name!=null?name:"Invitado" %> (<%= role!=null?role:"-" %>)</span>
      <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Salir</a>
    </div>
  </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/sidebar.js"></script>
