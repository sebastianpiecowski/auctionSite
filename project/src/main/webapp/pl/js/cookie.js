function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function createBasic(userEmail, userPassword, role){
	document.cookie="Basic="+btoa(userEmail + ":" + userPassword)+";";
	document.cookie="role="+role+";";
}
function deleteAllCookies() {
 var c = document.cookie.split("; ");
 for (i in c) 
  document.cookie ="";
  document.cookie =/^[^=]+/.exec(c[i])[0]+"=;";    
  document.cookie =/^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT";    
}