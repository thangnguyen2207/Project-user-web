function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function addToCart(proId) {
	let cart = getCookie("cart");
	if (proId != "" && proId != null) {
		if (cart == "") {
			setCookie("cart", proId, 365);
		} else if (!cart.includes(proId)) {
			setCookie("cart", cart + "-" + proId, 365);
		}
	}
	$("#cart-success").removeClass("d-none");
	updateCartIndex();
	setTimeout(function () {
		$("#cart-success").addClass("d-none");
	}, 3000)
}

function addToWishlist(proId) {
	let favorite = getCookie("favorite");
	if (proId != "" && proId != null) {
		if (favorite == "") {
			setCookie("favorite", proId, 365);
		} else if (!favorite.includes(proId)) {
			setCookie("favorite", favorite + "-" + proId, 365);
		}
	}
	$("#wishlist-success").removeClass("d-none");
	setTimeout(function () {
		$("#wishlist-success").addClass("d-none");
	}, 3000)
}

function updateCartIndex() {
	let cart = getCookie("cart");
	$("#cart-count").text("[" + cart.split("-").length + "]");
	
}