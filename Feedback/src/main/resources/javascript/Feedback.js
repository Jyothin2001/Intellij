let fieldsChecks = {

	    'name' : false,
	    "comments" : false,
	    "terms" :false,
	    "recommend" : false,
	     "service" : false,

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

    if(!flag){
        document.getElementById("submit").removeAttribute("disabled");
    }else{
        document.getElementById("submit").setAttribute("disabled","");
    }
}


function nameValidation()
{
 let element = document.getElementById("name1");
                let error = document.getElementById("errorname");
                let nameRegex = /^[A-Za-z]+$/;   <!--^[A-Za-z]+( [A-Za-z]+)*$--!>

                if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value))
                {
                    error.innerHTML = "";
                    fieldsChecks['name'] = true;
                }
                else
                {
                    error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 3 and less than 30.";
                    error.style.color = "red";
                    fieldsChecks['name'] = false;

                }
    validate();
}


//phone=text
//   function phoneValidation() {
//             let element = document.getElementById("phone");
//             let error = document.getElementById("errorphone");
//
//             // Regular expression pattern for validating a 10-digit mobile number
//             let mobileRegex = /^\d{10}$/;
//
//             // Check if the mobile number is valid
//             if (mobileRegex.test(element.value)) {
//
//                 // Mobile number is valid
//                 error.innerHTML = "";
//                 fieldsChecks["phone"] = true;
//             } else {
//                 // Mobile number is invalid
//
//                 error.innerHTML = "Invalid mobile number. It should be exactly 10 digits.";
//                 error.style.color = "red";
//                 fieldsChecks["phone"] = false;
//             }
//             validate();
//         }

//checkbox
function termsValidation()
{
 let element = document.getElementById("terms");
                let error = document.getElementById("errorterms");

               if(element.checked)
               {
               error.innerHTML="";
               //id name
               fieldsChecks["terms"] = true;

               }
               else
               {
               error.innerHTML = "Please confirm";
                                   error.style.color = "red";
                                   fieldsChecks["terms"] = false;

               }
    validate();
}

//select=dropdown
   function serviceValidation()
   {
               let element = document.getElementById("service");
              let error = document.getElementById("errorservice");

                              if (element.value.length!=0))
                              {
                                  error.innerHTML = "";
                                  fieldsChecks["service"] = true;
                              } else {

                                  error.innerHTML = "Please select Service";
                                  error.style.color = "red";
                                  fieldsChecks["service"] = false;
                              }
                              validate();
                          }


                          //radio box
                  function recommendValidation()
                      	{
                      		 let error = document.getElementById("errortrecommend");
                      		 //document.give form a name
                      		 if(document.recommend.recommend[0].checked==false && document.recommend.recommend[1].checked==false){
                      		 error.innerHTML = "Please select recommend";
                      		 error.style.color="red"
                      		 //for radio use name attribute
                      		 fieldsChecks["recommend"] = false;

                      	}
                      		 else
                      			 {
                      			    error.innerHTML = "";
                      		        fieldsChecks["recommend"] = true;
                      			 }

                      	            validate()
                      			 }


              //textArea
              function commentsValidation()
              {
                              let element = document.getElementById("comments");
                              let error = document.getElementById("errorcomments");
                              let nameRegex = /^[A-Za-z]+$/;

                              if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value)) {
                                  error.innerHTML = "";
                                  fieldsChecks["service"] = true;
                              } else {
                                  error.innerHTML = " Service should be alphabetic characters only and length should be greater than 3 and less than 30.";
                                  error.style.color = "red";
                                  fieldsChecks["service"] = false;

                  }
                  recommendValidation();
                  validate();
              }


1