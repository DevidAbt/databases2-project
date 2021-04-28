function addToCart(product) {
  var user = sessionStorage.getItem("user");
  if (!user) {
    window.location.replace("./profilom.html");
  }
  console.log("addToCart called, ", product);
  let cartString = sessionStorage.getItem("cart");
  if (cartString) {
    let cart = JSON.parse(cartString);
    let sameProduct = cart.filter((x) => x.id === product.id)[0];
    if (sameProduct) {
      sameProduct.mennyiseg += product.mennyiseg;
    } else {
      cart.push(product);
    }
    console.log("addToCart: ", cart);
    sessionStorage.setItem("cart", JSON.stringify(cart));
  } else {
    console.log("addToCart: ", [product]);
    sessionStorage.setItem("cart", JSON.stringify([product]));
  }
}

function getCart() {
  console.log("getCart called, ");
  let cartString = sessionStorage.getItem("cart");
  let cart = JSON.parse(cartString);
  console.log("getCart ", cart);
  return cart;
}

function removeFromCart(id) {
  console.log("removeFromCart called, ", id);
  let cartString = sessionStorage.getItem("cart");
  if (cartString) {
    let cart = JSON.parse(cartString);
    let product = cart.filter((x) => x.id === id)[0];
    product.mennyiseg = 0;
    console.log("removeFromCart: ", cart);
    sessionStorage.setItem("cart", JSON.stringify(cart));
  }
}
