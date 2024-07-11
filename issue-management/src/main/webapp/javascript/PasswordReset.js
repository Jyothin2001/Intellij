let fieldsChecks = {
    "email": false,
    "oldPassword": false,
    "newPassword": false,
    "confirmPassword": false
};

function validateAndEnableSubmit() {
    let allFieldsValid = Object.values(fieldsChecks).every(value => value);
    let submitButton = document.getElementById("submit");

    if (allFieldsValid) {
        submitButton.removeAttribute("disabled");
    } else {
        submitButton.setAttribute("disabled", "true");
    }
}

function emailValidation() {
    console.log("emailValidation")
    let email = document.getElementById("email");
    let error = document.getElementById("emailError");
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (emailRegex.test(email.value)) {
        error.innerHTML = "";
        fieldsChecks["email"] = true;
    } else {
        error.innerHTML = "Please enter a valid email address.";
        error.style.color = "red";
        fieldsChecks["email"] = false;
    }

    validateAndEnableSubmit();
}

function emailValidation() {
          let element = document.getElementById("email");
          let error = document.getElementById("emailError");

          let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
          let trimmedValue = element.value.trim();

          if (trimmedValue === "") {
            error.innerHTML = "Please enter an email address.";
            error.style.color = "red";
            fieldsChecks["email"] = false;
          } else if (!emailRegex.test(trimmedValue)) {
            error.innerHTML = "Invalid hh email address.";
            error.style.color = "red";
            fieldsChecks["email"] = false;
          } else {
            error.innerHTML = "";
            fieldsChecks["email"] = true;
          }

          validateAndEnableSubmit();
        }


function oldPasswordValidation() {
    console.log("oldPasswordValidation")
    let oldPassword = document.getElementById("oldPassword");
    let error = document.getElementById("oldPasswordError");
    //let oldPasswordRegex =!@#$%^&*()_+[]{}|;:,.<>?
    //let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&<>])[A-Za-z\d@$!%*?&<>]{10,}$/;
    //let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;
    let newPasswordRegex = [A-Za-z0-9];




    if (oldPasswordRegex.test(oldPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["oldPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["oldPassword"] = false;
    }

    validateAndEnableSubmit();
}

 function passwordValidation()
            {
                let element = document.getElementById("oldPassword");
                let error = document.getElementById("passwordError");

               if(element.value.length != "0")
               {
                    error.innerHTML = "";
                    fieldsChecks["password"] = true;
                }
                else
                {
                    error.innerHTML = "please Enter password";
                    error.style.color="red"
                    fieldsChecks["password"] = false;


                }
                validateAndEnableSubmit();
            }


function newPasswordValidation() {
    console.log("newPasswordValidation")
    let newPassword = document.getElementById("newPassword");
    let error = document.getElementById("newPasswordError");
//    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&<>])[A-Za-z\d@$!%*?&<>]{10,}$/;

    //let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;
    //let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$/;
    let newPasswordRegex = [A-Za-z0-9];


    if (newPasswordRegex.test(newPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["newPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["newPassword"] = false;
    }

    confirmPasswordValidation();
    validateAndEnableSubmit();
}

function confirmPasswordValidation() {
    let newPassword = document.getElementById("newPassword");
    let confirmPassword = document.getElementById("confirmPassword");
    let error = document.getElementById("confirmPasswordError");

    if (confirmPassword.value === newPassword.value && fieldsChecks["newPassword"]) {
        error.innerHTML = "";
        fieldsChecks["confirmPassword"] = true;
    } else {
        error.innerHTML = "Passwords do not match.";
        error.style.color = "red";
        fieldsChecks["confirmPassword"] = false;
    }

    validateAndEnableSubmit();
}


//let oldPasswordRegex =!@#$%^&*()_+[]{}|;:,.<>?
    //let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&<>])[A-Za-z\d@$!%*?&<>]{10,}$/;
    //let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;



    // let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&<>])[A-Za-z\d@$!%*?&<>]{10,}$/;

        //let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;
        //let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$/;
