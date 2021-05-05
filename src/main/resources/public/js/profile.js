$(document).ready(function () {
  var userString = sessionStorage.getItem("user");
  if (userString) {
    switchToProfile();
    let user = JSON.parse(userString);
    console.log(user);
    fillProfileFields(user);
  }

  setUpListeners();
});

function switchToProfile() {
  $("#loginRegRow").css("display", "none");
  $("#profileRow").css("display", "block");
}

function switchToRegLogin() {
  $("#loginRegRow").css("display", "block");
  $("#profileRow").css("display", "none");
}

function fillProfileFields(user) {
  $("#profileName").text(user.nev);
  $("#profileUsername").text(user.felhasznalonev);
  $("#profileTel").text(user.telefonszam);
  $("#profileEmail").text(user.email);

  getAddress(user.lakcimId);
}

function fillAddressField(address) {
  $("#profileAddress").text(
    address.varos + ",\n" + address.utca + " " + address.hazszam
  );
}

function setUpListeners() {
  $("#LoginForm").on("submit", (event) => {
    event.preventDefault();
    let username = $("#loginUsername").val();
    let password = $("#loginPassword").val();

    login(username, password);

    return false;
  });

  $("#RegForm").on("submit", (event) => {
    event.preventDefault();
    let username = $("#regUsername").val();
    let name = $("#regName").val();
    let password = $("#regPassword").val();
    let tel = $("#regTel").val();
    let email = $("#regEmail").val();
    let city = $("#regCity").val();
    let street = $("#regStreet").val();
    let number = $("#regNumber").val();

    register(username, name, password, tel, email, city, street, number);

    return false;
  });

  $("#logoutButton").click(() => logout());
}

function login(username, password) {
  console.log("login called");
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/api/user/login",
    data: JSON.stringify({ felhasznalonev: username, hash: password }),
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("login: ", data);
      sessionStorage.setItem("user", JSON.stringify(data));
      fillProfileFields(data);
      switchToProfile();
      getAddress(data.lakcimId);
    },
    error: function (e) {
      console.log("login error:");
      console.log(JSON.stringify(e));
      $("#wrongLogin").css("display", "block");
    },
  });
}

function register(username, name, password, tel, email, city, street, number) {
  console.log("register called");
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/api/user/register",
    data: JSON.stringify({
      felhasznalonev: username,
      nev: name,
      hash: password,
      telefonszam: tel,
      email: email,
      lakcimId: 900,
      varos: city,
      utca: street,
      hazszam: number,
    }),
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      let result = JSON.stringify(data);
      console.log("register: ", result);
      console.log(result);
      if (result == "false") {
        $("#wrongReg").css("display", "block");
      } else {
        location.reload();
      }
    },
    error: function (e) {
      console.log("register error:");
      console.log(JSON.stringify(e));
      $("#wrongReg").css("display", "block");
    },
  });
}

function getAddress(id) {
  console.log("getAddress called");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/api/user/address",
    data: {
      id: id,
    },
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log("getAddress: ", data);
      fillAddressField(data);
    },
    error: function (e) {
      console.log("getAddress error");
      console.log(e);
    },
  });
}

function logout() {
  sessionStorage.removeItem("user");
  switchToRegLogin();
}
