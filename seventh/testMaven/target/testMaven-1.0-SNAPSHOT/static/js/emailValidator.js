document.addEventListener("DOMContentLoaded", () => {
    let emailField = document.querySelector("#inputEmail");

    emailField.addEventListener("input", () => {
        if(emailField.value == ''){
            emailField.style.boxShadow = "0 0 0 0.2rem rgba(0,123,255,.25)";
        }
        else if(validateEmail(emailField.value)){
            emailField.style.boxShadow = "0 0 0 0.2rem rgba(0,123,255,.25)";
        } else {
            emailField.style.boxShadow = "0 0 0 0.2rem red";
        }
    });

    function validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }
});