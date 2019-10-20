import React from "react";
import "./App.css";
import App from "./App";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect,
  Link
} from "react-router-dom";
import CustomizedExpansionPanels from "./eventList";
import CreateEvent from "./createEvent";
import Home from "./Home";
import Checkout from "./RegisterForm";
import SignInSide from "./SignInSide.js";
import PrimarySearchAppBar from "./Navigation";

function Application() {
  const [loggedIn, setLoggedIn] = React.useState(true);
  const renderView = () => {
    if (!loggedIn) {
      return <SignInSide setLoggedIn={setLoggedIn} />;
    } else {
      return <App />;
    }
  };

  return (
    <div>
      <Router>
        <PrimarySearchAppBar />
        <div>
          <Switch>
            <Route exact path="/">
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
            <Route exact path="/login">
              <SignInSide />
            </Route>
          </Switch>
        </div>
      </Router>
      {renderView()}
    </div>
  );
}

export default Application;
