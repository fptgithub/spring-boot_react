import React,{useState,useEffect} from 'react'
import axios from 'axios';
import { Link, Redirect } from "react-router-dom";
import {myHeaderConf} from '../../../tokenutils/tokenutils';
export default function Table({uri}) {


    const api = "http://localhost:8080";
    const [data,setData] = useState([]);
    const [page,setPage] = useState({});
    const [isOrderdetailByOrder,setIsOrderdetailByOrder] = useState(false);
    
    const getCookie = (cname) => {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for(let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
            c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
            }
        }
        return "";
      }

    useEffect(()=>{
        axios.get(api+uri,{
            withCredentials: true,
            credentials : true
        })
        .then((res)=>{
            let data = res.data;
            setData(data.content);
            setPage({number:data.number,
                first: data.first,last: data.last,
                totalElements: data.totalElements,totalPages: data.totalPages,size: data.size,
                numberOfElements: data.numberOfElements});
        })
        .catch(error => {
            console.log(error);
        })
    },[])

    const changePage = (page) => {
        axios.get(api+uri+"?page="+page,{
            withCredentials: true,
            credentials : true
        })
        .then((res)=>{
            let data = res.data;
            setData(data.content);
            setPage({number:data.number,
                first: data.first,last: data.last,
                totalElements: data.totalElements,totalPages: data.totalPages,size: data.size,
                numberOfElements: data.numberOfElements});
        })
        .catch(error => {
            console.log(error);
        })
    }

    const convertIfObjectOrArray = (val)=>{
        if(Array.isArray(val)){
            let rs = "";
            val.map((val)=>{
                rs+=val.role.name+", ";
            })
            return rs
        }else if(typeof val === "object"){
            if(Object.keys(val).includes("name")){
                return val.name;
            }else if(Object.keys(val).includes("username")){
                return val.username;
            }
            return val.id;
        }
        return val;
    }

    const getAllOrderDetailByOrder = (id)=>{
        axios.get(api+"/admin/api/order/orderdetail/"+id,{
            withCredentials: true,
            credentials : true
        })
        .then(res => {
            setData(res.data);
            setIsOrderdetailByOrder(true);
        })
        .catch((error)=>{
            console.log(error);
        })
    }

    const orderDetailByOrder = (id) => {
        if(uri.includes('order') && !uri.includes('orderdetail') && !isOrderdetailByOrder){
            return <a className="dropdown-item" onClick = {()=>{getAllOrderDetailByOrder(id)}}>Order Detail</a>
        }
    }

    const deleteObj = (elm) => {
        let key = null;
        if(Object.keys(elm).includes("id")){
            key = elm.id;
        }else if(Object.keys(elm).includes("username")){
            key = elm.username;
        }
        let url = "";
        if(isOrderdetailByOrder){
            url = api+"/admin/orderdetail/delete/"+key;
        }else{
            url = api+uri.replace("table","delete/"+key)
        }
        axios.delete(url,{
            withCredentials: true,
            credentials : true
        })
        .then(()=>{isOrderdetailByOrder?getAllOrderDetailByOrder(elm.order.id):changePage(page.number)})
        .catch((error)=>{
            console.log(error)
        });
        
    }

    const backToOrder = ()=>{
        changePage(page.number);
        setIsOrderdetailByOrder(false);
    }

    const render = () => {
        var keys = [];
        if(Array.isArray(data) && data.length>0){
            keys = Object.keys(data[0]);
            return (
            <table className="table">
            <thead>
                <tr>
                    {keys.map((key,idx) => {
                        return (
                        <th scope="col" key = {idx}>{key}</th>
                        )
                    })}
                    <th scope="col" key = "xx">delete</th>
                </tr>
            </thead>
            <tbody>
                    {data.map((elm,idx)=>{
                        return (
                        <tr key = {idx}>
                            {Object.values(elm).map((val,idx)=>{
                                return (<td key = {idx} >{String(convertIfObjectOrArray(val))}</td>)
                            })}
                            <td key = "xx" >
                            <div className="btn-group">
                                <button type="button" className="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div className="dropdown-menu">
                                <a className="dropdown-item" onClick = {()=>{deleteObj(elm)}}>delete</a>
                                {orderDetailByOrder(elm.id)}
                                {!uri.includes('order') && <Link className="dropdown-item" to = {uri.replace("table","form").replace("/api","")+"/"+elm.id}>edit</Link>}
                                
                                </div>
                            </div>

                            </td>
                        </tr>
                        )
                    })}
          </tbody>
          </table>
            )
        }
    }
    return (
        <div className = "d-flex flex-column justify-content-center mt-3 mb-3">
            {render()}
            {!isOrderdetailByOrder &&
            <nav aria-label="Page navigation ">
            <ul className="pagination justify-content-center mt-3 mb-3">
                {!page.first && <li className="page-item"><a className="page-link" onClick = {() => {changePage(page.number-1)}}>Previous</a></li>}
                <li className="page-item"><a className="page-link">{page.number+1}</a></li>
                {!page.last && <li className="page-item"><a className="page-link" onClick = {() => {changePage(page.number+1)}}>Next</a></li>}
            </ul>
            </nav>
            }
            {
                isOrderdetailByOrder && <button type="button" className="btn btn-primary btn-sm" onClick = {backToOrder}>Back</button>
            }
        </div>
    )
}
