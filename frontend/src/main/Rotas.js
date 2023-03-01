import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import Login from "../views/login";
import CadastroUsuario from "../views/CadastroUsuario";

function Rotas(){
  return(

    <HashRouter>
      <Switch>
        <Route path={"/login"} component={Login} />
        <Route path={"/cadastro-usuarios"} component={CadastroUsuario} />
      </Switch>
    </HashRouter>
  );
}

export default Rotas;