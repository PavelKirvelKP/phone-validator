$(document).ready(function () {
    $("#submit").click(async function () {
       const phoneNumber = $('#phone-number').val();
        $.ajax({
            url: `http://localhost:8088/countries?phone_number=${phoneNumber}`,
            type: 'GET',
            datatype: 'json',
        }).done(function (data) {
            let result = "";
            if (data.length > 0) {
                for (let i = 0; i < data.length; i++) {
                    result += data[i] + "<br>";
                }
            } else {
                result = "Couldn't find any countries for this phone number";
            }
            document.getElementById('country-group').innerHTML = result;
            $("#error-message").empty();
            $("#country-list").show();
            $("#country-group").show();
            $("#error").hide();
        }).fail(function (jqXHR) {
            $("#error-message").text(jqXHR.responseText);
            $("#county-name").empty();
            $("#country-list").hide();
            $("#error").show();
            $("#country-group").hide();
        })
    });

});