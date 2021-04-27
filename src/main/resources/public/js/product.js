$(document).ready(function () {
  getCategories();

  $.getScript("./js/cart.js", () => {
    getCart();
  });

  setUpListeners();
});

function switchToProductInfo() {
  $("#category-rows").css("display", "none");
  $("#product-table").css("display", "block");
  $("#search-row").css("display", "none");
}

function switchToCategoryRows() {
  $("#category-rows").css("display", "block");
  $("#product-table").css("display", "none");
  $("#shop-table").css("display", "none");
  $("#shop-table").html("");
  $("#search-row").css("display", "block");
}

function setUpListeners() {
  $("#searchButton").click(() => {
    let searchInput = $("#searchInput").val();
    getShopInfoByName(searchInput);
  });
}

var categoriesWithTypes = [];
var currentProducts = [];

function getCategories() {
  console.log("getCategories called");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/product/categories",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("getCategories: ", data);
      data.forEach((kategoria) => {
        getProductTypes(kategoria);
      });
    },
    error: function (e) {
      console.log("getCategories error");
      console.log(e);
    },
  });
}

function getProductTypes(kategoria) {
  console.log("getProductTypes called, ", kategoria);
  $.ajax({
    type: "GET",
    url: "/api/product/types/",
    data: {
      kategoriaId: kategoria.id,
    },
    success: function (data) {
      console.log("getProductTypes: ", data);
      kategoria.types = data;
      categoriesWithTypes.push(kategoria);
      updateCategoryRows();
    },
    error: function (e) {
      console.log("getProductTypes error");
      console.log(e);
    },
  });
}

function updateCategoryRows() {
  let rows = [];
  categoriesWithTypes.forEach((category) => {
    let row = `<div class="row">
                 <div class="garden">
                   <h2>${category.nev}</h2>`;
    category.types.forEach((type) => {
      row += `<div class="menu">
                  <li><a href="#" onclick="getProductsInfo(${type.id});return false;">${type.nev}</a></li>
                  <img class="tkep" src="./img/jacint.jpg" alt="leves" />
                </div>`;
    });
    row += `
                 </div>
               </div>`;
    rows.push(row);
  });

  $("#category-rows").html(rows.join());
}

function getProductsInfo(termekFajtaId) {
  console.log("getProductsInfo called, ", termekFajtaId);
  $.ajax({
    type: "GET",
    url: "/api/product/type/info",
    data: {
      termekFajtaId: termekFajtaId,
    },
    success: function (data) {
      console.log("getProductsInfo: ", data);
      updateProductTable(data);
      switchToProductInfo();
    },
    error: function (e) {
      console.log("getProductsInfo error");
      console.log(e);
    },
  });
}

function updateProductTable(products) {
  currentProducts = products;
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>KÉP</td>
              <td>NÉV</td>
              <td>TERMÉKKÓD</td>
              <td>ÜZLET, AHOL KAPHATÓ</td>
              <td>TERMÉKFAJTA</td>
              <td>KATEGÓRIA</td>
              <td>ÁR</td>
              <td>LEÍRÁS</td>
              <td>ÉRTÉKELÉS</td>
              <td></td>
            </tr>`;
  products.forEach((product) => {
    table += `<tr id="product-${product.id}" class="termek-box">
                <td>
                  <img src="./img/torpe.jpg" alt="jacint" />
                </td>
                <td>${product.nev}</td>
                <td>${product.id}</td>
                <td><a href="#" onclick="getShopInfoByProduct(${product.id});return false;">UZLET</a></td>
                <td>${product.termekFajta}</td>
                <td>${product.kategoria}</td>
                <td>${product.ar}</td>
                <td>${product.leiras}</td>
                <td>Ertekeles</td>
                <td>
                  <input type="number" style="width: 35px" min="1" value="1">
                  <button onclick="toCart(${product.id})">KOSÁRBA</button>
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

  $("#product-table").html(table);
}

function getShopInfoByProduct(termekId) {
  console.log("getShopInfoByProduct called, ", termekId);
  $.ajax({
    type: "GET",
    url: "/api/product/shop/info",
    data: {
      termekId: termekId,
    },
    success: function (data) {
      console.log("getShopInfoByProduct: ", data);
      updateShopTable(data);
    },
    error: function (e) {
      console.log("getShopInfoByProduct error");
      console.log(e);
    },
  });
}

function updateShopTable(shop) {
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>ÜZLETKÓD</td>
              <td>VÁROS</td>
              <td>UTCA</td>
              <td>HÁZSZÁM</td>
              <td>NYITÁS</td>
              <td>ZÁRÁS</td>
            </tr>`;
  table += `<tr class="termek-box">
              <td>${shop.id}</td>
              <td>${shop.varos}</td>
              <td>${shop.utca}</td>
              <td>${shop.hazszam}</td>
              <td>${shop.nyitas}</td>
              <td>${shop.zaras}</td>
              `;
  table += `</table>
          </div>
        </main>
      </div>
    </div>`;

  $("#shop-table").html(table);
  $("#shop-table").css("display", "block");
}

function getShopInfoByName(nev) {
  console.log("getShopInfoByName called, ", nev);
  $.ajax({
    type: "GET",
    url: "/api/product/search/name",
    data: {
      nev: nev,
    },
    success: function (data) {
      console.log("getShopInfoByName: ", data);
      updateProductTable(data);
      switchToProductInfo();
    },
    error: function (e) {
      console.log("getShopInfoByName error");
      console.log(e);
    },
  });
}

function toCart(id) {
  console.log("toCart called, ", id);
  let product = currentProducts.filter((x) => x.id == id)[0];
  let mennyiseg = parseInt($(`#product-${product.id} > td > input`).val());
  product.mennyiseg = mennyiseg;
  addToCart(product);
}
