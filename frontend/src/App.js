import React from 'react';
import Login from './views/login';
import CadastroUsuario from './views/CadastroUsuario';
import "bootswatch/dist/flatly/bootstrap.css";
import "./custom.css";

class App extends React.Component {
  
  render(){

    return(
      <div>
        <Login/>
        <CadastroUsuario/>
      </div>
    );
  }  
}

export default App;
