function load() {
    var URL = "api/person/all";

    var row = document.getElementsByTagName('tbody')[0];
    row.parentNode.removeChild(row);

    $.getJSON(URL, function (data) {
        var items = [];
        $.each(data, function (key, val) {
            items.push("<tr>");
            items.push("<td id=''" + key + "''>" + val.id + "</td>");
            items.push("<td id=''" + key + "''>" + val.name + "</td>");
            items.push("<td id=''" + key + "''>" + JSON.stringify(val.cars)+ "</td>");
            items.push("</tr>");
        });

        $("<tbody/>", {html: items.join("")}).appendTo("table");
    });
}

$(document).ready(function () {
    $('#findButton').click(function () {
        load();
    });
});



