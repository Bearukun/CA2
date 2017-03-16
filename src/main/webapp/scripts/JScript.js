var btn = document.getElementById("btn");
var tbody = document.getElementById("bodyT");

var people;
var fetchedPerson;


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


function getHobbies(hobbies){
    var tempString = "";
    for(var x in hobbies){
        tempString += "<b>" + hobbies[x].name + "\n  </b><br>"; 
        tempString += hobbies[x].description + "\n <br>"; 
    }
    return tempString;
}
function getPhones(phoneNumbers){
    
    var tempString = "";
    
    for(var x in phoneNumbers){
        
        tempString += "<b>" + phoneNumbers[x].description + "\n </b><br>"; 
        tempString += phoneNumbers[x].number + "\n <br>"; 
        
    }
    
    return tempString;
    
}
function addPerson() {
    var fName = document.getElementById("fName");
    var lName = document.getElementById("lName");
    var phone = document.getElementById("phone");
    var url = "http://localhost:8084/CA2/api/Person";
    var conf = {method: 'POST',
        headers:
                {
                    'Accept': 'application/JSON',
                    'Content-Type': 'application/JSON'
                },

        body: JSON.stringify({

            fName: fName.value.toString(),
            lName: lName.value.toString(),
            phone: phone.value.toString()

        })
    };
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();
    }).then(function (text) {

        fetchPeople();
    });
};
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