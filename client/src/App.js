import React, {useEffect, useState} from 'react';
import './App.css';
import PrimarySearchAppBar from './Navigation';
import Checkout from './RegisterForm';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom'
import Home from './Home';
import CustomizedExpansionPanels from './eventList';
import CreateEvent from './createEvent';
import Application from './Application';

function App() {
  const [redirect, setRedirect] = useState(false);
  useEffect(() => {
    setRedirect(true); // Probably need to set redirect based on some condition
  }, []);
  
  return (
    <div>
      <PrimarySearchAppBar />
      <Router>
        <div>
          <Switch>
          <Route exact path="/registerform">
              <Checkout />
          </Route>

          <Route exact path="/home">
            <Home />
          </Route>

          <Route exact path="/eventList">
            <CustomizedExpansionPanels />
          </Route>

          <Route exact path="/createEvent">
            <CreateEvent />
          </Route>
          </Switch>
        </div>
      </Router>
    </div>
  );
  }

export default App;
