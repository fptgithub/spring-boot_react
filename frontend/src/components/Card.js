import React,{useEffect,useState} from 'react'
import axios  from 'axios';

export default function Card() {

    const [categorys,setCategorys] = useState([]);
    const [products,setProducts] = useState([]);
    useEffect(()=>{
      axios.get("http://localhost:8080/card/categorys")
      .then((res) => {
        setCategorys(res.data);
      })
      .catch((error)=>{
        console.log(error);
      });
      setProducts(JSON.parse(localStorage.getItem("myCard")));
    },[]);

    const changeQuantity = (e)=>{
      let target = e.target;
      if(Number(target.value)<1) return null;
      localStorage.setItem("myCard",JSON.stringify(products.map((p)=>{
        return target.id === p.productid ? {...p,quantity:target.value} : p;
      })));
      setProducts(products.map((p)=>{
        return target.id === p.productid ? {...p,quantity:target.value} : p;
      }));
      
    }
    
    return (
      <div className = "container">
<header className="header row d-flex justify-content-center mt-3 mb-3">
        <div className="col-10">
          <nav className="navbar navbar-expand-lg navbar-light bg-light col-12">
            <a className="navbar-brand" href="/home">Shop</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon" />
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
              <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                  <a className="nav-link" href="/home">Home <span className="sr-only">(current)</span></a>
                </li>
                <li className="nav-item dropdown">
                  <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Category
                  </a>
                  <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                    {categorys.map((cate,idx) => {
                        let href = "http://localhost:8080/home/"+cate.id;
                        return <a key = {idx} className="dropdown-item"  href={href}>{cate.name}</a>
                    })}
                  </div>
                </li>
              </ul>
              <form className="form-inline my-2 my-lg-0 mr-5" action="http://localhost:8080/home/search" method="get">
                <input className="form-control mr-sm-2" type="search" name="name" placeholder="Name of product" aria-label="Search" />
                <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
              </form>
              <div className="dropdown">
                <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Search with price
                </button>
                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <a className="dropdown-item" href="http://localhost:8080/home/search?price1=1&price2=50">1$ -&gt; 50$</a>
                  <a className="dropdown-item" href="http://localhost:8080/home/search?price1=51&price2=100">51 -&gt;100</a>
                  <a className="dropdown-item" href="http://localhost:8080/home/search?price1=101&price2=150">101 -&gt; 150</a>
                  <a className="dropdown-item" href="http://localhost:8080/home/search?price1=151&price2=200">151 -&gt; 200</a>
                  <a className="dropdown-item" href="http://localhost:8080/home/search?price1=201&price2=250">201 -&gt; 250</a>
                </div>
              </div>
              {/* <div sec:authorize="isAuthenticated()">
                <a className="btn btn-primary" href="/logout" role="button">Logout</a>
              </div>
              <div sec:authorize="!isAuthenticated()">
                <a className="btn btn-primary" href="/login" role="button">Login</a>
              </div> */}
            </div>
          </nav>
        </div>
      </header>
      <div className = "col-10 ">
              {products.map((p,idx) => {
                return (
                  <div className="row" key = {idx}>
                <div className = "col-4"><p> {p.name}</p></div>
                <div className = "col-4"><p>{p.quantity*p.price}$</p></div>
                <div className = "col-4"><input type="number" onChange = {changeQuantity} value = {p.quantity} id = {p.productid}  /></div>
              </div>
                )
              })}
              <a class="btn btn-primary content-conter"  href = "/home/order">Order</a>
      </div>
      </div>
        
    )
}
