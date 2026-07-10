(function () {
  var productos = {
    'Laptop Pro 15': { precio: 999, pagina: 'producto.html' },
    'Smartphone X': { precio: 699, pagina: 'producto-smartphone.html' },
    'Audifonos Max': { precio: 129, pagina: 'producto-audifonos.html' },
    'Teclado Mecanico': { precio: 89, pagina: 'producto-teclado.html' },
    'Mouse Gamer': { precio: 49, pagina: 'producto-mouse.html' },
    'Monitor 24': { precio: 199, pagina: 'producto-monitor.html' },
    'Tablet Edu': { precio: 249, pagina: 'producto-tablet.html' },
    'Camara Web HD': { precio: 59, pagina: 'producto-camara.html' }
  };

  function formatoPrecio(valor) {
    return '$' + valor;
  }

  function obtenerCarrito() {
    return JSON.parse(localStorage.getItem('techstore-carrito') || '[]');
  }

  function guardarCarrito(carrito) {
    localStorage.setItem('techstore-carrito', JSON.stringify(carrito));
    actualizarContador();
  }

  function totalCarrito(carrito) {
    return carrito.reduce(function (total, item) {
      return total + item.precio * item.cantidad;
    }, 0);
  }

  function actualizarContador() {
    var contador = document.getElementById('contador-carrito');
    if (!contador) return;
    var total = obtenerCarrito().reduce(function (suma, item) {
      return suma + item.cantidad;
    }, 0);
    contador.textContent = total;
  }

  function agregarProducto(nombre) {
    var info = productos[nombre];
    if (!info) return;
    var carrito = obtenerCarrito();
    var existente = carrito.find(function (item) {
      return item.nombre === nombre;
    });
    if (existente) {
      existente.cantidad += 1;
    } else {
      carrito.push({ nombre: nombre, precio: info.precio, cantidad: 1 });
    }
    guardarCarrito(carrito);
    alert(nombre + ' agregado al carrito.');
  }

  function renderCarrito() {
    var cuerpo = document.getElementById('carrito-cuerpo');
    var totalTexto = document.getElementById('carrito-total');
    var checkoutTotal = document.getElementById('checkout-total');
    if (!cuerpo || !totalTexto) return;

    var carrito = obtenerCarrito();
    cuerpo.innerHTML = '';

    if (carrito.length === 0) {
      cuerpo.innerHTML = '<tr><td colspan="5">El carrito esta vacio.</td></tr>';
      totalTexto.textContent = '$0';
      if (checkoutTotal) checkoutTotal.textContent = '$0';
      return;
    }

    carrito.forEach(function (item, index) {
      var subtotal = item.precio * item.cantidad;
      var fila = document.createElement('tr');
      fila.innerHTML =
        '<td>' + item.nombre + '</td>' +
        '<td>' + item.cantidad + '</td>' +
        '<td>' + formatoPrecio(item.precio) + '</td>' +
        '<td>' + formatoPrecio(subtotal) + '</td>' +
        '<td><button type="button" data-eliminar="' + index + '">Quitar</button></td>';
      cuerpo.appendChild(fila);
    });

    var total = totalCarrito(carrito);
    totalTexto.textContent = formatoPrecio(total);
    if (checkoutTotal) checkoutTotal.textContent = formatoPrecio(total);
  }

  function activarMenu() {
    var paginaActual = window.location.pathname.split('/').pop() || 'index.html';
    document.querySelectorAll('nav a').forEach(function (enlace) {
      var destino = enlace.getAttribute('href');
      var esDetalle = paginaActual.indexOf('producto') === 0 && destino === 'producto.html';
      if (destino === paginaActual || esDetalle) {
        enlace.classList.add('activo');
      }
    });
  }

  function prepararContacto() {
    var rango = document.getElementById('satisfaccion');
    var salida = document.querySelector('output[for=satisfaccion]');
    if (rango && salida) {
      salida.textContent = rango.value;
      rango.addEventListener('input', function () {
        salida.textContent = rango.value;
      });
    }

    var ayuda = document.getElementById('ayuda-contacto-btn');
    if (ayuda) {
      ayuda.addEventListener('click', function () {
        alert('Completa los campos requeridos y envia el formulario.');
      });
    }

    var form = document.getElementById('form-contacto');
    var mensaje = document.getElementById('mensaje-formulario');
    if (form && mensaje) {
      form.addEventListener('submit', function (evento) {
        evento.preventDefault();
        mensaje.textContent = 'Mensaje enviado correctamente. TechStore respondera pronto.';
      });
    }
  }

  function limpiarTextoPdf(texto) {
    return String(texto || '')
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '')
      .replace(/[^\x20-\x7E]/g, '?');
  }

  function escaparPdf(texto) {
    return limpiarTextoPdf(texto)
      .replace(/\\/g, '\\\\')
      .replace(/\(/g, '\\(')
      .replace(/\)/g, '\\)');
  }

  function crearPdfPedido(orden) {
    var lineas = [
      'TechStore Online',
      'Comprobante de compra simulada',
      'Pedido: ' + orden.pedido,
      'Fecha: ' + new Date(orden.fecha).toLocaleString('es-ES'),
      'Cliente: ' + orden.nombre,
      'Correo: ' + orden.email,
      'Telefono: ' + (orden.telefono || 'No indicado'),
      'Direccion: ' + orden.direccion,
      'Metodo de pago: ' + orden.metodo,
      ' ',
      'Productos:'
    ];

    orden.productos.forEach(function (item) {
      lineas.push(
        item.cantidad + ' x ' + item.nombre + ' - ' +
        formatoPrecio(item.precio * item.cantidad)
      );
    });

    lineas.push(' ');
    lineas.push('Total: ' + formatoPrecio(orden.total));
    lineas.push(' ');
    lineas.push('Este PDF es un comprobante local. No se envia ningun mensaje.');

    var comandos = ['BT', '/F1 18 Tf', '50 760 Td'];
    lineas.forEach(function (linea, index) {
      if (index === 1) {
        comandos.push('/F1 12 Tf');
      }
      if (index > 0) {
        comandos.push('0 -22 Td');
      }
      comandos.push('(' + escaparPdf(linea) + ') Tj');
    });
    comandos.push('ET');

    var stream = comandos.join('\n');
    var objetos = [
      '1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n',
      '2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n',
      '3 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Resources << /Font << /F1 5 0 R >> >> /Contents 4 0 R >>\nendobj\n',
      '4 0 obj\n<< /Length ' + stream.length + ' >>\nstream\n' + stream + '\nendstream\nendobj\n',
      '5 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\nendobj\n'
    ];

    var pdf = '%PDF-1.4\n';
    var offsets = [0];
    objetos.forEach(function (objeto) {
      offsets.push(pdf.length);
      pdf += objeto;
    });

    var xref = pdf.length;
    pdf += 'xref\n0 ' + (objetos.length + 1) + '\n';
    pdf += '0000000000 65535 f \n';
    for (var i = 1; i < offsets.length; i += 1) {
      pdf += String(offsets[i]).padStart(10, '0') + ' 00000 n \n';
    }
    pdf += 'trailer\n<< /Size ' + (objetos.length + 1) + ' /Root 1 0 R >>\n';
    pdf += 'startxref\n' + xref + '\n%%EOF';

    return new Blob([pdf], { type: 'application/pdf' });
  }

  function descargarPdfPedido(orden) {
    var pdf = crearPdfPedido(orden);
    var enlace = document.createElement('a');
    var url = URL.createObjectURL(pdf);
    enlace.href = url;
    enlace.download = orden.pedido + '-techstore.pdf';
    document.body.appendChild(enlace);
    enlace.click();
    enlace.remove();
    URL.revokeObjectURL(url);
  }

  function prepararCheckout() {
    var form = document.getElementById('checkout-form');
    var resultado = document.getElementById('compra-resultado');
    var pdfEstado = document.getElementById('pdf-confirmacion');
    if (!form || !resultado || !pdfEstado) return;

    form.addEventListener('submit', function (evento) {
      evento.preventDefault();

      var carrito = obtenerCarrito();
      if (carrito.length === 0) {
        resultado.className = 'order-error';
        resultado.textContent = 'Agrega productos al carrito antes de comprar.';
        pdfEstado.textContent = '';
        return;
      }

      if (!form.checkValidity()) {
        form.reportValidity();
        return;
      }

      var nombre = document.getElementById('cliente-nombre').value.trim();
      var email = document.getElementById('cliente-email').value.trim();
      var telefono = document.getElementById('cliente-telefono').value.trim();
      var direccion = document.getElementById('cliente-direccion').value.trim();
      var metodo = document.getElementById('metodo-pago').value;
      var total = totalCarrito(carrito);
      var pedido = 'TS-' + Date.now().toString().slice(-6);

      var orden = {
        pedido: pedido,
        nombre: nombre,
        email: email,
        telefono: telefono,
        direccion: direccion,
        metodo: metodo,
        total: total,
        productos: carrito,
        fecha: new Date().toISOString()
      };

      localStorage.setItem('techstore-ultimo-pedido', JSON.stringify(orden));
      descargarPdfPedido(orden);
      resultado.className = 'order-success';
      resultado.innerHTML = 'Compra simulada completada. Se creo el comprobante PDF del pedido <strong>' + pedido + '</strong>.';
      pdfEstado.textContent = 'El PDF incluye nombre, correo, telefono, direccion, productos y total.';
      guardarCarrito([]);
      renderCarrito();
      form.reset();
    });
  }

  function prepararDialogo() {
    var abrir = document.getElementById('abrir-dialogo');
    var dialogo = document.getElementById('aviso-dialogo');
    if (abrir && dialogo) {
      abrir.addEventListener('click', function () {
        dialogo.showModal();
      });
    }
  }

  function dibujarCanvas() {
    var canvas = document.getElementById('canvas-producto');
    if (!canvas || !canvas.getContext) return;
    var ctx = canvas.getContext('2d');
    ctx.fillStyle = '#e8f1ff';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.strokeStyle = '#12233f';
    ctx.lineWidth = 4;
    ctx.strokeRect(24, 24, canvas.width - 48, canvas.height - 48);
    ctx.fillStyle = '#12233f';
    ctx.font = '22px Arial';
    ctx.fillText('TechStore', 78, 78);
  }

  document.addEventListener('DOMContentLoaded', function () {
    activarMenu();
    actualizarContador();
    prepararContacto();
    prepararCheckout();
    prepararDialogo();
    dibujarCanvas();
    renderCarrito();

    document.addEventListener('click', function (evento) {
      var comprar = evento.target.closest('[data-comprar]');
      if (comprar) {
        agregarProducto(comprar.getAttribute('data-comprar'));
      }

      var eliminar = evento.target.closest('[data-eliminar]');
      if (eliminar) {
        var carrito = obtenerCarrito();
        carrito.splice(Number(eliminar.getAttribute('data-eliminar')), 1);
        guardarCarrito(carrito);
        renderCarrito();
      }
    });

    var vaciar = document.getElementById('vaciar-carrito');
    if (vaciar) {
      vaciar.addEventListener('click', function () {
        guardarCarrito([]);
        renderCarrito();
      });
    }
  });
}());




