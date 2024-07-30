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
              error.innerHTML = "Please enter  an email address.";
              error.style.color = "red";
              console.log("not entered email");
              fieldsChecks["email"] = false;
            } else if (!emailRegex.test(trimmedValue)) {
              error.innerHTML = "Invalid email address.";
              error.style.color = "red";
               console.log(" invalid email");
              fieldsChecks["email"] = false;
            } else {
              error.innerHTML = "";
               console.log("crt email");
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
                    console.log("crt password");
                    fieldsChecks["password"] = true;
                }
                else
                {
                    error.innerHTML = "please Enter password";
                    error.style.color="red";
                    console.log("not crt password");
                    fieldsChecks["password"] = false;


                }
                validate();
            }


          function disableButton() {
              var accountLocked = document.getElementById("accountLocked").value;
              console.log(" disable button");
              if (accountLocked === "true") {
              console.log("inside disabled button()");
                  document.getElementById("signinsubmit").disabled = true;
              }
          }

          window.onload = disableButton;







