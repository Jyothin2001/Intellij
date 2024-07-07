let fieldsChecks = {
  "firstName": false,
  "lastName": false,
  "email": false,
  "contactNumber": false,
  "alternateContactNumber": false,
  "address": false,
  "agree": false
};

function validateAndEnableSubmit() {
  let flag = Object.values(fieldsChecks).includes(false);

  if (!flag) {
    document.getElementById("submit").removeAttribute("disabled");
  } else {
    document.getElementById("submit").setAttribute("disabled", "");
  }
}

function firstNameValidation() {
  let element = document.getElementById("firstName");
  let error = document.getElementById("firstNameError");
  let nameRegex = /^[A-Za-z ]+$/;

  if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["firstName"] = true;
  } else {
    error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 3 and less than 30.";
    error.style.color = "red";
    fieldsChecks["firstName"] = false;
  }
  validateAndEnableSubmit();
}

function lastNameValidation() {
  let element = document.getElementById("lastName");
  let error = document.getElementById("lastNameError");
  let nameRegex = /^[A-Za-z ]+$/;

  if (element.value.length >=1 && element.value.length <= 20 && nameRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["lastName"] = true;
  } else {
    error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 1 and less than 20.";
    error.style.color = "red";
    fieldsChecks["lastName"] = false;
  }
  validateAndEnableSubmit();
}

function emailValidation() {
  let element = document.getElementById("email");
  let error = document.getElementById("emailError");
  let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  if (emailRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["email"] = true;
    emailAjaxValidation();
  } else {
    error.innerHTML = "Invalid email address.";
    error.style.color = "red";
    fieldsChecks["email"] = false;
  }
  validateAndEnableSubmit();
}

function contactNumberValidation() {
  let element = document.getElementById("contactNumber");
  let error = document.getElementById("contactNumberError");
  let mobileRegex = /^\d{10}$/;

  if (mobileRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["contactNumber"] = true;
    numberAjaxValidation();
  } else {
    error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
    error.style.color = "red";
    fieldsChecks["contactNumber"] = false;
  }
  validateAndEnableSubmit();
}

function alternateContactNumberValidation() {
  let element = document.getElementById("alternateContactNumber");
  let error = document.getElementById("altContactNbrError");
  let mobileRegex = /^\d{10}$/;

  if (mobileRegex.test(element.value)) {
    error.innerHTML = "";
    fieldsChecks["alternateContactNumber"] = true;
  } else {
    error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
    error.style.color = "red";
    fieldsChecks["alternateContactNumber"] = false;
  }
  validateAndEnableSubmit();
}

function addressValidation() {
  let element = document.getElementById("address");
  let error = document.getElementById("addressError");

  if (element.value.length > 3 && element.value.length < 300) {
    error.innerHTML = "";
    fieldsChecks["address"] = true;
  } else {
    error.innerHTML = "Address should be greater than 3 and less than 300 characters.";
    error.style.color = "red";
    fieldsChecks["address"] = false;
  }
  validateAndEnableSubmit();
}

function agreeValidation() {
  let element = document.getElementById("agree");
  let error = document.getElementById("agreeError");

  if (element.checked) {
    error.innerHTML = "";
    fieldsChecks["agree"] = true;
  } else {
    error.innerHTML = "Please agree to the terms.";
    error.style.color = "red";
    fieldsChecks["agree"] = false;
  }
  validateAndEnableSubmit();
}

function emailAjaxValidation() {
  console.log("Validate email");
  let email = document.getElementById("email").value;
  let error = document.getElementById("emailError");

  if (email == "") {
    error.innerHTML = "Please Enter Valid email";
    fieldsChecks["email"] = false;
    validateAndEnableSubmit();
    return;
  }

  const request = new XMLHttpRequest();
  request.open("GET", "http://localhost:8080/issue-management/validateEmail/" + email);
  request.send();
  request.onload = function ()
  {
    let ref = this.responseText;
    error.innerHTML = ref;

    if (ref === "") {
      fieldsChecks["email"] = true;
    } else {
      fieldsChecks["email"] = false;
    }
    validateAndEnableSubmit();
  };
  request.onerror = function () {
    console.error("Request failed");
    error.innerHTML = "Validation failed. Please try again.";
    fieldsChecks["email"] = false;
    validateAndEnableSubmit();
  };
}

function numberAjaxValidation() {
  console.log("Validate contact number");
  let contactNumber = document.getElementById("contactNumber").value;
  let error = document.getElementById("contactNumberError");

  if (contactNumber == "") {
    error.innerHTML = "Please enter valid contactNumber";
    fieldsChecks["contactNumber"] = false;
    validateAndEnableSubmit();
    return;
  }

  const request = new XMLHttpRequest();
  request.open("GET", "http://localhost:8080/issue-management/validateNumber/" + contactNumber);
  request.send();
  request.onload = function () {
    let ref = this.responseText;
    error.innerHTML = ref;

    if (ref === "") {
      fieldsChecks["contactNumber"] = true;
    } else {
      fieldsChecks["contactNumber"] = false;
    }
    validateAndEnableSubmit();
  };
  request.onerror = function () {
    console.error("Request failed");
    error.innerHTML = "Validation failed. Please try again.";
    fieldsChecks["contactNumber"] = false;
    validateAndEnableSubmit();
  };
}