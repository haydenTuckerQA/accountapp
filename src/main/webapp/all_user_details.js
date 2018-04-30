$( window ).on('load', function() { 
    $.ajax({
    	type: 'GET',
        url: 'http://localhost:8080/accountapp/api/account/get',
        contentType: 'application/json',
        dataType: "json",
        async: false,
        success: function(data) {
            if (data.hasOwnProperty('message')) {
                alert(data.message);
            } else {
            	$.each(data, function(i, item) {
            		var row = '<tr><th id="username">' + data[i].username
	                	+ '</th><td><p id="accountNo">' + data[i].accountNumber
	                	+ '</p></td><td><p id="firstName">' + data[i].firstName
	                	+ '</p></td><td><p id="lastName">' + data[i].lastName
	                	+ '</p></td><td><p id="type">' + data[i].type;

	                if (data[i].type === "USER") {
	                	row += '</p></td><td> <button class="btn btn-primary my-2 my-lg-0 editButton">Edit</button>' +
	                		'<button class="btn btn-danger my-2 my-lg-0 deleteButton">Delete</button></td></tr>';
	                } else {
	                	row += '</p></td><td><button class="btn btn-danger my-2 my-lg-0 deleteButton">Delete</button></td></tr>';
	                }

	                

    				$('#table > tbody:last-child').append(row);
    			});

            	$("#table").on('click','.editButton',function(){
			         var currentRow=$(this).closest("tr"); 
			         
			         var accNo=currentRow.find("td:eq(0)").text();
			         var first=currentRow.find("td:eq(1)").text();
			         var last=currentRow.find("td:eq(2)").text();

			         currentRow.find("td:eq(0)").html("<input value='" + accNo + "'/>");
			         currentRow.find("td:eq(1)").html("<input value='" + first + "'/>");
			         currentRow.find("td:eq(2)").html("<input value='" + last + "'/>");
			         currentRow.find("td:eq(4)").html('<button class="btn btn-primary my-2 my-lg-0 updateButton">Update</button>' +
	                		'<button class="btn btn-danger my-2 my-lg-0 deleteButton">Delete</button>');

			         $("#table").on('click','.updateButton',function(){
				         var currentRow=$(this).closest("tr"); 
				         
				         var username=currentRow.find("th:eq(0)").text();

				         var accNo=currentRow.find("input:eq(0)").val();
				         var first=currentRow.find("input:eq(1)").val();
				         var last=currentRow.find("input:eq(2)").val();

				         var type=currentRow.find("td:eq(3)").text();

						var json = { username: username, password: "", firstName: first, lastName: last, accountNumber: accNo, type: type};
					    $.ajax({
					    	type: 'PUT',
					        url: 'http://localhost:8080/accountapp/api/account/update/' + username,
					        data: JSON.stringify(json),
					        contentType: 'application/json',
					        dataType: "json",
					        async: false,
					        success: function(data) {
								alert(data.message);
								$(location).attr('href',"all_user_details.html");
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

			    $("#table").on('click','.deleteButton',function(){
			         var currentRow=$(this).closest("tr"); 
			         
			         var username=currentRow.find("th:eq(0)").text(); // get current row 1st TD value
			         
			         $.ajax({
					    	type: 'DELETE',
					        url: 'http://localhost:8080/accountapp/api/account/delete/' + username,
					        contentType: 'application/json',
					        dataType: "json",
					        async: false,
					        success: function(data) {
					            alert(data.message);
					            if (Cookies.get('accountappUsername') === username) {
					            	Cookies.remove("accountappUsername");
    								Cookies.remove("accountappType");
					            }
					            $(location).attr('href',"all_user_details.html");
					        },
					        error: function(XMLHttpRequest, textStatus, errorThrown) {
							    alert(XMLHttpRequest.responseText);
								alert(textStatus);
								alert(errorThrown);
							}
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