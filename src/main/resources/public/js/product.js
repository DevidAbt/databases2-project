var admin = false;
$(document).ready(function () {
  getCategories();

  var userString = sessionStorage.getItem("user");
  var user = JSON.parse(userString);
  if (user && user.isAdmin) {
    admin = true;
  }

  $.getScript("./js/cart.js", () => {
    getCart();
  });

  setUpListeners();
});

function switchToProductInfo() {
  $("#category-rows").css("display", "none");
  $("#product-table").css("display", "block");
  if (admin) {
    $("#add-row").css("display", "block");
  }
  $("#search-row").css("display", "none");
}

function switchToCategoryRows() {
  $("#category-rows").css("display", "block");
  $("#product-table").css("display", "none");
  if (admin) {
    $("#add-row").css("display", "none");
  }
  $("#shop-table").css("display", "none");
  $("#shop-table").html("");
  $("#ratings-table").css("display", "none");
  $("#ratings-table").html("");
  $("#search-row").css("display", "block");
}

function setUpListeners() {
  $("#searchButton").click(() => {
    let searchInput = $("#searchInput").val();
    getShopInfoByName(searchInput);
  });

  $("#addButton").click(() => {
    let name = $("#addName").val();
    let desc = $("#addDesc").val();
    let price = parseInt($("#addPrice").val());
    let shop = $("#addShop").val();
    addProduct(name, desc, price, shop);
  });
}

var categoriesWithTypes = [];
var currentProducts = [];
var currentCategory;
var currentProdutctType;

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
  currentCategory = kategoria.id;
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
  currentProdutctType = termekFajtaId;
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
                    <h2>Termékek</h2>
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
                <td><a href="#" onclick="getShopInfoByProduct(${product.id});return false;">ÜZLET</a></td>
                <td>${product.termekFajta}</td>
                <td>${product.kategoria}</td>
                <td>${product.ar}</td>
                <td>${product.leiras}</td>
                <td><a href="#" onclick="getRatings(${product.id});return false;">ÉRTÉKELÉS</a></td>
                <td>`;
    if (!admin) {
      table += `<input type="number" style="width: 35px" min="1" value="1">
      <button onclick="toCart(${product.id})">KOSÁRBA</button>`;
    } else {
      table += `<button onclick="removeProduct(${product.id})">TÖRLÉS</button>`;
    }
    table += "</td>";
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
                    <h2>Üzletek</h2>
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
  $("#ratings-table").css("display", "none");
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

function getRatings(productId) {
  console.log("getRatings called, ", productId);
  $.ajax({
    type: "GET",
    url: "/api/product/ratings",
    data: {
      termekId: productId,
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
              <td>DÁTUM</td>`;
  if (admin) {
    table += "<td></td>";
  }
  table += "</tr>";
  ratings.forEach((rating) => {
    table += `<tr class="termek-box">
              <td>${rating.csillag}/10</td>
              <td>${rating.szoveg}</td>
              <td>${rating.datum}</td>`;
    if (admin) {
      table += `<td><button onclick="removeRating(${rating.id})">TÖRLÉS</button></td>`;
    }
  });
  table += `</table>
          </div>
        </main>
      </div>
    </div>`;

  $("#ratings-table").html(table);
  $("#ratings-table").css("display", "block");
  $("#shop-table").css("display", "none");
}

function addProduct(name, desc, price, shop) {
  console.log("addProduct called");
  console.log(currentProdutctType);
  console.log(currentCategory);
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/api/product/add",
    data: JSON.stringify({
      uzletId: shop,
      termekFajtaId: currentProdutctType,
      kategoriaId: currentCategory,
      nev: name,
      ar: price,
      leiras: desc,
    }),
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("addProduct: ", data);
      getProductsInfo(currentProdutctType);
    },
    error: function (e) {
      console.log("addProduct error:");
      console.log(JSON.stringify(e));
    },
  });
}

function removeProduct(productId) {
  console.log("removeProduct called");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/product/remove",
    data: {
      productId: productId,
    },
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("removeProduct: ", data);
      getProductsInfo(currentProdutctType);
    },
    error: function (e) {
      console.log("removeProduct error");
      console.log(e);
    },
  });
}

function removeRating(ratingId) {
  console.log("removeRating called", ratingId);
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/product/removeRating",
    data: {
      ratingId: ratingId,
    },
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("removeRating: ", data);
      $("#ratings-table").css("display", "none");
      $("#ratings-table").html("");
    },
    error: function (e) {
      console.log("removeRating error");
      console.log(e);
    },
  });
}
