import './App.css';
import Card from './components/Card';
import AdminIndex from './components/adminComponents/AdminIndex';
import Table from './components/adminComponents/table/Table';
import Header from './components/adminComponents/Header';
import Product from './components/adminComponents/adminForm/Product'
import Account from './components/adminComponents/adminForm/Account';
import Login from './components/Login';
import {
  HashRouter,
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      
      <HashRouter>
      <Switch>
          <Route path="/" exact>
            <Header />
          </Route>
          <Route path="/card" exact>
            <Card />
          </Route>
          <Route exact path="/admin/product/table"
          component={()=><AdminIndex body = {<Table uri = "/admin/api/product/table"/>}/>}
          >
          </Route>
          <Route exact path="/admin/order/table"
          component = {()=><AdminIndex body = {<Table uri = "/admin/api/order/table"/>}/>}
          >
          </Route>
          <Route exact path="/admin/orderdetail/table"
          component = {()=><AdminIndex body = {<Table uri = "/admin/api/orderdetail/table"/>}/>}
          >
          </Route>
          <Route exact path="/admin/account/table"
          component = {()=><AdminIndex body = {<Table uri = "/admin/api/account/table"/>}/>}
          >
          </Route>
          <Route exact path="/admin/category/table"
          component = {()=><AdminIndex body = {<Table uri = "/admin/api/category/table"/>}/>}
          >
          </Route>
          <Route exact path="/admin/product/form"
          component = {()=><AdminIndex body = {<Product/>}/>}
          >
          </Route>
          <Route exact path="/admin/product/form/:id"
          component = {()=><AdminIndex body = {<Product/>}/>}
          >
          </Route>
          <Route exact path="/admin/account/form"
          component = {()=><AdminIndex body = {<Account/>}/>}
          >
          </Route>
          <Route exact path="/admin/account/form/:id"
          component = {()=><AdminIndex body = {<Account/>}/>}
          >
          </Route>
        </Switch>
        </HashRouter>
    </div>
  );
}

export default App;
