let ws;

function connect() {
    // Connect to WebSocket server hosted by Spring Boot
    ws = new WebSocket("ws://localhost:8080/ws");

    // Event handler when a message is received from WebSocket server
    ws.onmessage = function(event) {
        const responseElement = document.getElementById("root");
        responseElement.innerHTML = event.data;
    };

    // Event handler when WebSocket connection is established
    ws.onopen = function() {
        console.log("Connected to WebSocket");
    };

    // Event handler when WebSocket connection is closed
    ws.onclose = function() {
        console.log("Disconnected from WebSocket");
    };
}

function sendMessage() {
    // Retrieve user input message
    const inputElement = document.getElementById("input");
    const message = inputElement.value;

    // Send message via WebSocket to Spring Boot
    ws.send(message);

    inputElement.value = ''; // input field is not cleared after sending the message
}

// Handle the Enter key press event
function handleKeyDown(event) {
    if (event.key === 'Enter') {
        sendMessage();
        event.preventDefault();  // Prevent the default action of form submission if the input is inside a form
    }
}

// Call connect function when the page loads
window.onload = connect;
