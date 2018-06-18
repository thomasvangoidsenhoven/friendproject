/**
 * Created by thomas vangoidsenhoven on 30/05/2017.
 */

//TODO duplicate code


var startChatButtons = document.getElementsByClassName("chatProc");
var chat = document.getElementById("chat");
var bindMessageButtons = document.getElementsByClassName("sendButton");


//check if room is already created
function createRoom() {
    var friendId = this.id;

    friendId = friendId.slice(5,friendId.length);

    $.post("Controller?action=asyncChatController", {"friendId": friendId, "strategy" : "createRoom"} ,function (data) {
        var div = document.createElement("div");
        div.id = data[2];

        var header = document.createElement("h4");
        header.innerHTML = "Chat tussen " + data[1] + " en " + data[0];
        var div2 = document.createElement("div");
        div2.appendChild(header);
        chat.appendChild(div);
        div.appendChild(div2);

        var input = document.createElement("input");
        var submit = document.createElement("button")
        submit.innerHTML = "send";
        submit.className = "sendButton";
        input.id = "input_"+data[2];
        div.appendChild(input);
        div.appendChild(submit);
        bindSendMessageButton();

    });
}

function getRooms() {
    $.get("Controller?action=asyncChatController&strategy=getRooms",function (data) {

        //for each messagebinding
        for(var i = 0; i < data.length; i++)
        {
            var element = document.getElementById(data[i].id);
            //if room exists dont create a new one
            if(element === null)
            {
                //create room
                var div = document.createElement("div");
                div.id = data[i].id;
                var div2 = document.createElement("div");
                var header = document.createElement("h4");
                header.innerHTML = "Chat tussen " + data[i].user1.firstName + " en " + data[i].user2.firstName;


                var input = document.createElement("input");
                var submit = document.createElement("button");
                submit.innerHTML = "send";
                submit.className = "sendButton";
                input.id = "input_" +data[i].id;;

                div2.appendChild(header);


                //for each message
                for(var j =0; j< data[i].messages.length; j++)
                {
                    console.log("message");
                    var p = document.createElement("p");
                    p.innerHTML = data[i].messages[j].author + ": " + data[i].messages[j].message;

                    div2.appendChild(p);
                }

                div.appendChild(div2);
                div.appendChild(input);
                div.appendChild(submit);
                chat.appendChild(div);

            }
            else
            {
                var textContainer = document.getElementById(data[0]).firstChild;
                for(var j =0; j< data[i].messages.length; j++)
                {
                    console.log("message");
                    var p = document.createElement("p");
                    p.innerHTML = data[i].messages[j].author + ": " + data[i].messages[j].message;

                    textContainer.appendChild(p);
                }
            }



        }
        document.getElementById("chat").parentNode.replaceChild(newChat,document.getElementById("chat"));
        bindSendMessageButton();
        //recall function every 4 seconds
        setTimeout("getRooms()", 4000);
    });
}



function postMessage() {

    var bindingId= this.parentNode.id;
    var message = document.getElementById("input_"+this.parentNode.id);
    var parent = this.parentNode;
    $.post("Controller?action=asyncChatController", {"bindingId" : bindingId,"message": message.value, "strategy" : "postMessage"} ,function (data) {

        var json =data;
        var p = document.createElement("p");
        p.innerHTML = json.author + ": " + json.message;
        parent.firstChild.appendChild(p);


    });
}


function bindButtons() {
    for(var i = 0; i < startChatButtons.length; i++)
    {
        startChatButtons[i].onclick = createRoom;
    }
}

function bindSendMessageButton()
{
    for(var i = 0; i < bindMessageButtons.length; i++)
    {
        bindMessageButtons[i].onclick = postMessage;
    }
}

bindButtons();
getRooms();
