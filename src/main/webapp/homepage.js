$( window ).on('load', function() { 
    $("#welcome").html("Welcome " + Cookies.get('accountappUsername') + "!");
});