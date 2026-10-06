const output = document.getElementById("data");

function login(){
  console.log("button was clicked");
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
//  data += document.getElementById("password").value + document.getElementById("username").value;
  fetch("http://localhost:8000/loginAPIjs", {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify({
              username: username,
              password: password
          })
      });
}
function loadLatestData(){
     console.log("Fetching...");

        fetch("http://localhost:8000/loginAPIjs")
            .then(res => {
                console.log("Status:", res.status);
                return res.text();
            })
            .then(data => {
                console.log("Received:", data);
                output.textContent = data;
            })
            .catch(err => {
                console.error("FETCH ERROR:", err);
                output.textContent = "ERROR: " + err;
            });
}
