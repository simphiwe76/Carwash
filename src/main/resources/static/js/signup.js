const  email  = document.getElementById('email');
const  password  = document.getElementById('password');
const btnSign = document.getElementById('sign');

const auth = firebase.auth();

const signUpUser = () =>{
    const e = email.value;
    const p = password.value;

    auth.createUserWithEmailAndPassword(e,p)
    .then(()=>{
            alert('User signed in successfully');
    })
    .catch(error=>{
        console.log(error);
    })
}


btnSign.addEventListener('click',signUpUser);