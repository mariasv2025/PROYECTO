<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"/><title>Login</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<div class="container"><div class="row justify-content-center" style="margin-top:80px"><div class="col-md-5"><div class="card"><div class="card-body"><h4>Iniciar sesión</h4><form method="post" action="login"><div class="mb-3"><label>Correo</label><input class="form-control" name="correo" required/></div><div class="mb-3"><label>Contraseña</label><input type="password" class="form-control" name="password" required/></div><c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if><button class="btn btn-primary">Ingresar</button></form></div></div></div></div></div>
</body>
</html>
