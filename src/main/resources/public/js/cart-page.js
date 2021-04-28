$(document).ready(function () {
    var user = sessionStorage.getItem("user");
    if (!user) {
        window.location.replace("./profilom.html");
    }
  $.getScript("./js/cart.js", () => {
    let cart = getCart();
    updateCartTable(cart ? cart : []);
  });
});

function updateCartTable(cart) {
  let table = `<div class="row">
                <div class="content">
                  <main>
                    <h2>Kosaram</h2>
                    <div class="kert">
                      <table>`;
  table += `<tr class="termek-box">
              <td>Termékkód</td>
              <td>Terméknév</td>
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
                <td><button onclick="remove(${
                  product.id
                })">Töröl</button></td>
                </tr>
              `;
    }
  });
  table += `</table>
          </div>
        </main>
        <div>
          <div style="margin: auto;display: block;">Összesen: ${sum}Ft</div>
          <button style="margin: auto;display: block;"
            onclick="">Rendelés</button>
        </div>
      </div>
    </div>`;

  $("#rows").html(table);
}

function remove(id) {
    removeFromCart(id);
    let cart = getCart();
    updateCartTable(cart ? cart : []);
}