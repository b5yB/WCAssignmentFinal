var createBtn = document.getElementById("register").addEventListener("submit", register);

let userDTO = sessionStorage.getItem("userDTO")

async function register () {
    console.log(userDTO);
    try{
        fetch ('/register', {
            method: 'POST',
            body: JSON.stringify(userDTO)
        })
        .then(response => console.log(response.json()))
        //.then(user => {
            //sessionStorage.setItem('user', JSON.stringify(user));
            console.log(JSON.parse(userDTO));
        //})
        
        alert("Tenant account successfuly created!")
    }
    catch (e){
        alert("Tenant account already exists!")
    }
};