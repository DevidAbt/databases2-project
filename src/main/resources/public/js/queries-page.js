$(document).ready(function () {
  var userString = sessionStorage.getItem("user");
  if (!userString) {
    window.location.replace("./profilom.html");
  }
  var user = JSON.parse(userString);
  if (!user || !user.isAdmin) {
    window.location.replace("./index.html");
  }

});