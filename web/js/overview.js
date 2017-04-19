/**
 * Created by Thomas on 15/04/2017.
 */
var xHRObject = new XMLHttpRequest();
var persons = document.getElementsByClassName("addFriend");

function addFriendTableRow(id,firstname,lastname,role)
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

    table.appendChild(tableTR);
    tableTR.appendChild(idTD);
    tableTR.appendChild(firstNameTD);
    tableTR.appendChild(lastNameTD);
    tableTR.appendChild(roleTD);
}

function getData () {
    if (xHRObject.status == 200) {
        if (xHRObject.readyState == 4) {
            var serverResponse = JSON.parse(xHRObject.responseText);
            addFriendTableRow(serverResponse.friend.id,serverResponse.friend.firstname,serverResponse.friend.lastname,serverResponse.friend.role);
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
    xHRObject.onreadystatechange = getData;
    xHRObject.send(null);
}
function addEventTo(persons) {
    for(var i = 0; i < persons.length; i++)
    {
        persons[i].onclick = doAddFriend;
    }
}


addEventTo(persons);