import React from 'react';
import './App.css';
import SignInSide from './SignInSide';
import App from './App';
import { BrowserRouter as Router, Route, Switch, Redirect, Link } from 'react-router-dom'

function Application() {
  const [loggedIn, setLoggedIn] = React.useState(false);
  const renderView = () => {
    if (!loggedIn) {
      return (
        <SignInSide setLoggedIn={setLoggedIn} />
      );
    }
    else {
      return (<App />);
    }
  }

  return (
    <div>
      {renderView()}
    </div>
  );

}

export default Application;
