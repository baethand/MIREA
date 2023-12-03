// Function to check if an object is empty
function isEmpty(obj) {
    for (let key in obj) {
      if (obj.hasOwnProperty(key)) {
        return false;
      }
    }
    return true;
}
  
  // Function to generate a random alphanumeric captcha value
  function generateAlphanumericCaptcha() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
    let captchaValue = '';
    for (let i = 0; i < 6; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      captchaValue += characters.charAt(randomIndex);
    }
    return captchaValue;
}
  
  // Function to generate a numerical captcha (N + M)
  function generateNumericalCaptcha() {
    const num1 = Math.floor(Math.random() * 10);
    const num2 = Math.floor(Math.random() * 10);
    return { num1, num2, result: num1 + num2 };
}
  
  // Function to initialize the captcha
  function initializeCaptcha() {
    const alphanumericCaptcha = generateAlphanumericCaptcha();
    document.getElementById('captchaValue').innerText = alphanumericCaptcha;
    document.getElementById('captchaForm').setAttribute('data-captcha-type', 'alphanumeric');
}
  
  // Function to switch to numerical captcha
  function switchToNumericalCaptcha() {
    const numericalCaptcha = generateNumericalCaptcha();
    document.getElementById('captchaValue').innerText = `${numericalCaptcha.num1} + ${numericalCaptcha.num2} = ?`;
    document.getElementById('captchaForm').setAttribute('data-captcha-type', 'numerical');
    document.getElementById('captchaForm').setAttribute('data-correct-sum', numericalCaptcha.result);
}
  
  // Function to validate the captcha
  function validateCaptcha() {
    const input = document.getElementById('captchaInput').value;
    const captchaType = document.getElementById('captchaForm').getAttribute('data-captcha-type');
  
    if (isEmpty(input)) {
      document.getElementById('message').innerText = 'Пожалуйста, введите капчу.';
    } else if (captchaType === 'alphanumeric') {
      const captchaValue = document.getElementById('captchaValue').innerText;
      if (input === captchaValue) {
        document.getElementById('message').innerText = 'Капча введена правильно!';
      } else {
        document.getElementById('message').innerText = 'Капча введена неправильно. Пожалуйста, решите пример.';
        switchToNumericalCaptcha(); // Switch to numerical captcha
      }
    } else if (captchaType === 'numerical') {
      const correctSum = parseInt(document.getElementById('captchaForm').getAttribute('data-correct-sum'), 10);
      if (parseInt(input, 10) === correctSum) {
        document.getElementById('message').innerText = 'Капча решена верно!';
      } else {
        document.getElementById('message').innerText = 'Капча введена неправильно. Пожалуйста, попробуйте еще раз.';
        switchToNumericalCaptcha(); // Retry with a new numerical captcha
      }
    }
}
  
  // Initialize the captcha when the page loads
  initializeCaptcha();
  

  ////Task3///

  // Function to truncate text if it exceeds a specified maximum length
function truncate(str, maxlength) {
    if (str.length <= maxlength) {
      return str; // No truncation needed
    } else {
      return str.slice(0, maxlength - 3) + '...'; // Truncate and add "..."
    }
  }
  
  // Update the text content of the cards with truncated text
  document.getElementById('text1').textContent = truncate(document.getElementById('text1').textContent, 50);
  document.getElementById('text2').textContent = truncate(document.getElementById('text2').textContent, 50);

