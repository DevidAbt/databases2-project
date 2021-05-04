$(document).ready(function () {
  getServices();

  $.getScript("./js/cart.js", () => {
    getCart();
  });

  setUpListeners();
});

function setUpListeners() {
  //TODO
  $("#searchButton").click(() => {
    let searchInput = $("#searchInput").val();
    // getShopInfoByName(searchInput);
  });
}

var currentServices = [];

function getServices() {
  console.log("getServices called");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/service/all",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("getServices: ", data);
      updateServicesTable(data);
    },
    error: function (e) {
      console.log("getServices error");
      console.log(e);
    },
  });
}

function updateServicesTable(services) {
  currentServices = services;
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <h2>Szolgáltatások</h2>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>KÉP</td>
              <td>NÉV</td>
              <td>SZOLGÁLTATÁS KÓD</td>
              <td>ÁR</td>
              <td>LEÍRÁS</td>
              <td>ÉRTÉKELÉS</td>
              <td></td>
            </tr>`;
  services.forEach((service) => {
    table += `<tr id="service-${service.id}" class="termek-box">
                <td>
                  <img src="./img/torpe.jpg" alt="jacint" />
                </td>
                <td>${service.nev}</td>
                <td>${service.id}</td>
                <td>${service.ar}</td>
                <td>${service.leiras}</td>
                <td><a href="#" onclick="getRatings(${service.id});return false;">ÉRTÉKELÉS</a></td>
                <td>
                  <input type="number" style="width: 35px" min="1" value="1">
                  <button onclick="toCart(${service.id})">KOSÁRBA</button>
                </td>
                `;
  });
  table += `</table>
          </div>
        </main>
        <button style="margin: auto;display: block;"
          onclick="switchToCategoryRows()">Vissza</button>
      </div>
    </div>`;

  $("#service-table").html(table);
}

function toCart(id) {
  console.log("toCart called, ", id);
  let service = currentServices.filter((x) => x.id == id)[0];
  let mennyiseg = parseInt($(`#service-${service.id} > td > input`).val());
  service.mennyiseg = mennyiseg;
  addToCart(service);
}

function getRatings(serviceId) {
  console.log("getRatings called, ", serviceId);
  $.ajax({
    type: "GET",
    url: "/api/service/ratings",
    data: {
      termekId: serviceId,
    },
    success: function (data) {
      console.log("getRatings: ", data);
      updateRatingsTable(data);
    },
    error: function (e) {
      console.log("getRatings error");
      console.log(e);
    },
  });
}

function updateRatingsTable(ratings) {
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <h2>Értékelések</h2>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>CSILLAG</td>
              <td>SZÖVEG</td>
              <td>DÁTUM</td>
            </tr>`;
  ratings.forEach((rating) => {
    table += `<tr class="termek-box">
              <td>${rating.csillag}/10</td>
              <td>${rating.szoveg}</td>
              <td>${rating.datum}</td>`;
  });
  table += `</table>
          </div>
        </main>
      </div>
    </div>`;

  $("#ratings-table").html(table);
  $("#ratings-table").css("display", "block");
}
