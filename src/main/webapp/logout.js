$( window ).on('load', function() { 
    if (!Cookies.get('accountappUsername') || !Cookies.get('accountappType')) {
        $(location).attr('href',"index.html");
    }
});

$('#logout').click( function() {
    Cookies.remove("accountappUsername");
    Cookies.remove("accountappType");
});