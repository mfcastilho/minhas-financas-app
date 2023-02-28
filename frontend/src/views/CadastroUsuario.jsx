import React from "react";
import Card from "../components/Card";
import FormGroup from "../components/FormGroup";

class CadastroUsuario extends React.Component {

  state = {
    nome: "",
    email: "",
    senha: "",
    senhaRepeticao: ""
  }

  cadastrar = () => {
    console.log(this.state);
  }

  render() {

    return (
      <div className="container">
        <Card title="Cadastro de Usuário">
          <div className="row">
            <div className="col-lg-12">
              <div className="bs-component">

                <FormGroup htmlFor="inputNome" label="Nome: *">
                  <input type="text"
                    id="inputNome"
                    value={this.state.nome}
                    className="form-control"
                    onChange={e => this.setState({ nome: e.target.value })}
                    name="nome" />
                </FormGroup>

                <FormGroup htmlFor="inputEmail" label="Email: *">
                  <input type="email"
                    id="inputEmail"
                    value={this.state.email}
                    className="form-control"
                    name="email"
                    onChange={e => this.setState({ email: e.target.value })} />
                </FormGroup>

                <FormGroup htmlFor="inputSenha" label="Senha: *">
                  <input type="password"
                    id="inputSenha"
                    value={this.state.senha}
                    className="form-control"
                    name="senha"
                    onChange={e => this.setState({ senha: e.target.value })} />
                </FormGroup>

                <FormGroup htmlFor="inputRepitaSenha" label="Repita a Senha: *">
                  <input type="password"
                    id="inputRepitaSenha"
                    value={this.state.senhaRepeticao}
                    className="form-control"
                    name="senha"
                    onChange={e => this.setState({ senhaRepeticao: e.target.value })} />
                </FormGroup>

                <button onClick={this.cadastrar} className="btn btn-success">Salvar</button>
                <button className="btn btn-danger">Cancelar</button>
              </div>
            </div>
          </div>
        </Card>
      </div>
    );
  }
}

export default CadastroUsuario;