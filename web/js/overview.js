/**
 * Created by Thomas on 15/04/2017.
 */
var xHRObject = new XMLHttpRequest();
var xHRObjectStatus = new XMLHttpRequest();
var persons = document.getElementsByClassName("addFriend");
var statusi = document.getElementsByClassName("statusi");

function addFriendTableRow(id,firstname,lastname,role, status)
{
    var table = document.getElementById("friendtable");
    var tableTR = document.createElement("tr");

    var idTD = document.createElement("td");
    idTD.innerHTML = id;

    var firstNameTD = document.createElement("td");
    firstNameTD.innerHTML = firstname;

    var lastNameTD = document.createElement("td");
    lastNameTD.innerHTML = lastname;

    var roleTD = document.createElement("td");
    roleTD.innerHTML = role;

    var statusTD = document.createElement("td");
    statusTD.innerHTML = status;

    table.appendChild(tableTR);
    tableTR.appendChild(idTD);
    tableTR.appendChild(firstNameTD);
    tableTR.appendChild(lastNameTD);
    tableTR.appendChild(roleTD);
    tableTR.appendChild(statusTD);
}

function getDataForAddFriend () {
        if (xHRObject.status == 200) {
            if (xHRObject.readyState == 4) {
                var serverResponse = JSON.parse(xHRObject.responseText);
                addFriendTableRow(serverResponse.friend.id,serverResponse.friend.firstname,serverResponse.friend.lastname,serverResponse.friend.role, serverResponse.friend.status);
                /*
                 var quote = serverResponse.quote; // of je kan ook doen: serverResponse["quote"]

                 var quoteDiv = document.getElementById("quote");
                 var quoteParagraph = quoteDiv.childNodes[0];

                 if (quoteParagraph == null) {
                 quoteParagraph = document.createElement('p');
                 quoteParagraph.id = "quoteText";
                 var quoteText = document.createTextNode(quote);
                 quoteParagraph.appendChild(quoteText);
                 quoteDiv.appendChild(quoteParagraph);
                 }
                 else {
                 var quoteText = document.createTextNode(quote);
                 quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
                 quoteParagraph.appendChild(quoteText);
                 }*/
            }
        }
}



function doAddFriend()
{
    xHRObject.open("GET","Controller?action=asyncAddFriend&id=" + this.id,true);
    xHRObject.onreadystatechange = getDataForAddFriend;
    xHRObject.send(null);
}

function doChangeStatus() {
    xHRObject.open("GET","Con");

    xHRObject.send(null);
}

function addEventTo(persons) {
    for(var i = 0; i < persons.length; i++)
    {
        persons[i].onclick = doAddFriend;
    }
}

function addEventToStatus(stati) {
    for (var i =0; i< stati.length; i++)
    {
        stati[i].onclick = doChangeStatus;
    }

}


addEventTo(persons);
addEventToStatus(statusi)