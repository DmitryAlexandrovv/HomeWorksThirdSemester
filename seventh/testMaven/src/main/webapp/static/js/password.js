document.addEventListener("DOMContentLoaded", function () {
    let password = document.querySelector("#inputPassword"),
        passwordConf = document.querySelector("#inputPasswordConf");

    passwordConf.addEventListener("input",() => {
        checkPassword(password, passwordConf);
    });

    password.addEventListener("input", () => {
        checkPassword(password, passwordConf);
    });

    function checkPassword(password, passwordConf) {
        let passwrodValue = password.value,
            passwordConfValue = passwordConf.value;

        if(passwrodValue != passwordConfValue){
            passwordConf.style.boxShadow = "0 0 0 0.2rem red";
        } else {
            passwordConf.style.boxShadow = "none";
        }
    }
});