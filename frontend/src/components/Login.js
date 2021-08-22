import axios from 'axios';
import React,{useState} from 'react'

export default function Login() {

    const api = "http://localhost:8080/authenticate";

    const [user,setUser] = useState({username:'',password:''})

    const change = (e)=>{

        setUser({...user,[e.target.name]:e.target.value});
    }
    function setCookie(cname, cvalue) {
        const d = new Date();
        d.setTime(d.getTime() +(1000*60*60*10));
        let expires = "expires="+ d.toUTCString();
        console.log(d.toUTCString())
        document.cookie = cname + "=" + cvalue + ";" + expires ;
      }
    const login = ()=>{
        axios.post(api,user)
        .then((res)=>{
            setCookie("jwt","Bearer "+res.data.jwt);
            console.log(res.data);
        })
        .catch((error)=>{
            console.log(error);
        })
    }

    return (
        <div>
            <h3 className = "text-center">Login</h3>
        <div className = "d-flex justify-content-center mt-3 mb-3">
            
            <form>
            <div className="form-group">
                <label for="exampleFormControlInput1">Username</label>
                <input type="text" className="form-control" id="username" name = "username" value = {user.username} onChange = {change} placeholder="username"/>
            </div>
            <div className="form-group">
                <label for="exampleFormControlInput1">Password</label>
                <input type="password" className="form-control" id="password" name = "password" value = {user.password} onChange = {change} placeholder="password"/>
            </div>
            </form>
            
        </div>
        <button type="button" class="btn btn-primary content-conter" onClick={login}>Login</button>
        </div>
    )
}
