<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/app/partials/header.jsp" />

<div class="container-fluid">
  <div class="row">
    <!-- Menú lateral -->
    <div class="col-md-2 bg-light vh-100 p-3">
      <jsp:include page="/app/partials/vertical-menu.jsp" />
    </div>

    <!-- Contenido principal -->
    <div class="col-md-10 p-4">
      <div class="d-flex justify-content-between mb-3">
        <h3>Usuarios</h3>
        <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/usuarios-new">Nuevo</a>
      </div>

      <!-- Formulario de búsqueda -->
      <form class="row g-3 mb-3" method="get" action="${pageContext.request.contextPath}/admin/usuarios">
        <div class="col-auto">
          <input class="form-control" name="q" placeholder="Buscar por nombre o correo" value="${q}" />
        </div>
        <div class="col-auto">
          <button class="btn btn-outline-primary" type="submit">Buscar</button>
        </div>
      </form>

      <!-- Tabla de usuarios -->
      <table class="table table-striped table-hover align-middle">
        <thead class="table-dark">
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Área</th>
            <th>Rol</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="u" items="${usuarios}">
            <tr>
              <td>${u.idUsuario}</td>
              <td>${u.nombre}</td>
              <td>${u.correo}</td>
              <td>${u.area}</td>
              <td>${u.rol}</td>
              <td>
                <a class="btn btn-sm btn-primary"
                   href="${pageContext.request.contextPath}/admin/usuarios-edit?id=${u.idUsuario}">
                   Editar
                </a>
                <a class="btn btn-sm btn-danger"
                   href="${pageContext.request.contextPath}/admin/usuarios-delete?id=${u.idUsuario}"
                   onclick="return confirm('¿Desea eliminar este usuario?');">
                   Eliminar
                </a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <!-- Paginación -->
      <nav aria-label="Page navigation">
        <ul class="pagination">
          <c:if test="${page > 1}">
            <li class="page-item">
              <a class="page-link"
                 href="${pageContext.request.contextPath}/admin/usuarios?page=${page-1}&q=${q}">
                 Anterior
              </a>
            </li>
          </c:if>

          <c:forEach begin="1" end="${pages}" var="p">
            <li class="page-item ${p == page ? 'active' : ''}">
              <a class="page-link"
                 href="${pageContext.request.contextPath}/admin/usuarios?page=${p}&q=${q}">
                 ${p}
              </a>
            </li>
          </c:forEach>

          <c:if test="${page < pages}">
            <li class="page-item">
              <a class="page-link"
                 href="${pageContext.request.contextPath}/admin/usuarios?page=${page+1}&q=${q}">
                 Siguiente
              </a>
            </li>
          </c:if>
        </ul>
      </nav>

    </div>
  </div>
</div>
