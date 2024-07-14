let fieldsChecks = {
  "firstName": false,
  "lastName": false,
  "email": false,
  "contactNumber": false,
  "alternateContactNumber": false,
  "address": false
};

function validateAndEnableSubmit() {
  let flag = Object.values(fieldsChecks).includes(false);

  if (!flag) {
    document.getElementById("submit").removeAttribute("disabled");
  } else {
    document.getElementById("submit").setAttribute("disabled", "");
  }
}

// FirstName
function firstNameValidation() {
    let element = document.getElementById("firstName");
    let error = document.getElementById("firstNameError");
    let nameRegex = /^[A-Za-z ]+$/;
    console.log(element);

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

// LastName
function lastNameValidation() {
    let element = document.getElementById("lastName");
    let error = document.getElementById("lastNameError");
    let nameRegex = /^[A-Za-z ]+$/;

    if (element.value.length > 1 && element.value.length <= 20 && nameRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["lastName"] = true;
    } else {
        error.innerHTML = " jj Invalid name. Name should be alphabetic characters only and length should be greater than 1 and less than 20.";
        error.style.color = "red";
        fieldsChecks["lastName"] = false;
    }
    validateAndEnableSubmit();
}






// Contact Number
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



// Alternate Contact Number
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

// Address
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


//Ajax for number
//already wrote ajax in controller just make use of it here with same action(/validateNumber/)
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


//adding addEventListener() while edit user data for one field change(edit) the button should enable purpose
document.getElementById("firstName").addEventListener("blur", firstNameValidation);
document.getElementById("lastName").addEventListener("blur", lastNameValidation);
document.getElementById("contactNumber").addEventListener("blur", contactNumberValidation);
document.getElementById("alternateContactNumber").addEventListener("blur", alternateContactNumberValidation);
document.getElementById("address").addEventListener("blur", addressValidation);


function validateAndEnableSubmit() {
    let isValid = true;

    // Check each field validity
    for (let field in fieldsChecks) {
        if (!fieldsChecks[field]) {
            isValid = false;
            break;
        }
    }

    // Enable/disable submit button based on overall form validity
    document.getElementById("submit").disabled = !isValid;
    }