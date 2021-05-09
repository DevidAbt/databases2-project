$(document).ready(function () {
  var userString = sessionStorage.getItem("user");
  if (!userString) {
    window.location.replace("./profilom.html");
  }
  var user = JSON.parse(userString);
  if (!user || !user.isAdmin) {
    window.location.replace("./index.html");
  }

  setUpListeners();
});

function setUpListeners() {
  $("#usersButton").click(() => {
    getUsers();
  });
  //   $("#ordersButton").click(() => {
  //     getOrders();
  //   });
  $("#recent3button").click(() => {
    getRecentOrders(3);
  });
  $("#recent6button").click(() => {
    getRecentOrders(6);
  });
  $("#recent12button").click(() => {
    getRecentOrders(12);
  });

  $("#learazasButton").click(() => {
    let typeId = parseInt($("#learazasTypeId").val());
    let price = parseInt($("#learazasPrice").val());
    learaz(typeId, price);
  });
}

function getUsers() {
  console.log("getUsers called");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/user/all",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("getUsers: ", data);
      updateUsersTable(data);
    },
    error: function (e) {
      console.log("getUsers error");
      console.log(e);
    },
  });
}

function updateUsersTable(users) {
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <h2>Felhasználók</h2>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>AZONOSÍTÓ</td>
              <td>FELHASZNALONEV</td>
              <td>NÉV</td>
              <td>TELEFONSZAM</td>
              <td>EMAIL</td>
            </tr>`;
  users.forEach((user) => {
    table += `<tr class="termek-box">
              <td>${user.id}</td>
              <td>${user.felhasznalonev}</td>
              <td>${user.nev}</td>
              <td>${user.telefonszam}</td>
              <td>${user.email}</td>
              `;
  });
  table += `</table>
          </div>
        </main>
      </div>
    </div>`;

  $("#content-panel").html(table);
}

function getRecentOrders(months) {
  console.log("getOrders called");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/order/recent",
    data: {
      months: months,
    },
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("getOrders: ", data);
      updateOrdersTable(data, months);
    },
    error: function (e) {
      console.log("getOrders error");
      console.log(e);
    },
  });
}

function updateOrdersTable(orders, months) {
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <h2>Rendelések az elmúlt ${months} hónapból</h2>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>RENDELÉS SZÁM</td>
              <td>FELH. AZONOSÍTÓ</td>
              <td>FELHASZNALONEV</td>
              <td>DÁTUM</td>
              <td>KUPONKÓD</td>
              <td>TERMEK AZONOSÍTÓ</td>
              <td>ÁRUCIKK NEVE</td>
              <td>MENNYISÉG</td>
            </tr>`;
  orders.forEach((order) => {
    table += `<tr class="termek-box">
              <td>${order.rendelesSzam}</td>
              <td>${order.felhasznaloId}</td>
              <td>${order.felhasznalonev}</td>
              <td>${order.mikor}</td>
              <td>${order.kuponKod ? order.kuponKod : "-"}</td>
              <td>${order.termekid ? order.termekid : 200}</td>
              <td>${order.termeknev ? order.termeknev : order.nev}</td>
              <td>${order.mennyiseg}</td>
            </tr>`;
  });
  table += `</table>
          </div>
        </main>
      </div>
    </div>`;

  $("#content-panel").html(table);
}

function learaz(typeId, price) {
  console.log("learaz called");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/order/price",
    data: {
      productTypeId: typeId,
      price: price,
    },
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("learaz: ", data);
      window.location.replace("./termekek.html");
    },
    error: function (e) {
      console.log("learaz error:");
      console.log(JSON.stringify(e));
    },
  });
}
