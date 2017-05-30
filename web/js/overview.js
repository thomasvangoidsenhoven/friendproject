/**
 * Created by Thomas on 15/04/2017.
 */
var xHRObject = new XMLHttpRequest();
var xHRObjectStatus = new XMLHttpRequest();
var xHRObjectPull = new XMLHttpRequest();
var persons = document.getElementsByClassName("addFriend");
var statusi = document.getElementsByClassName("statusi");
var currentStatus = document.getElementById("currentStatus");

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

    var chatTD = document.createElement("td");

    chatTD.innerHTML = "<a href='#'  class='chatProc'> Chat ._. </a>";

    table.appendChild(tableTR);
    tableTR.appendChild(idTD);
    tableTR.appendChild(firstNameTD);
    tableTR.appendChild(lastNameTD);
    tableTR.appendChild(roleTD);
    tableTR.appendChild(statusTD);
    tableTR.appendChild(chatTD);
}

function getDataForAddFriend () {
        if (xHRObject.status == 200) {
            if (xHRObject.readyState == 4) {
                var serverResponse = JSON.parse(xHRObject.responseText);
                addFriendTableRow(serverResponse.friend.id,serverResponse.friend.firstName,serverResponse.friend.lastName,serverResponse.friend.role, serverResponse.friend.status);
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

function getDataForChangeStatus() {
    if(xHRObjectStatus.status == 200)
    {
        if(xHRObjectStatus.readyState ==4)
        {

            var serverResponse = JSON.parse(xHRObjectStatus.responseText);

            currentStatus.innerHTML = "Current status: " + serverResponse.status;

            var el = document.getElementById("status_"+serverResponse.userId);
            el.innerHTML = serverResponse.status;

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

    if(this.tagName=="BUTTON")
    {
        var el = document.getElementById("customStatus");

        xHRObjectStatus.open("GET","Controller?action=asyncChangeStatus&status="+el.value,true);
    }
    else
    {

        xHRObjectStatus.open("GET","Controller?action=asyncChangeStatus&status="+this.text,true);
    }

    xHRObjectStatus.onreadystatechange = getDataForChangeStatus;


    xHRObjectStatus.send(null);
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

function getData () {
    if (xHRObjectPull.readyState == 4) {
        if (xHRObjectPull.status == 200) {
            var serverResponse = JSON.parse(xHRObjectPull.responseText);
            var overview = serverResponse[0];


            var new_tbody = document.createElement("tbody");
            new_tbody.id = "personTable";
            for(var i = 0; i < overview.length; i++)
            {
                var tableTR = document.createElement("tr");

                var idTD = document.createElement("td");
                idTD.innerHTML = overview[i].userId;

                var firstNameTD = document.createElement("td");
                firstNameTD.innerHTML = overview[i].firstName;

                var lastNameTD = document.createElement("td");
                lastNameTD.innerHTML = overview[i].lastName;

                var roleTD = document.createElement("td");
                roleTD.innerHTML = overview[i].role;

                var statusTD = document.createElement("td");
                statusTD.innerHTML = overview[i].status;
                statusTD.id = "status_"+ overview[i].userId;

                var addFriendTD = document.createElement("td");
                var addFriendA = document.createElement("a");
                addFriendA.href = "#";
                addFriendA.text = "Add Friend";
                addFriendA.id = ""+overview[i].userId+"";
                addFriendA.className = "addFriend";
                addFriendTD.appendChild(addFriendA);

                new_tbody.appendChild(tableTR);
                tableTR.appendChild(idTD);
                tableTR.appendChild(firstNameTD);
                tableTR.appendChild(lastNameTD);
                tableTR.appendChild(roleTD);
                tableTR.appendChild(statusTD);
                tableTR.appendChild(addFriendTD);
            }

            var overview_old_tbody = document.getElementById("personTable");
            overview_old_tbody.parentNode.replaceChild(new_tbody,overview_old_tbody);
            persons = document.getElementsByClassName("addFriend");
            addEventTo(persons);
            setTimeout("updateTables()", 5000);
        }
    }
}

function updateTables() {
    xHRObjectPull.open("GET", "Controller?action=asyncPolling", true);
    xHRObjectPull.onreadystatechange = getData;
    xHRObjectPull.send(null);
}

addEventTo(persons);
addEventToStatus(statusi)
updateTables();