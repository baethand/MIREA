// Задача 1: Создание элемента уведомлений с обновлением каждые 3 секунды
let notificationCount = 0;

function generateRandomSentence() {
    const subjects = [
        "1",
        "2",
        "3",
        "4",
        "5",
    ];
    const actions = [
        "> 1 >",
        "> 2 >",
        "> 3 >",
    ];
    const objects = [
    "1",
    "2",
    "3",
    ];

    const randomSubject =
        subjects[Math.floor(Math.random() * subjects.length)];
    const randomAction =
        actions[Math.floor(Math.random() * actions.length)];
    const randomObject =
        objects[Math.floor(Math.random() * objects.length)];

    return `${randomSubject} ${randomAction} ${randomObject}.`;
}

function showNotification(content) {
    notificationCount++;
    const notificationElement = document.getElementById("notification");
    const randomSentence = generateRandomSentence();
    notificationElement.style.display = "block";
    notificationElement.innerHTML = `Оповещение ${notificationCount}: ${randomSentence}`;

    // Добавляем уведомление в контейнер для просмотра
    const notificationsList = document.getElementById("notificationsList");
    const listItem = document.createElement("li");
    listItem.textContent = `Оповещение ${notificationCount}: ${randomSentence}`;
    notificationsList.appendChild(listItem);

    setTimeout(() => {
        notificationElement.style.display = "none";
    }, 3000);
}

// Задача 2: Остановка счетчика на 10 секунд при нажатии на кнопку уведомлений
function stopNotificationCounter() {
    clearInterval(notificationInterval);
    setTimeout(() => {
        notificationInterval = setInterval(() => {
        showNotification(generateRandomSentence());
        }, 3000);
    }, 10000);
}

let notificationInterval = setInterval(() => {
    showNotification(generateRandomSentence());
}, 6000);

// Задача 3: Интерфейс для создания списка
function createList() {
    const list = document.getElementById("myList");

    while (true) {
        const listItemContent = prompt("Введите содержимое списка:");
        if (listItemContent === null || listItemContent === "") {
            break;
        }

        // Создаем элемент <li> и добавляем его к <ul>
        const liElement = document.createElement("li");
        // Используем textContent, чтобы обработать HTML-теги как текст
        liElement.textContent = listItemContent;
        list.appendChild(liElement);
    }
}

// Задача 4: Показать/скрыть контейнер с уведомлениями
function toggleNotifications() {
    const notificationsContainer = document.getElementById(
        "notificationsContainer"
    );
    notificationsContainer.style.display =
    notificationsContainer.style.display === "none" ? "block" : "none";
}