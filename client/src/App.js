import React, { useEffect, useState } from "react";
import "./App.css";
import PrimarySearchAppBar from "./Navigation";
import Checkout from "./RegisterForm";
import SignInSide from "./SignInSide.js";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect
} from "react-router-dom";
import Home from "./Home";
import CustomizedExpansionPanels from "./eventList";
import CreateEvent from "./createEvent";
import Application from "./Application";

function App() {
  const [redirect, setRedirect] = useState(false);
  useEffect(() => {
    setRedirect(true); // Probably need to set redirect based on some condition
  }, []);

  return <div></div>;
}

export default App;
