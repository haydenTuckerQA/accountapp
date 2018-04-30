$('#registration_form').submit( function() {
	var json = { username: $('#username').val(), password: $('#password').val(), firstName: $('#firstName').val(), lastName: $('#lastName').val(), accountNumber: $('#accountNo').val(), type: "USER"};
    $.ajax({
    	type: 'POST',
        url: 'http://localhost:8080/accountapp/api/account/create',
        data: JSON.stringify(json),
        contentType: 'application/json',
        async: false,
        success: function(msg) {
            alert(msg.message);
			$(location).attr('href',"index.html");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert(XMLHttpRequest.responseText);
			alert(textStatus);
			alert(errorThrown);
		}
    });
 	return false;
});