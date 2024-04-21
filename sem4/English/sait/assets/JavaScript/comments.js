// Функция для добавления комментария
function addComment() {
    var name = document.getElementById("name").value;
    var comment = document.getElementById("comment").value;
  
    var commentList = document.getElementById("comment-list");
  
    var newComment = document.createElement("div");
    newComment.classList.add("comment");
    newComment.innerHTML = "<strong>" + name + "</strong>: " + comment;
  
    commentList.appendChild(newComment);
  
    document.getElementById("name").value = "";
    document.getElementById("comment").value = "";
  }
  
  // Обработчик отправки формы
  document.getElementById("comment-form").addEventListener("submit", function (e) {
    e.preventDefault();
    addComment();
  });