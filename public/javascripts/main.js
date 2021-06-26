    $(document).ready(function(){
        $('#board-size').change(function() {
          var opval = $(this).val();
          if (opval != '') {
            console.log("Sending ...");
            sendInitToServer();
            $('#board-size').val("");
          }
        });

        $('#board-size').click(function() {
          if ( $(this).data('clicks') == 1 ) {
            // Trigger here your function:    
            console.log('Selected Option: ' + $(this).val() );
            $(this).data('clicks', 0);
            var opval = $(this).val();
            if (opval != '') {
              console.log("Sending ...");
              sendInitToServer();
              $('#board-size').val("");
            }
          } else {
            console.log('first click');
            $(this).data('clicks', 1);
          }
        });
        
        $('#board-size').vclick(function() {
          if ( $(this).data('clicks') == 1 ) {
            // Trigger here your function:    
            console.log('Selected Option: ' + $(this).val() );
            $(this).data('clicks', 0);
            var opval = $(this).val();
            if (opval != '') {
              console.log("Sending ...");
              sendInitToServer();
              $('#board-size').val("");
            }
          } else {
            console.log('first click');
            $(this).data('clicks', 1);
          }
        });

        $('#board-size').focusout( function() {
          $(this).data('clicks', 0);
        });
    });

    var webSocket;
    var messageInput;

    function init() {
        webSocket = new WebSocket("ws://localhost:9000/ws");
        webSocket.onopen = onOpen;
        webSocket.onclose = onClose;
        webSocket.onmessage = onMessage;
        webSocket.onerror = onError;
        $("#message-input").focus();
    }

    function onOpen(event) {
        consoleLog("CONNECTED");
    }

    function onClose(event) {
        consoleLog("DISCONNECTED");
        alert("Disconnected from server. Try refreshing session");
    }

    function onError(event) {
        consoleLog("ERROR: " + event.data);
        consoleLog("ERROR: " + JSON.stringify(event));
    }

    function onMessage(event) {
        console.log(event.data);
        let receivedData = JSON.parse(event.data);
        console.log("New Data: ", receivedData);
        // get the text from the "body" field of the json we
        // receive from the server.
        if (receivedData.body.startsWith("Invalid")) {
          alert(receivedData.body);
          // appendServerMessageToView("Server", receivedData.body);
        } else {
          $('#remaining-moves').text(receivedData.body);
          nextMove();
        }
    }

    function consoleLog(message) {
        console.log("New message: ", message);
    }

    window.addEventListener("load", init, false);

    $("#step-button").click(function (e) {
        nextMove();
        
        // put focus back in the textarea
        $("#message-input").focus();
    });
    
    // send the message when the user presses the <enter> key while in the textarea
    $(window).on("keydown", function (e) {
        if (e.which == 13) {
            step();
            return false;
        }
    });

    function sendInitToServer() {
        messageInput = $("#board-size").val();
        // if the trimmed message was blank, return now
        if ($.trim(messageInput) == "") {
            return false;
        }
        // create the message as json
        let jsonMessage = {
            message: messageInput
        };
        // send our json message to the server
        sendToServer(jsonMessage);
    }

    function sendStepToServer() {

        messageInput = $("#board-size").val();
        // if the trimmed message was blank, return now
        if ($.trim(messageInput) == "") {
            return false;
        }

        // create the message as json
        let jsonMessage = {
            message: messageInput
        };

        // send our json message to the server
        sendToServer(jsonMessage);
    }

    // send the data to the server using the WebSocket
    function sendToServer(jsonMessage) {
        if(webSocket.readyState == WebSocket.OPEN) {
            consoleLog("SENT: " + jsonMessage.message);
            webSocket.send(JSON.stringify(jsonMessage));
        } else {
            consoleLog("Could not send data. Websocket is not open.");
        }
    }

    // Convert a given string such as: ((1,2,3),(4,5,6),(7,8,*))
    // into markup as an unordered list
    function boardMarkup(b, gridSize) {
        var board = b.replace(' ', '');
        var markup = '<ul><li>';
        for (var i = 0; i < board.length; i++) {
          if (i % gridSize == 0) {
            markup += '</li><li>';
          }
          var curr = board.charAt(i);
          var next = ''; // next is to look ahead for * so we know to use special css class on its li element
          if ((i + 1) < board.length) {
            next = board.charAt(i + 1)
          }
          if (next == '*') {
            if (curr == '(') {
              markup += "<ul><li class='empty'>";
            } else if (curr == ')') {
              markup += "</li></ul>";
            } else if (curr == ',') {
              markup += "</li><li class='empty'>";
            } else {
              markup += curr
            }
          } else {
            if (curr == '(') {
              markup += "<ul><li>";
            } else if (curr == ')') {
              markup += "</li></ul>";
            } else if (curr == ',') {
              markup += "</li><li>";
            } else {
              markup += curr
            }
          }
        }
        markup += '</li></ul>';
        return markup;
      }

      function head(lst) {
        return lst[0];
      }

      function tail(lst) {
        return lst.slice(1);
      }

      function concatenate(lst, delimiter) {
        var result = ''
        for (i = 0; i < lst.length; i++) {
          if (result.length > 0) {
            result += delimiter
          }
          result += lst[i];
        }
        return result;
      }

      function nextMove() {
        var moves = $('#remaining-moves').text().split('|')
        var curr = head(moves);
        var remaining = tail(moves);
        var remainingCount = remaining.length;
        $('#board').html(boardMarkup(curr));
        $('#remaining-moves').text(concatenate(remaining, '|'));
        $('#remaining-count').text('remaining: ' + remainingCount)
      }

