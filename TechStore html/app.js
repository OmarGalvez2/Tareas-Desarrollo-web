(function () {
  if (!window.angular) {
    return;
  }

  angular.module('techStoreApp', [])
    .controller('ProductosController', function ($scope, $window) {
      $scope.productos = [
        {
          nombre: 'Laptop Pro 15',
          categoria: 'Laptops',
          marca: 'TechPro',
          color: '#808080',
          precio: 999,
          pagina: 'producto.html',
          imagen: 'producto1.jpeg',
          oferta: '20% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Laptop para productividad, estudio y trabajo.'
        },
        {
          nombre: 'Smartphone X',
          categoria: 'Smartphones',
          marca: 'MobileX',
          color: '#000000',
          precio: 699,
          pagina: 'producto-smartphone.html',
          imagen: 'producto2.jpeg',
          oferta: '10% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Telefono inteligente con pantalla amplia y camara de alta calidad.'
        },
        {
          nombre: 'Audifonos Max',
          categoria: 'Accesorios',
          marca: 'SoundMax',
          color: '#ffffff',
          precio: 129,
          pagina: 'producto-audifonos.html',
          imagen: 'producto3.jpeg',
          oferta: '15% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Audifonos inalambricos para musica, clases y llamadas.'
        },
        {
          nombre: 'Teclado Mecanico',
          categoria: 'Accesorios',
          marca: 'KeyTech',
          color: '#000000',
          precio: 89,
          pagina: 'producto-teclado.html',
          imagen: 'producto4.jpg',
          oferta: '5% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Teclado mecanico para escritura, oficina y videojuegos.'
        },
        {
          nombre: 'Mouse Gamer',
          categoria: 'Gaming',
          marca: 'GameMax',
          color: '#000000',
          precio: 49,
          pagina: 'producto-mouse.html',
          imagen: 'producto5.jpeg',
          oferta: '12% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Mouse gamer con sensor optico y botones programables.'
        },
        {
          nombre: 'Monitor 24',
          categoria: 'Gaming',
          marca: 'ViewTech',
          color: '#000000',
          precio: 199,
          pagina: 'producto-monitor.html',
          imagen: 'producto6.jpeg',
          oferta: '8% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Monitor de 24 pulgadas para oficina, estudio y juegos.'
        },
        {
          nombre: 'Tablet Edu',
          categoria: 'Smartphones',
          marca: 'EduTab',
          color: '#2563eb',
          precio: 249,
          pagina: 'producto-tablet.html',
          imagen: 'producto7.jpeg',
          oferta: '18% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Tablet para clases, lectura y entretenimiento.'
        },
        {
          nombre: 'Camara Web HD',
          categoria: 'Accesorios',
          marca: 'CamTech',
          color: '#000000',
          precio: 59,
          pagina: 'producto-camara.html',
          imagen: 'producto8.jpeg',
          oferta: '7% OFF',
          disponibilidad: 'Disponible',
          descripcion: 'Camara web para clases virtuales, reuniones y videollamadas.'
        }
      ];

      $scope.categorias = ['Laptops', 'Smartphones', 'Accesorios', 'Gaming'];
      $scope.marcas = ['TechPro', 'MobileX', 'SoundMax', 'KeyTech', 'GameMax', 'ViewTech', 'EduTab', 'CamTech'];
      $scope.orden = 'nombre';
      $scope.filtro = {
        precio: null,
        marca: '',
        categoria: '',
        color: '#12233f',
        texto: ''
      };

      var params = new URLSearchParams($window.location.search);
      if (params.get('buscar')) {
        $scope.filtro.texto = params.get('buscar');
      }

      function coincideTexto(producto, texto) {
        if (!texto) return true;
        var valor = texto.toLowerCase();
        return (
          producto.nombre.toLowerCase().indexOf(valor) >= 0 ||
          producto.categoria.toLowerCase().indexOf(valor) >= 0 ||
          producto.marca.toLowerCase().indexOf(valor) >= 0 ||
          producto.descripcion.toLowerCase().indexOf(valor) >= 0
        );
      }

      $scope.productosFiltrados = function () {
        return $scope.productos.filter(function (producto) {
          var precioMaximo = $scope.filtro.precio ? Number($scope.filtro.precio) : Infinity;
          var marca = ($scope.filtro.marca || '').toLowerCase();
          var categoria = $scope.filtro.categoria || '';

          return (
            producto.precio <= precioMaximo &&
            (!marca || producto.marca.toLowerCase().indexOf(marca) >= 0) &&
            (!categoria || producto.categoria === categoria) &&
            coincideTexto(producto, $scope.filtro.texto)
          );
        });
      };

      $scope.totalFiltrado = function () {
        return $scope.productosFiltrados().length;
      };

      $scope.limpiarFiltros = function () {
        $scope.filtro.precio = null;
        $scope.filtro.marca = '';
        $scope.filtro.categoria = '';
        $scope.filtro.color = '#12233f';
        $scope.filtro.texto = '';
        $scope.orden = 'nombre';
      };

      $scope.evitarEnvio = function ($event) {
        if ($event) {
          $event.preventDefault();
        }
      };
    });
}());
