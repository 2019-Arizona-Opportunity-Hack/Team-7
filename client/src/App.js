import React from 'react';
import './App.css';
import PrimarySearchAppBar from './Navigation';
import Checkout from './RegisterForm';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Home from './Home';

function App() {
  return (
    <div>
        <PrimarySearchAppBar/>
        <Router>
        <div>
      <Switch>
      <Route exact path="/">
        <Checkout/>
      </Route>
          <Route path="/home">
            <Home/>
          </Route>
          {/* <Route path="/login" component={Login}/> */}
      
      </Switch>
    </div>
  </Router>
        <Router>
         
        </Router>
    </div>
  );
}

export default App;
