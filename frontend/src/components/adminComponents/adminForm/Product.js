import React, { useState ,useEffect} from "react";
import axios from "axios";
import {useParams
} from "react-router-dom";
export default function Product() {

  const api = "http://localhost:8080/admin/api/product/";
  const {id} = useParams();
  const [product, setProduct] = useState({
    id: null,
    name: '',
    image: '',
    categoryid: '',
    price:'',
    available: true,
  });

  const [categorys,setCategorys] = useState([]);
  const [file,setFile] = useState('');

  const change = (e) => {
    let target = e.target;
    target.type == 'checkbox' ? setProduct({ ...product, [target.name]: target.checked }) 
    :setProduct({ ...product, [target.name]: target.value });
    ;
  };

  const filechange = (e)=>{
    console.log(e.target.files[0]);
    setFile(e.target.files[0]);
    setProduct({...product,image:e.target.files[0].name});
    var oFReader = new FileReader();

    oFReader.onload = function (oFREvent) {
        document.getElementById("blah").src = oFREvent.target.result;
        };

    oFReader.readAsDataURL(e.target.files[0]);

        
  }

  const clean = () => {
    setProduct({...product,
      id:null,
      price:'',
      available:true,
      categoryid:'',
      name:'',
      image:''
      });
    setFile('');
    document.getElementById('formfile').value = '';
  }

  const fileUpload = ()=>{
    const url = 'http://localhost:8080/file-upload';
    const formData = new FormData();
    formData.append('file',file)
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    axios.post(url, formData,config).then(res => console.log(res.data))
    .catch(error => console.log(error));
  }

  const checkData = ()=>{
    
  }

  const update = ()=>{
     file!='' && fileUpload();
    axios.post(api+"update",product)
    .then((res)=>{
      alert('update successfully!');
      setProduct(res.data);
    })
    .catch(error => console.log(error));
  }

  const create = ()=>{
    file!='' && fileUpload();
    axios.put(api+"create",product)
    .then((res)=>{
      alert('create successfully!');
      setProduct(res.data);
    })
    .catch(error => console.log(error));
  }

  useEffect(()=>{
    axios.get("http://localhost:8080/card/categorys")
      .then((res) => {
        setCategorys(res.data);
      })
      .catch((error)=>{
        console.log(error);
      });
      if(id){
        axios.get("http://localhost:8080/admin/api/product/detail/"+id)
      .then((res)=>{
        console.log(res.data);
        setProduct(res.data);
      })
      .catch(error => console.log(error));
      }
    
  },[]);

  return (
    <div className="mt-5">
      <form className = "row">
          <div className="col-6">
        <div className="form-group mb-4">
          <label htmlFor="formname">name</label>
          <input
            type="text"
            onChange = {change}
            className="form-control"
            id="formname"
            name = "name"
            value = {product.name}
            placeholder="enter name"
          />
        </div>
        <div className="form-group mb-4">
          <label for="formfile">image</label>
          <input
            type="file"
            accept="image/png, image/jpeg"
            onChange = {filechange}
            className="form-control-file"
            id="formfile"
          />
          <img id="blah" className = "img-fluid" src={file=='' && product.image != '' && '/images/'+product.image} />
        </div>
        <div className="form-group mb-4">
          <label htmlFor="formprice">Price</label>
          <input
            type="number"
            className="form-control"
            onChange = {change}
            name = "price"
            id="formprice"
            value = {product.price}
            placeholder="price"
          />
        </div>
        </div>
        <div className="col-6">
        <div className="form-check mb-2">
        <input className="form-check-input" type="checkbox" name = "available" onChange ={change} checked = {product.available?true:false} id="formavailable"/>
        <label className="form-check-label" for="formavailable">
            Available
        </label>
        </div>
        <div className="form-check mb-3">
        <label className="mr-sm-2" for="inlineFormCustomSelect">Category</label>
      <select onChange = {change} name = "categoryid" className="custom-select mr-sm-2" id="formcategory">
        <option value = '' selected = {product.categoryid===''?true:false}>Select...</option>
        {product.id!=null && categorys.map((elm,idx)=>{
            return <option  key = {idx} value={elm.id} selected = {product.categoryid==elm.id?true:false}>{elm.name}</option>
        })}
        {
        product.id==null && categorys.map((elm,idx)=>{
            return <option  key = {idx} value={elm.id} >{elm.name}</option>
        })}
      </select>
      </div>
      {product.id!=null && <button onClick={update} type="button" className="btn btn-primary">
          Update
        </button>}
        
        <button type="button" className="btn btn-info" onClick = {clean}>Clean</button>
        {
          product.id==null && <button onClick = {create} type="button" className="btn btn-success">
          Create
        </button>
        }
        
        </div>
      </form>
    </div>
  );
}
