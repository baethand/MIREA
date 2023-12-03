
// Исходный массив товаров
let shoppingCart = [];

function renderCart() {
    const cartElement = document.getElementById('cart');
    cartElement.innerHTML = ''; // Очищаем содержимое корзины

    // Выводим элементы корзины
    shoppingCart.forEach(item => {
        const itemElement = document.createElement('div');
        itemElement.textContent = `УНИК-токен: ${item.name}, Количество: ${item.quantity}, Цена: ${item.price}`;
        cartElement.appendChild(itemElement);
    });
}

function addItem() {
    const newItem = {
        name: `${shoppingCart.length + 1}`,
        quantity: 1,
        price: Math.floor(Math.random() * 100) + 1 // Генерация случайной стоимости
    };

    shoppingCart.push(newItem);
    renderCart();
}

function clearCart() {
    shoppingCart = [];
    renderCart();
}

function increaseQuantity() {
    if (shoppingCart.length > 0) {
        // Увеличиваем количество последнего товара
        shoppingCart[shoppingCart.length - 1].quantity++;
        renderCart();
    }
}

function removeRandomItem() {
    if (shoppingCart.length > 0) {
        // Удаляем случайный элемент
        const randomIndex = Math.floor(Math.random() * shoppingCart.length);
        shoppingCart.splice(randomIndex, 1);
        renderCart();
    }
}

function filterItems() {
    const a = parseInt(prompt('Введите значение a --->'));
    const b = parseInt(prompt('Введите значение b --->'));

    // Фильтруем элементы
    const filteredItems = shoppingCart.filter(item => item.price >= a && item.price <= b);

    // Выводим результат
    alert(JSON.stringify(filteredItems));
}

function sortItems(ascending) {
    // Копируем массив для избежания изменения оригинала
    const sortedItems = [...shoppingCart];

    // Сортировка по цене
    sortedItems.sort((a, b) => (ascending ? a.price - b.price : b.price - a.price));

    // Выводим результат
    alert(JSON.stringify(sortedItems));
}

// Инициализация
renderCart();