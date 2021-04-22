$(document).ready(function () {
    test_request();
})

function test_request() {
    console.log("request sent");

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/user/address/all",
        // data: JSON.stringify(search),
        // dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
            let rows = data.map(x => `<p>${x.varos}, ${x.utca} ${x.hazszam}</p>`);
            $("#results").html("<h1>Sikeres hívás</h1>" + rows);
        },
        error: function (e) {
            console.log(e);
            $('#results').text("<h1>Sikertelen hívás:</h1><br>" + e.toString())
        }
    });
}