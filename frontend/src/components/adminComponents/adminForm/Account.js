import axios from 'axios';
import React,{useEffect,useState} from 'react'

export default function Account() {
    const [user,setUser] = useState({username:'',password:'',fullname:'',email:'',role:[]});
    var role = []
    useEffect(()=>{
        axios.get('http://localhost:8080/admin/api/account/role',{
            withCredentials: true,
            credentials : true    
        })
        .then((res)=>{
            console.log(res.data)
            role = res.data;
        })
        .catch(error => console.log(error))
    },[])

    const change = (e) => {
        let target = e.target;
        setUser({ ...user, [target.name]: target.value });
      };
    return (
        <div className = "mt-5">
            <form className = "row">
          <div className="col-6">
        <div className="form-group mb-4">
          <label htmlFor="username">username</label>
          <input
            type="text"
            className="form-control"
            value = {user.usrename}
            onChange = {change}
            id="username"
            name = "username"
            placeholder="username"
          />
        </div>
        <div className="form-group mb-4">
          <label htmlFor="password">password</label>
          <input
            type="password"
            className="form-control"
            value = {user.password}
            onChange = {change}
            id="password"
            name = "password"
            placeholder="password"
          />
        </div>
        <div className="form-group mb-4">
          <label htmlFor="fullname">fullname</label>
          <input
            type="text"
            className="form-control"
            value = {user.fullname}
            onChange = {change}
            id="fullname"
            name = "fullname"
            placeholder="fullname"
          />
        </div>
        <div className="form-group mb-4">
          <label for="formfile">image</label>
          <input
            type="file"
            accept="image/png, image/jpeg"
            className="form-control-file"
            id="formfile"
          />
          <img id="blah" className = "img-fluid" src="" />
        </div>
        </div>
        <div className="col-6">
        <div className="form-group mb-4">
          <label htmlFor="fullname">email</label>
          <input
            type="text"
            value = {user.email}
            onChange = {change}
            className="form-control"
            id="email"
            name = "email"
            placeholder="email"
          />
        </div>
        <div className="form-check mb-3">
        <label className="mr-sm-2" for="inlineFormCustomSelect">role</label>
      <select multiple  name = "categoryid" className="custom-select mr-sm-2" id="formcategory">
        {role.map((val,idx)=>{
            <option key = {idx} value = {val.id} selected = {user.role.includes(val.id)?true:false}>{val.name}</option>
        })}
      </select>
      </div>
      <button  type="button" className="btn btn-primary">
          Update
        </button>
        
        <button type="button" className="btn btn-info" >Clean</button>
        
        </div>
      </form>
        </div>
    )
}
