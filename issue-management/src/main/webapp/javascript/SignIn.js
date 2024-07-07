let fieldsChecks =
{
        "email" : false,
	    "password" : false,

}

function validate()
{
    let flag = false;

    for(let [key, value] of Object.entries(fieldsChecks))
    {

        if(value === false)
        {
            flag = true;
            break;
        }
    }

    if(!flag)
    {
      document.getElementById("submit").removeAttribute("disabled");
    }
    else
    {
        document.getElementById("submit").setAttribute("disabled","");
    }
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

          validate();
        }

          function passwordValidation()
          {
              let element = document.getElementById("password");
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
              validate();
          }

