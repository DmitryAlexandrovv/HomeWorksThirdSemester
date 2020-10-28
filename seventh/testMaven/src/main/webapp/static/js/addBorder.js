document.addEventListener("DOMContentLoaded", () => {
    let fields = document.querySelectorAll(".form-control");

    fields.forEach((field) => {
        addBorder(field, "0 0 0 0.2rem rgba(0,123,255,.25)", "none", "#80bdff", "#ced4da")
    });


    function addBorder(field, shadoeFocus, shadowBlur, borderFocus, borderBlur) {
        field.addEventListener("blur", () => {
            field.style.boxShadow = shadowBlur;
            field.style.borderColor = borderBlur;
        })

        field.addEventListener("focus", () => {
            field.style.boxShadow = shadoeFocus;
            field.style.borderColor = borderFocus;
        });
    }
});