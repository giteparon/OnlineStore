const output = document.getElementById("data");

async function login(){
  console.log("button was clicked");
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
//  data += document.getElementById("password").value + document.getElementById("username").value;
  await fetch("http://localhost:8000/loginAPIjs", {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify({
              username: username,
              password: password
          })
      });
      loadLatestData();
      if(output.textContent == "Found"){
        window.location.href = "https://www.youtube.com";
        //im too lazy to do anymore now im sorry mr mench thats all i could manage in 2 days (note: i used ai to study how to do the post and get stuff in js and in the server and in the api, all the other stuff i learned from google or youtube. this was a big learning experience and now ig i know how to do all this lol. im SOOO ready for the final project youll be so proud) ty

      }




}
function loadLatestData(){
     console.log("Fetching...");

        fetch("http://localhost:8000/loginAPIjsAnswer")
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
