import React from 'react';
import './App.css';
import PrimarySearchAppBar from './Navigation';
import Checkout from './RegisterForm';

function App() {
  return (
    <div>
        <PrimarySearchAppBar/>
        <Checkout/>
    </div>
  );
}

export default App;
