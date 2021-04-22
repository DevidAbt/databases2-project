$(document).ready(function () {
  getCategories();
});

var categoriesWithTypes = [];

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
      updateRows();
    },
    error: function (e) {
      console.log(e);
    },
  });
}

function updateRows() {
  let rows = [];
  categoriesWithTypes.forEach((category) => {
    let row = `<div class="row">
                 <div class="garden">
                   <h2>${category.nev}</h2>`;
    let types = [];
    category.types.forEach(type => {
        row += `<div class="menu">
                  <li><a href="funyiro.html">${type.nev}</a></li>
                  <img class="tkep" src="./img/jacint.jpg" alt="leves" />
                </div>`;
    });
    row += `
                 </div>
               </div>`;
    rows.push(row);
  });

  $("#rows").html(rows.join());
}
