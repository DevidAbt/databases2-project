$(document).ready(function () {
  var userString = sessionStorage.getItem("user");
  if (!userString) {
    window.location.replace("./profilom.html");
  }
  var user = JSON.parse(userString);

  $.getScript("./js/cart.js", () => {
    let cart = getCart();
    updateCartTable(cart ? cart : []);
  });

  getOrdersOfUser(user.id);
});

function updateCartTable(cart) {
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <h2>Kosaram</h2>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>Kód</td>
              <td>Termék/Szolgáltatás neve</td>
              <td>Mennyiség</td>
              <td>Ár/darab</td>
              <td>Ár</td>
              <td>töröl</td>
            </tr>`;
  let sum = 0;
  cart.forEach((product) => {
    sum += product.ar * product.mennyiseg;
    if (product.mennyiseg > 0) {
      table += `<tr class="termek-box">
                <td>${product.id}</td>
                <td>${product.nev}</td>
                <td>${product.mennyiseg}</td>
                <td>${product.ar} Ft</td>
                <td>${product.ar * product.mennyiseg} Ft</td>
                <td><button onclick="remove(${product.id})">Töröl</button></td>
                </tr>
              `;
    }
  });
  table += `</table>
          </div>
        </main>
        <div>
          <div style="margin: auto;display: block;">Összesen: ${sum}Ft</div>
          <div id="order" style="margin-top: 7px;">
            <input id="kuponKod" type="number" placeholder="Kuponkód" ></input>
            <input id="atvevo" type="text" placeholder="Átvevő neve" ></input>
            <button style="margin: auto;"
              onclick="order()">Rendelés</button>
          </div>
        </div>
      </div>
    </div>`;

  $("#cart-table").html(table);
}

function remove(id) {
  removeFromCart(id);
  let cart = getCart();
  updateCartTable(cart ? cart : []);
}

function order() {
  var userString = sessionStorage.getItem("user");
  let user = JSON.parse(userString);
  let kuponKod = parseInt($("#kuponKod").val());
  let atvevo = $("#atvevo").val();
  console.log("order called");
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/api/order/place",
    data: JSON.stringify({
      felhasznaloId: user.id,
      kuponKod: kuponKod,
      atvevoNev: atvevo,
      lakcimId: 900,
    }),
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("order: ", data);
      $.getScript("./js/cart.js", () => {
        let cart = getCart();
        let products = [];
        let services = [];
        cart.forEach((item) => {
          if (item.mennyiseg > 0) {
            if (item.kategoria) {
              products.push(item);
            } else {
              services.push(item);
            }
          }
        });

        orderProduct(products);
        orderServices(services);

        sessionStorage.removeItem("cart");
        updateCartTable([]);
        getOrdersOfUser(JSON.parse(sessionStorage.getItem("user")).id);
      });
    },
    error: function (e) {
      console.log("order error:");
      console.log(e);
    },
  });
}

function orderProduct(products) {
  console.log("orderProduct called");
  let payload = products.map((x) => {
    return {
      id: x.id,
      mennyiseg: x.mennyiseg,
    };
  });
  let payloadString = JSON.stringify(payload);
  console.log(payloadString);
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/api/order/products",
    data: payloadString,
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("orderProduct: ", data);
    },
    error: function (e) {
      console.log("orderProduct error:");
      console.log(e);
    },
  });
}

function orderServices(services) {
  let ids = services.map((x) => x.id);
  console.log("orderServices called");
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/api/order/services",
    data: JSON.stringify(ids),
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("orderServices: ", data);
    },
    error: function (e) {
      console.log("orderServices error:");
      console.log(e);
    },
  });
}

function getOrdersOfUser(userId) {
  console.log("getOrdersOfUser called, ", userId);
  $.ajax({
    type: "GET",
    url: "/api/order/user",
    data: {
      felhasznaloId: userId,
    },
    success: function (data) {
      console.log("getOrdersOfUser: ", data);
      updateOrderRows(data);
    },
    error: function (e) {
      console.log("getOrdersOfUser error");
      console.log(e);
    },
  });
}

function updateOrderRows(orders) {
  let tables = [];
  orders.forEach((order) => {
    let table = `<div class="row">
                <div class="content">
                  <main>
                    <h2>${order.rendelesSzam} számú rendelés, ${order.mikor}</h2>
                    <div class="kert">
                      <table>`;
    table += `<tr class="termek-box">
              <td>Kód</td>
              <td>Termék/Szolgáltatás neve</td>
              <td>Mennyiség</td>
              <td>Ár/darab</td>
              <td>Ár</td>
            </tr>`;
    let sum = 0;
    order.termekek.forEach((product) => {
      sum += product.ar * product.mennyiseg;
      if (product.mennyiseg > 0) {
        table += `<tr class="termek-box">
                <td>${product.id}</td>
                <td>${product.nev}</td>
                <td>${product.mennyiseg}</td>
                <td>${product.ar} Ft</td>
                <td>${product.ar * product.mennyiseg} Ft</td>
                </tr>
              `;
      }
    });
    order.szolgaltatasok.forEach((service) => {
      sum += service.ar;
      table += `<tr class="termek-box">
                <td>${service.id}</td>
                <td>${service.nev}</td>
                <td>1</td>
                <td>${service.ar} Ft</td>
                <td>${service.ar} Ft</td>
                </tr>
              `;
    });
    table += `</table>
          </div>
        </main>
        <div>
          <div style="margin: auto;display: block;">Összesen: ${sum}Ft</div>
        </div>
      </div>
    </div>`;
    tables.push(table);
  });

  $("#order-rows").html(tables.join(""));
}
