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
      <h3 class="mb-4">${usuario.idUsuario == 0 ? "Nuevo Usuario" : "Editar Usuario"}</h3>

      <form method="post" action="${pageContext.request.contextPath}/admin/usuarios-save" class="needs-validation" novalidate>
        <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />

        <div class="mb-3">
          <label class="form-label">Nombre</label>
          <input type="text" class="form-control" name="nombre" value="${usuario.nombre}" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Correo</label>
          <input type="email" class="form-control" name="correo" value="${usuario.correo}" required>
        </div>

        <div class="mb-3">
          <label class="form-label">Contraseña</label>
          <input type="password" class="form-control" name="password" placeholder="******** (dejar vacío para mantener)">
          <div class="form-text">Si no cambias la contraseña, deja el campo vacío.</div>
        </div>

        <div class="mb-3">
          <label class="form-label">Área</label>
          <input type="text" class="form-control" name="area" value="${usuario.area}">
        </div>

        <div class="mb-3">
          <label class="form-label">Rol</label>
          <select class="form-select" name="rol" required>
            <option value="">Seleccione un rol</option>
            <option value="ADMIN" ${usuario.rol == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
            <option value="TECNICO" ${usuario.rol == 'TECNICO' ? 'selected' : ''}>TECNICO</option>
            <option value="USUARIO" ${usuario.rol == 'USUARIO' ? 'selected' : ''}>USUARIO</option>
          </select>
        </div>

        <div class="mt-4">
          <button class="btn btn-primary me-2" type="submit">Guardar</button>
          <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/usuarios">Cancelar</a>
        </div>
      </form>
    </div>
  </div>
</div>
