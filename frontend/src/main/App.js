import React from 'react';
import "bootswatch/dist/flatly/bootstrap.css";
import "../custom.css";
import Navbar from '../components/navbar.jsx';

import Rotas from './Rotas';

class App extends React.Component {
  
  render(){

    return(
      <>
        <Navbar/>
        <div className='container'>
          <Rotas/>
        </div>
      </>
    );
  }  
}

export default App;
