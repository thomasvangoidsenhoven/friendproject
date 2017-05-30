/**
 * Created by thomas on 29/05/2017.
 */
var websocket;
var path = "friendapp";
var kolom1 = document.getElementById("k1");
var kolom2 = document.getElementById("k2");
var kolom3 = document.getElementById("k3");

var submitbuttons = document.getElementsByClassName("submit");

function openSocket()
{
    websocket = new WebSocket("ws://localhost:8080/"+path+"/blog");
    websocket.onmessage = function (event) {
        console.log( "incoming: "+event.data);
        var parsed = (JSON.parse(event.data));
        var hook;
        if(parsed.id == 1)
        {
            hook = document.getElementById("k1");

        }
        else if(parsed.id == 2)
        {
            hook = document.getElementById("k2");
        }
        else if (parsed.id == 3)
        {
            hook = document.getElementById("k3");
        }

        var li = document.createElement("li");
        var author = document.createElement("p");
        var message = document.createElement("p");

        if(parsed.user === "")
        {
            author.innerHTML = "User: anonymous";
        }else
        {
            author.innerHTML = "User: " + parsed.user;

        }

        message.innerHTML = "message: " +parsed.message;

        hook.appendChild(li);
        li.appendChild(author);
        li.appendChild(message);
        li.appendChild(document.createElement("hr"));

    };
}


function send(message) {
    websocket.send(message);
}

function closeSocket() {
    websocket.close();
}


function bindButtons() {
    for(var i =0; i < submitbuttons.length;i++)
    {
        submitbuttons[i].onclick = callToAction;
    }
}

function callToAction()
{
    var id = this.id;
    var message = document.getElementById("input"+id).value;
    var trunkedmessage = "{\"user\":" + "\"" +document.getElementById("user").value +  "\"" + ",\"message\": "+  "\"" + message +  "\"" + ",\"id\": " + id + "}";
    console.log("sending: " + trunkedmessage);
    send(trunkedmessage);
}

bindButtons();
openSocket();
window.onbeforeunload = closeSocket;