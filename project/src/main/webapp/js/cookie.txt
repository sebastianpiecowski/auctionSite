function getToken() {
	var token = getCookie("token")
	return token;
}

function isTokenExists() {
	var token = getCookie("token");
	if (token == "") {
		return false;
	}
	else {
		return true;
	}
}

function saveToken(token) {
    document.cookie = "token=" + token;
}

function clearToken() {
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
}

function getCookie(cookieName) {
	var name = cookieName + "=";
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