// /static/js/sidebar.js
document.addEventListener('DOMContentLoaded', function(){
  const sidebar = document.querySelector('.sidebar');
  const toggleBtn = document.querySelector('#sidebarToggle');
  const mobileToggle = document.querySelector('#sidebarToggleMobile');

  // Restaurar estado collapsed desde localStorage
  if (localStorage.getItem('sidebar-collapsed') === 'true') {
    sidebar && sidebar.classList.add('collapsed');
  }

  if (toggleBtn) {
    toggleBtn.addEventListener('click', function(){
      if (!sidebar) return;
      sidebar.classList.toggle('collapsed');
      localStorage.setItem('sidebar-collapsed', sidebar.classList.contains('collapsed'));
    });
  }

  if (mobileToggle) {
    mobileToggle.addEventListener('click', function(){
      if (!sidebar) return;
      sidebar.classList.toggle('show');
    });
  }

  // Cerrar sidebar mobile al click fuera
  document.addEventListener('click', function(e){
    if (!sidebar) return;
    const isClickInside = sidebar.contains(e.target) || (mobileToggle && mobileToggle.contains(e.target));
    if (!isClickInside && window.innerWidth <= 768 && sidebar.classList.contains('show')) {
      sidebar.classList.remove('show');
    }
  });

  // Marcar link activo comparando rutas
  const links = document.querySelectorAll('.sidebar .nav-link');
  const currentPath = window.location.pathname + window.location.search;
  links.forEach(a => {
    try {
      const url = new URL(a.href);
      const hrefPath = url.pathname + url.search;
      if (currentPath.startsWith(hrefPath) || hrefPath.startsWith(currentPath)) {
        a.classList.add('active');
      }
    } catch(e) {}
  });

  // Inicializar tooltips si bootstrap disponible
  if (typeof bootstrap !== 'undefined') {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (el) { return new bootstrap.Tooltip(el); });
  }
});

