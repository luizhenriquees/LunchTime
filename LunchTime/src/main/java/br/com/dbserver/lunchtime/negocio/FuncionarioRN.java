/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.dao.FuncionarioDAO;
import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.util.DAOFactory;
import br.com.dbserver.lunchtime.util.RNException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public class FuncionarioRN {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioRN() {
        this.funcionarioDAO = DAOFactory.criarFuncionarioDAO();
    }

    public Funcionario carregar(Integer id) {
        return this.funcionarioDAO.carregar(id);
    }

    public Funcionario buscaPorCodigoFuncionarioNaEmpresa(String codigo) {
        return this.funcionarioDAO.buscarPorCodigoFuncionarioNaEmpresa(codigo);
    }

    public Funcionario buscaPorLogin(String login) {
        return this.funcionarioDAO.buscarPorLogin(login);
    }

    public Funcionario buscaPorEmail(String email) {
        return this.funcionarioDAO.buscarPorEmail(email);
    }

    public void salvar(Funcionario funcionario) throws RNException{
        Integer codigo = funcionario.getId();
        if (codigo == null || codigo == 0) {
            funcionario.setNome(funcionario.getNome().toUpperCase());
            funcionario.getPermissao().add("ROLE_USUARIO");
            funcionario.setDataCadastro(new Date(System.currentTimeMillis()));
            this.funcionarioDAO.salvar(funcionario);
        } else {
            this.funcionarioDAO.atualizar(funcionario);
        }
    }

    public void excluir(Funcionario funcionario) {
        this.funcionarioDAO.excluir(funcionario);
    }

    public List<Funcionario> listar() {
        List<Funcionario> lista = this.funcionarioDAO.listar();
        return lista;
    }
}