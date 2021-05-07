/**
 * 
 */
const hamburger = document.querySelector('.toggle');
const btnCloseModal = document.getElementById('btnCloseModal');

hamburger.addEventListener('click', () => {
	hamburger.classList.toggle('active');
});

/*
==============================================
		Modal Form ADD product to Cart 
=============================================*/

const modalBox = document.querySelector('.modal');
const btnsAddtoCart = document.querySelectorAll('.btn-cart');
const modalForm = document.getElementById('modalForm');

let actualProduct;
const tBQuantity = document.getElementById('quantity');


//Open Modal Form
btnsAddtoCart.forEach(btn => btn.addEventListener('click', (e) => {
	actualProduct = e.target.dataset.id;
	modalBox.classList.toggle('hidden');
}));

//Form to Add product info to localStorage
modalForm.addEventListener('submit', (e) => {
	e.preventDefault();
	if (actualProduct) {
		let item = {
			'productID': actualProduct,
			'quantity': parseInt(tBQuantity.value)
		}
		updateStorage(item);//Add product to LocalStorage
		updateCart(item.productID);
	}
});


//close Modal form
btnCloseModal.addEventListener('click', () => {
	actualProduct = null;
	tBQuantity.value = 1;
	modalBox.classList.toggle('hidden');
});

/*
==============================================
			Modal Cart Functions 
=============================================*/
const btnCloseCart = document.getElementById("btnCloseCart");
const cartBox = document.querySelector(".cart");
const shoppingList = document.getElementById('shopping-list');

//Show all items in the user cart 
function showCartItems() {
	shoppingList.innerHTML = '';
	getCart()//getting cart info as a promise
		.then(cart => {
			const fragment = document.createDocumentFragment();
			for (cartItem of cart.cartItems) {
				let itemDiv = document.createElement('div');
				itemDiv.classList.add('cart__item');

				let name = document.createElement('span');
				let quantity = document.createElement('b');
				let total = document.createElement('strong');
				let btnDelete = document.createElement('button');

				name.innerHTML = cartItem.product.name;
				quantity.innerHTML = cartItem.quantity;
				total.innerHTML = `$ ${cartItem.totalprice}`;
				btnDelete.setAttribute('data-id', cartItem.id);
				btnDelete.innerHTML = '&#10006';
				itemDiv.appendChild(name);
				itemDiv.appendChild(quantity);
				itemDiv.appendChild(total);
				itemDiv.appendChild(btnDelete);
				fragment.appendChild(itemDiv);
			}
			shoppingList.appendChild(fragment);
		})
		.catch(error => console.log(error));
}


btnCloseCart.addEventListener("click", () => {
	if (cartBox.classList.contains("cart-hide")) {
		cartBox.classList.toggle("cart-hide");
		btnCloseCart.style.background = "url(../img/icons/cancel.png)";
		btnCloseCart.style.backgroundSize = "cover";
		showCartItems();
	} else {
		cartBox.classList.toggle("cart-hide");
		btnCloseCart.style.background = "url(../img/icons/shopping-cart.png)";
		btnCloseCart.style.backgroundSize = "cover";
	}
});



/*
==============================================
			Local Storage API
=============================================*/

const updateStorage= (item) => {
	let itemInStorage = localStorage.getItem(item.productID);

	if (itemInStorage) {
		localStorage[item.productID] = +localStorage[item.productID] + item.quantity;
	} else {
		localStorage.setItem(item.productID, item.quantity);
	}
}

const updateCart = (productID)=> {
	let item = {
		'productID': productID,
		'quantity': parseInt(localStorage[productID])
	}
	
	let cart = {
		cartItems: []
	}
	
	cart.cartItems.push(item);
	localStorage.getItem('cartID') ? addItemToCart(item,localStorage['cartID']) : createCart(cart);
}


/*
==============================================
			API Consuming Service
=============================================*/

const url = window.location.protocol + "//" + window.location.host + "/api/cart/";


//creates a new cart
function createCart(cart) {
	fetch(url, {
		method: 'POST',
		body: JSON.stringify(cart),
		headers: {
			'Content-Type': 'application/json'
		}
	}).then(res => {
		if (res.status === 201) {
			res.text()
				.then(text => {
					localStorage.setItem('cartID', text);
				});
		}
	}).catch(error => console.error('Error:', error));
}

function addItemToCart(item, cartID) {

	fetch(`${url}${cartID}`, {
		method: 'POST',
		body: JSON.stringify(item),
		headers: {
			'Content-type': 'application/json'
		}
	}).then(res => {
		console.log(res);
	})
	console.log(`update cart and Stuff`);
}

async function getCart() {
	let cartID = localStorage.getItem('cartID');
	if (cartID != null) {
		let cart = await fetch(`${url}${cartID}`)
			.then(res => res.json())
			.then(json => {
				return json;
			});
		return cart;
	}
}








