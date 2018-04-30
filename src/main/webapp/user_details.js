$( window ).on('load', function() { 
    $.ajax({
    	type: 'GET',
        url: 'http://localhost:8080/accountapp/api/account/get/' + Cookies.get('accountappUsername'),
        contentType: 'application/json',
        dataType: "json",
        async: false,
        success: function(data) {
            if(data.hasOwnProperty('message')) {
                alert(data.message);
            } else {
                $('#table > tbody:last-child').append('<tr><th id="username">' + data.username
                	+ '</th><td><p id="accountNo">' + data.accountNumber
                	+ '</p></td><td><p id="firstName">' + data.firstName
                	+ '</p></td><td><p id="lastName">' + data.lastName
                	+ '</p></td><td> <button class="btn btn-primary my-2 my-lg-0" id="edit">Edit</button>'
                	+ '<button class="btn btn-danger my-2 my-lg-0" id="delete" onclick="window.location=\'index.html\';">Delete</button></td></tr>');

                $('#delete').click( function() {
				    $.ajax({
					    	type: 'DELETE',
					        url: 'http://localhost:8080/accountapp/api/account/delete/' + Cookies.get('accountappUsername'),
					        contentType: 'application/json',
					        dataType: "json",
					        async: false,
					        success: function(data) {
					            alert(data.message);
					            Cookies.remove("accountappUsername");
    							Cookies.remove("accountappType");
					            $(location).attr('href',"index.html");
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
							    alert(XMLHttpRequest.responseText);
								alert(textStatus);
								alert(errorThrown);
							}
					    });
				});

				$('#edit').click( function() {
				    var accNo = $('#accountNo').text();
				    var first = $('#firstName').text();
				    var last = $('#lastName').text();

				    $("#accountNo").replaceWith("<input id='newAccNo' value='" + accNo + "'/>");
				    $("#firstName").replaceWith("<input id='newFirst'value='" + first + "'/>");
				    $("#lastName").replaceWith("<input id='newLast' value='" + last + "'/>");
				    $("#edit").replaceWith('<button class="btn btn-primary my-2 my-lg-0" id="update">Update</button>');

				    $('#update').click( function() {
				    	var accNo = $("#newAccNo").val();
						var first = $("#newFirst").val();
						var last = $("#newLast").val();
				    	var json = { username: Cookies.get('accountappUsername'), password: "", firstName: first, lastName: last, accountNumber: accNo, type: "USER"};
					    $.ajax({
					    	type: 'PUT',
					        url: 'http://localhost:8080/accountapp/api/account/update/' + Cookies.get('accountappUsername'),
					        data: JSON.stringify(json),
					        contentType: 'application/json',
					        dataType: "json",
					        async: false,
					        success: function(data) {
								alert(data.message);
								$(location).attr('href',"user_details.html");
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
							    alert(XMLHttpRequest.responseText);
								alert(textStatus);
								alert(errorThrown);
								$(location).attr('href',"user_details.html");
							}
					    });
					});
				});
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
		    alert(XMLHttpRequest.responseText);
			alert(textStatus);
			alert(errorThrown);
		}
    });
});

$( document ).ready(function() {
    $('#resetPassword').submit( function() {
    	var t = document.getElementById('table');
		var accNo = $(t.rows[1].cells[1]).text();
		var first = $(t.rows[1].cells[2]).text();
		var last = $(t.rows[1].cells[3]).text();
    	var json = { username: Cookies.get('accountappUsername'), password: $('#newPassword').val(), firstName: first, lastName: last, accountNumber: accNo, type: "USER"};
	    $.ajax({
	    	type: 'PUT',
	        url: 'http://localhost:8080/accountapp/api/account/update/' + Cookies.get('accountappUsername') + '/' + $('#oldPassword').val(),
	        data: JSON.stringify(json),
	        contentType: 'application/json',
	        dataType: "json",
	        async: false,
	        success: function(data) {
				alert(data.message);
				$('#exampleModalCenter').modal('toggle');
				$('#resetPassword').reset();
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown) {
			    alert(XMLHttpRequest.responseText);
				alert(textStatus);
				alert(errorThrown);
			}
	    });
	 	return false;
	});
});