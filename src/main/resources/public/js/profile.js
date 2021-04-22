$(document).ready(function () {
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

    register(username, name, password, tel, email);

    return false;
  });
});

function login(username, password) {
  $.ajax({
    type: "POST",
    contentType: "application/json",
    url: "/api/user/login",
    data: JSON.stringify({ felhasznalonev: username, hash: password }),
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      console.log(JSON.stringify(data));
    },
    error: function (e) {
      console.log("login error:");
      console.log(JSON.stringify(e));
      $("#wrongLogin").css("display", "block");
    },
  });
}

function register(username, name, password, tel, email) {
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
    }),
    dataType: "json",
    cache: false,
    timeout: 600000,
    success: function (data) {
      let result = JSON.stringify(data);
      console.log(result);
      if (result == "false") {
        $("#wrongReg").css("display", "block");
      }
    },
    error: function (e) {
      console.log("register error:");
      console.log(JSON.stringify(e));
      $("#wrongReg").css("display", "block");
    },
  });
}
