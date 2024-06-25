function checkPassword() {
    var password = document.getElementById("password").value;
    var passwordConfirm = document.getElementById("passwordConfirm").value;
    var msgElement = document.getElementById("msg");

    if (password !== passwordConfirm) {
        msgElement.innerHTML = "Mật khẩu không khớp.";
        return false;
    } else {
        msgElement.innerHTML = "";
        return true;
    }
}


function checkAgree() {
    var agreeTerms = document.getElementById("agreeTerms");
    var submitButton = document.getElementById("submit");

    if (agreeTerms.checked) {
        submitButton.style.display = "block";
    } else {
        submitButton.style.display = "none";
    }
}

