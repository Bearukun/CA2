var btn = document.getElementById("btn");
var tbody = document.getElementById("bodyT");

var people;
var fetchedPerson;

//Function to fetch people from the database through the REST Api.
function fetchPeople() {

    var url = "http://localhost:8084/CA2/api/Person";
    var conf = {method: 'get'};
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();

    }).then(function (text) {


        document.getElementById("bodyT").innerHTML = listMaker(text);

    });


}

//Function to generate a formated list to pupulate the table on the website
function listMaker(arr) {

    var parsed = JSON.parse(arr);
    var lis = parsed.map(function (people) {

        return  "<tr>" +
                "<td>" + people.id + "</td>" +
                "<td>" + people.firstName + "</td>" +
                "<td>" + people.lastName + "</td>" +
                "<td>" + people.email + "</td>" +
                "<td>" + getHobbies(people.hobbies) + "</td>" +
                "<td>" + people.address.street + "<br>"
                + people.address.cityInfo.city  + "<br>"
                + people.address.cityInfo.zipCode + "<br>"
                + "<b><i>Additional info:</b></i><br>" + people.address.additionalInfo + "</td>" +
                "<td>" + getPhones(people.phones) + "</td>" +
                "<td>" + "<a href= \"\" onclick=\"removePerson(" + people.id + ");return false;\">Delete</a>" +
                " <br> " + "<a href= \"\" data-toggle=\"modal\" data-target=\"#myEditModal\" onclick=\"fetchPerson(" + people.id + ");return false;\">Edit</a>" + "</td>" +
                "</tr>";

    });
    return lis.join("");
}
;

fetchPeople();

//Function to extract hobbies from the hobbies object, and print as formatted list for the table. 
function getHobbies(hobbies){
    var tempString = "";
    for(var x in hobbies){
        tempString += "<b>" + hobbies[x].name + "\n  </b><br>"; 
        tempString += hobbies[x].description + "\n <br>"; 
    }
    return tempString;
}

//Function to extract phonenumbers from the phones object, and print as formatted list for the table. 
function getPhones(phoneNumbers){
    
    var tempString = "";
    
    for(var x in phoneNumbers){
        
        tempString += "<b>" + phoneNumbers[x].description + "\n </b><br>"; 
        tempString += phoneNumbers[x].number + "\n <br>"; 
        
    }
    
    return tempString;
    
}

//Function to add a person.
function addPerson() {
    var fName = document.getElementById("firstName");
    var lName = document.getElementById("lastName");
    var email = document.getElementById("email");
    var street = document.getElementById("street");
    var city = document.getElementById("city");
    var zipCode = document.getElementById("zipCode");
    var addInfo = document.getElementById("additionalInfo");
    
  
    
    var url = "http://localhost:8084/CA2/api/Person";
    var conf = {method: 'POST',
        headers:
                {
                    'Accept': 'application/JSON',
                    'Content-Type': 'application/JSON'
                },

        body: JSON.stringify({

            firstName: fName.value.toString(),
            lastName: lName.value.toString(),
            hobbies: [{}],
            email: email.value.toString(),
            phones: null,
            address: {
                street: street.value.toString(),
                additionalInfo: addInfo.value.toString(),
                
                cityInfo: {
                    
                    zipCode: parseInt(zipCode.value.toString()),
                    city: city.value.toString()
                
                }
                
            }
            
            

        })
    };
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();
    }).then(function (text) {

        fetchPeople();
    });
};

//Function to remove a person by id.
function removePerson(id) {

    var url = "http://localhost:8084/CA2/api/Person/" + id;
    var conf = {method: 'delete'};
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();

    }).then(function (text) {
        console.log(text);

        fetchPeople();
        //document.getElementById("bodyT").innerHTML = listMaker(text);

    });


}

//Function to fetch a person by id.
function fetchPerson(id) {

    var url = "http://localhost:8084/CA2/api/Person/" + id;
    var conf = {method: 'get'};
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();

    }).then(function (text) {
        var person = JSON.parse(text);
        fetchedPerson = person;
        document.getElementById("eid").value = person.id;
        document.getElementById("efirstName").value = person.firstName;
        document.getElementById("elastName").value = person.lastName;
        document.getElementById("eemail").value = person.email;
        document.getElementById("estreet").value = person.address.street;
        document.getElementById("ecity").value = person.address.cityInfo.city;
        document.getElementById("ezipCode").value = person.address.cityInfo.zipCode;
        document.getElementById("eadditionalInfo").value = person.address.additionalInfo;

    });
}

//Function to edit a person.
function editPerson() {
    
    fetchedPerson.firstName = document.getElementById("efirstName").value;
    fetchedPerson.lastName = document.getElementById("elastName").value;
    fetchedPerson.email = document.getElementById("eemail").value;
    fetchedPerson.address.street = document.getElementById("estreet").value;
    fetchedPerson.address.cityInfo.city = document.getElementById("ecity").value;
    fetchedPerson.address.cityInfo.zipCode = document.getElementById("ezipCode").value;
    fetchedPerson.address.additionalInfo = document.getElementById("eadditionalInfo").value;

    console.log(fetchedPerson.firstName);

    var url = "http://localhost:8084/CA2/api/Person";
    var conf = {method: 'PUT',
        headers:
                {
                    'Accept': 'application/JSON',
                    'Content-Type': 'application/JSON'
                },

        body: JSON.stringify(fetchedPerson)
    };
    
    
    
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();
    }).then(function (text) {

        fetchPeople();
    });


}