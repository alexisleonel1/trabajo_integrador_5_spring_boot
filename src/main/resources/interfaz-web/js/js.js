"use strict"

let formCliente = document.getElementById("formCliente");
formCliente.addEventListener('submit', postCliente);

let formNuevoProducto = document.getElementById("formProducto");
formNuevoProducto.addEventListener('submit', postProducto);

let formNuevaVenta = document.getElementById("formVenta");
formNuevaVenta.addEventListener('submit', postVenta);

let ProdMasVendido = document.getElementById("prodMasVendido");
ProdMasVendido.addEventListener("toggle", event => {
  if (ProdMasVendido.open) {
    getProductoMasVendido()
  }});

document.addEventListener('DOMContentLoaded', iniciar);

function iniciar(){
  getClientes();
  setTimeout(() => {
    getProductos();
  }, "1000"); 
  setTimeout(() => {
    getMontoXCliente();
  }, "2000");
  setTimeout(() => {
    getVentasByDay();
  }, "3000");  
}

function postCliente() {
  event.preventDefault();
  let nombre = formCliente.querySelector('input[name="nombre"]');
  let apellido = formCliente.querySelector('input[name="apellido"]');
  (async () => {
    const rawResponse = await fetch('http://localhost:9007/clientes', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
          "nombre": nombre.value,
          "apellido":apellido.value
      })
    });
  })();
  nombre.value="";
  apellido.value="";
  getClientes();
}

function postVenta() {
  event.preventDefault();
  let cliente = formNuevaVenta.querySelector('select[name="cliente"]');
  let producto = formNuevaVenta.querySelector('select[name="producto"]').value;
  let cant = formNuevaVenta.querySelector('input[name="cant"]');
  producto = producto.split("/");
  (async () => {
    const rawResponse = await fetch('http://localhost:9007/ventas', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        "cliente": {
          "id": cliente.value,
          "nombre": "string",
          "apellido": "string"
        },
        "producto": {
          "id": producto[0],
          "nombre": producto[3],
          "stock": producto[1],
          "precio": producto[2]
        },
        "fecha": date(),
        "cantidad":cant.value,
        "total": producto[2]*cant.value
      })
    });
  })();
  cant.value="";
  getProductos();
}

function postProducto() {
    event.preventDefault();
    let nombre = formNuevoProducto.querySelector('input[name="nombre"]');
    let precio = formNuevoProducto.querySelector('input[name="precio"]');
    let stock = formNuevoProducto.querySelector('input[name="stock"]');
    (async () => {
      const rawResponse = await fetch('http://localhost:9007/productos', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "nombre": nombre.value,
            "precio":precio.value,
            "stock":stock.value
        })
      });
    })();
    nombre.value="";
    precio.value="";
    stock.value="";
    getProductos();
}

function getClientes(){
  fetch('http://localhost:9007/clientes')
  .then(response => response.json())
  .then(data => addClientes(data))
  .catch(); // Capturar errores
}

function addClientes(data){
  let select = document.getElementById("selectClientes");
  select.innerHTML = '';
  data.forEach(c =>{
    select.innerHTML += "<option value=" + c.id +">"+ c.nombre + " " + c.apellido +" </option>";
  });
}
function getProductos(){
  fetch('http://localhost:9007/productos')
  .then(response => response.json())
  .then(data => addProductos(data))
  .catch(); // Capturar errores
}

function addProductos(data){
  let select = document.getElementById("selectProducto");
  select.innerHTML = '';
  data.forEach(p =>{
    select.innerHTML += "<option value=" + p.id +"/"+ p.stock +"/"+ p.precio +"/"+ p.nombre +">"+ p.nombre + " " + "PRECIO: " + p.precio + " STOCK: " + p.stock +" </option>";
  });
}

function getMontoXCliente(){
  fetch('http://localhost:9007/clientes/compras')
  .then(response => response.json())
  .then(data => addMontoXCliente(data))
  .catch(); // Capturar errores
}

function addMontoXCliente(data){
  let select = document.getElementById("tBodyClientesConMonto");
  select.innerHTML = '';
  data.forEach(d =>{
    select.innerHTML += "<tr><td>" + d.nombre+ " " +d.apellido +
    "</td><td>" +d.total + "</td></tr>";
  });
}

function getVentasByDay(){
  fetch('http://localhost:9007/ventas/ventas-por-dia')
  .then(response => response.json())
  .then(data => addVentas(data))
  .catch(); // Capturar errores
}

function addVentas(data){
  let select = document.getElementById("tBodyVentas");
  select.innerHTML = '';
  data.forEach(v =>{
    select.innerHTML += "<tr><td>" + v.fecha + "</td><td> " 
    + v.cantidad + "</td><td>"
  });
}

function getProductoMasVendido(){
  fetch('http://localhost:9007/ventas/producto-mas-vendido')
  .then(response => response.json())
  .then(data => addProductoMasVendido(data))
  .catch(() => getProductoMasVendido()); // Capturar errores
}

function addProductoMasVendido(data){
  console.log(data)
  let select = document.getElementById("masVendido");
  select.innerHTML = '';
    select.innerHTML += "<p>El producto mas vendido es: <strong>"+data.nombre+
    "</strong></p><p>Actualmente quedan: <strong>"+ data.stock +"</strong> unidades</p>";
}

function date(){
  const date = new Date();
  const año = date.getFullYear();
  const mes = date.getMonth()+1>9? date.getMonth()+1 : '0'+(date.getMonth()+1);
  const dia = date.getDate()>9? date.getDate() : '0'+date.getDate();
  const horas = date.getHours()>9? date.getHours() : '0'+date.getHours();
  const minutos = date.getMinutes()>9? date.getMinutes() : '0'+date.getMinutes();
  const milisegundos = '0.0';
  const fecha = año+'-'+mes+'-'+dia+'T'+horas+':'+minutos+':'+milisegundos+"Z";
  return fecha;
}