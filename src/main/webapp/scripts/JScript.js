var btn = document.getElementById("btn");
var tbody = document.getElementById("bodyT");

var people;
var phoneP;



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

        // For loop test to number + desc. in the console
        for (var i = 0; i < people.phones.length; i++) {
            console.log(people.phones[i].id);
            console.log(people.phones[i].number);
            console.log(people.phones[i].description);
        }

        // For loop test to hobbies + desc. in the console
        for (var i = 0; i < people.hobbies.length; i++) {
            console.log(people.hobbies[i].id);
            console.log(people.hobbies[i].name);
            console.log(people.hobbies[i].description);


        }
        return  "<tr>" +
                "<td>" + people.id + "</td>" +
                "<td>" + people.firstName + "</td>" +
                "<td>" + people.lastName + "</td>" +
                "<td>" + people.email + "</td>" +
                "<td>" + people.hobbies + "</td>" +
                "<td>" + people.address.street + "<br>"
                + people.address.additionalInfo + "<br>"
                + people.address.cityInfo.zipCode + "<br>"
                + people.address.cityInfo.city + "</td>" +
                "<td>" +people.phones+ "</td>" +
                "<td>" + "<a href= \"\" onclick=\"removePerson(" + people.id + ");return false;\">delete</a>" +
                " \\ " + "<a href= \"\" data-toggle=\"modal\" data-target=\"#myEditModal\" onclick=\"fetchPerson(" + people.id + ");return false;\">edit</a>" + "</td>" +
                "</tr>";

    });
    return lis.join("");
}
;
fetchPeople();


function phoneConverter(arr) {

    var parsedPhone = JSON.parse(arr);
    var phoneLis = parsedPhone.map(function (phoneP) {
        var phoneString;
        var phoneNumber;

        console.log("PHONE CONVERTER!");
        for (var i = 0; i < phoneP.phones.length; i++) {

            phoneString += phoneP.phones[i];
            console.log(phoneP.phones[i].description);
            console.log(phoneP.phones[i].number);

        }
        console.log("PHONE CONVERTER - DONE!");



    });
    return "hi";
}
;


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
}
;

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

    var url = "http://localhost:8084/CA2/api/Person" + id;
    var conf = {method: 'get'};
    var promise = fetch(url, conf);

    promise.then(function (response) {

        return response.text();

    }).then(function (text) {
        var person = JSON.parse(text);
        document.getElementById("eid").value = person.id;
        document.getElementById("efName").value = person.fName;
        document.getElementById("elName").value = person.lName;
        document.getElementById("ePhone").value = person.phone;

    });
}

function editPerson() {
    var id = document.getElementById("eid");
    var fName = document.getElementById("efName");
    var lName = document.getElementById("elName");
    var phone = document.getElementById("ePhone");

    var url = "http://localhost:8084/CA2/api/Person";
    var conf = {method: 'PUT',
        headers:
                {
                    'Accept': 'application/JSON',
                    'Content-Type': 'application/JSON'
                },

        body: JSON.stringify({
            id: id.value.toString(),
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


}