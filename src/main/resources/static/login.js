var loginBtn = document.getElementById("login").addEventListener("submit", login);

let userDTO = sessionStorage.getItem("userDTO")
console.log(userDTO);

async function login () {
    try{
        fetch ('/login', {
            method: 'POST',
            body: JSON.stringify(userDTO)
        })
        .then(response => console.log(response.json()))
        //.then(user => {
          //  sessionStorage.setItem('userResponse', JSON.stringify(user));
        //})
        //alert("Tenant account successfuly created!")
    }
    catch (e){
        alert("Invalid credentials!")
    }
}