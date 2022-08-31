function getProductItems(keyword, startItem, size) {
	$("#proLoader").removeClass("d-none");
	$("#showBtn").attr("disabled", "disabled");
	$("#showBtn").text("Đang xử lý...");
	$.post("more-product/" + keyword + "&" + startItem + "&" + size).done(function (fragment) {
		$("#proLoader").addClass("d-none");
		$("#product-list").append(fragment);
	});
	$.post("end-of-content/" + keyword + "&" + (startItem + size)).done(function (result) {
		if (result.length < 1) {
			$("#showBtn").remove();
			$("#end").removeClass("d-none");
		} else {
			$("#showBtn").removeAttr("disabled");
			$("#showBtn").text("Xem thêm");
		}
	})
}

function getWishlistItems() {
	let ids = getCookie("favorite");
	if (ids != "") {
		$.post("get-wishlist-item/" + ids).done(function (fragment) {
			console.log(fragment);
			$("#product-list").append(fragment);
			emptyProductList("product-list");
		})
	}
}

function removeWishlistItems(id) {
	let ids = getCookie("favorite");
	
	let newValue = "";
	if (id != "" && id != null && ids != "") {
		newValue = ids.replace("-" + id, "");
		newValue = newValue.replace(id + "-", "");
		newValue = newValue.replace(id, "");
	}
	setCookie("favorite", newValue, 365);
	if ($('#' + id) != undefined) {
		$("#" + id).remove();
	}
	emptyProductList("product-list");
}


function getCartItems() {
	let ids = getCookie("cart");
	if (ids != "") {
		$.post("get-cart-item/" + ids).done(function (fragment) {
			$("#pro-list").append(fragment);
			emptyProductList("pro-list");
		})
	}
	updateCartIndex();
}

function removeCartItems(id) {
	let ids = getCookie("cart");
	
	let newValue = "";
	if (id != "" && id != null && ids != "") {
		newValue = ids.replace("-" + id, "");
		newValue = newValue.replace(id + "-", "");
		newValue = newValue.replace(id, "");
	}
	setCookie("cart", newValue, 365);
	if ($('#' + id) != undefined) {
		$("#" + id).remove();
	}
	updateCartIndex();
	emptyProductList("pro-list");
}

function emptyProductList(id) {
	if ($("#" + id).children().length == 0) {
		$("#empty-list").removeClass("d-none");
	} else {
		$("#empty-list").addClass("d-none");
	}
}