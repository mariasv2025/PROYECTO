Proyecto con autenticación usando campo 'rol' (y area) en tabla usuarios, plus búsqueda y paginación para usuarios.

Ajustes importantes:
- db.properties apunta a gestion_incidencias; edítalo si tu MySQL usa otras credenciales.
- El método PasswordUtil.check acepta hashes bcrypt o texto plano (fall back) para compatibilidad.
- Paginación por defecto: 10 elementos por página (apropiado para pocos registros).

Cómo probar:
1. Ejecuta tu 'base de datos.sql' si aún no lo hiciste.
2. Asegúrate que tabla usuarios tiene columnas: idUsuario, nombre, correo, password, area, rol.
3. mvn package -> despliega WAR en Tomcat 9 /webapps.
4. Accede a /login.jsp e inicia sesión.

Si quieres que hashee todas las contraseñas existentes en la tabla usuarios, puedo generar un script que actualice las contraseñas a bcrypt de forma segura.
