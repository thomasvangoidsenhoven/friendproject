/**
 * Created by thomas vangoidsenhoven on 30/05/2017.
 */
var startChatButtons = document.getElementsByClassName("chatProc");
var chat = document.getElementById("chat");
var hook = document.getElementById("hook");
var bindMessageButtons = document.getElementsByClassName("sendButton");
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

        var newChat = document.createElement("div");
        newChat.id = "chat";

        //for each messagebinding
        for(var i = 0; i < data.length; i++)
        {
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
            console.log(data[i].messages.length)
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
            newChat.appendChild(div);


        }
        document.getElementById("chat").parentNode.replaceChild(newChat,document.getElementById("chat"));
        bindSendMessageButton();
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
