$( window ).on('load', function() { 
    if (!!Cookies.get('accountappUsername') || !!Cookies.get('accountappType')) {
        if(Cookies.get('accountappType') === 'USER') {
            $(location).attr('href',"user_home.html");
        } else if (Cookies.get('accountappType')  === 'ADMIN') {
            $(location).attr('href',"admin_home.html");
        }
    }
});

$('#login_form').submit( function() {
    $.ajax({
    	type: 'GET',
        url: 'http://localhost:8080/accountapp/api/account/login/' + $('#username').val() + '/' + $('#password').val(),
        contentType: 'application/json',
        dataType: "json",
        async: false,
        success: function(data) {
            if(data.hasOwnProperty('type')) {
                Cookies.set('accountappUsername',data.username);
                Cookies.set('accountappType',data.type);
                if(data.type == 'USER') {
                    $(location).attr('href',"user_home.html");
                } else if (data.type == 'ADMIN') {
                    $(location).attr('href',"admin_home.html");
                }
            } else {
                alert(data.message);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert(XMLHttpRequest.responseText);
			alert(textStatus);
			alert(errorThrown);
		}
    });
 	return false;
});