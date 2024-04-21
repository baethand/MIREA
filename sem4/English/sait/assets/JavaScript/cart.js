const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');
const cartItemsList = document.getElementById('cart-items');

addToCartButtons.forEach(button => {
  button.addEventListener('click', () => {
    const productId = button.getAttribute('data-product-id');
    const productName = button.parentElement.querySelector('h2').innerText;

    const li = document.createElement('li');
    li.innerText = productName;
    cartItemsList.appendChild(li);
  });
});

const navLinks = document.querySelectorAll('nav ul li a');
const cartSidebar = document.querySelector('.cart-sidebar');

navLinks.forEach(link => {
  if (link.innerText === 'Корзина') {
    link.addEventListener('click', () => {
      cartSidebar.style.right = '0';
    });
  }
});

document.addEventListener('click', (e) => {
  if (!cartSidebar.contains(e.target) && e.target.innerText !== 'Корзина') {
    cartSidebar.style.right = '300px';
  }
});
