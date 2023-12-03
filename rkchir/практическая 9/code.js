var answer = prompt("Желаете пройти регистрацию на сайте?");

    if (answer.toLowerCase() === "да") {
        alert("Круто!");
    } else {
        alert("Окей");
    }



    let login = prompt("Введите логин:", "");

    if (login === "admin") {
    let password = prompt("Введите пароль:", "");

    if (password === "admin") {
    alert("Здравствуйте!");
    } else if (password === "" || password === null) {
    alert("Отменено");
    } else {
    alert("Неверный пароль");
    }
    } else if (login === "" || login === null) {
    alert("Отменено");
    } else {
    alert("Я вас не знаю");
    }



    function changeColor() {
  var button = document.getElementById("likeButton");
  button.classList.toggle("liked");
}
