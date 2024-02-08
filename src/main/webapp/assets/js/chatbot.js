const msgerForm = get(".msger-inputarea");
const msgerInput = get(".msger-input");
const msgerChat = get(".msger-chat");
const waitingSpinner = getById("waiting");

//const url = "http://192.168.68.99:11434/api/generate";
const url = "http://localhost:8765/aichatbot/chat?message=";

// Icons made by Freepik from www.flaticon.com
const BOT_IMG = "https://image.flaticon.com/icons/svg/327/327779.svg";
const PERSON_IMG = "https://image.flaticon.com/icons/svg/145/145867.svg";
const BOT_NAME = "BOT";
const PERSON_NAME = "Shivansh";

msgerForm.addEventListener("submit", event => {
  event.preventDefault();

  const msgText = msgerInput.value;
  if (!msgText) return;

  appendMessage(PERSON_NAME, PERSON_IMG, "right", msgText);
  msgerInput.value = "";
  
waitingSpinner.style.display="";
  botResponse(msgText);
});

function appendMessage(name, img, side, text) {
  //   Simple solution for small apps
  const msgHTML = `
    <div class="msg ${side}-msg">
      <div class="msg-img" style="background-image: url(${img})"></div>

      <div class="msg-bubble">
        <div class="msg-info">
          <div class="msg-info-name">${name}</div>
          <div class="msg-info-time">${formatDate(new Date())}</div>
        </div>

        <div class="msg-text">${text}</div>
      </div>
    </div>
  `;

  msgerChat.insertAdjacentHTML("beforeend", msgHTML);
  msgerChat.scrollTop += 500;
}

function botResponse(msgText) {
	let reqUrl = url + msgText;
	//postData(url, {  "model": "llama2",  prompt}).then((data) => {
	getData(reqUrl).then((data) => {	
		console.log(data);
		/*const lines = data.split('\n');
		var chatReponse = "";
		var chatDone = false;
		
		for (var i = 0; i < lines.length; i++) {
			const jsonLine =JSON.parse(lines[i]);
			let responseLine = jsonLine.response;
			responseLine = responseLine.replace(/(?:\r\n|\r|\n)/g, '<br>');;
			chatReponse += responseLine;
			chatDone = jsonLine.done;
			
			if (chatDone)
				break;
		}
		
		console.log(chatReponse);
		*/
		appendMessage(BOT_NAME, BOT_IMG, "left", data);
		waitingSpinner.style.display="none";
	});
  
}

// Utils
function get(selector, root = document) {
  return root.querySelector(selector);
}

function getById(eleid, root = document) {
  return root.getElementById(eleid);
}

function formatDate(date) {
  const h = "0" + date.getHours();
  const m = "0" + date.getMinutes();

  return `${h.slice(-2)}:${m.slice(-2)}`;
}

function random(min, max) {
  return Math.floor(Math.random() * (max - min) + min);
}

async function postData(url = "", data = {}) {
  // Default options are marked with *
  const response = await fetch(url, {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "Content-Type": "application/json",
      // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    redirect: "follow", // manual, *follow, error
    referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: JSON.stringify(data), // body data type must match "Content-Type" header
  }).then(function(response) {
    // The response is a Response instance.
    // You parse the data into a useable format using `.json()`
    return response.text();
  }).then(function(data) {
    // `data` is the parsed version of the JSON returned from the above endpoint.
    return data ;
  });
  
  return response; // parses JSON response into native JavaScript objects
}

async function getData(url = "") {
  // Default options are marked with *
  const response = await fetch(url, {
    method: "GET", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "Content-Type": "application/json",
      // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    redirect: "follow", // manual, *follow, error
    referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
  }).then(function(response) {
    // The response is a Response instance.
    // You parse the data into a useable format using `.json()`
    return response.text();
  }).then(function(data) {
    // `data` is the parsed version of the JSON returned from the above endpoint.
    return data ;
  });
  
  return response; // parses JSON response into native JavaScript objects
}


