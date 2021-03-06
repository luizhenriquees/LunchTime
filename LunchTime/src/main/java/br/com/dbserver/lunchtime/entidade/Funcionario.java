/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Luiz Henrique
 * Representa um Funcion�rio da empresa que no contexto � respons�vel por eleger o local do almo�o.
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue
    /**
     * Identificador �nico do funcion�rio.
     */
    private Integer id;
    private String nome;
    @Column(unique = true)
    private String email;
    @Column(name = "codigo_func_empresa", unique = true)
    /**
     * Identificador �nico do funcion�rio dentro da empresa, que confirme que realmente ele � um funcion�rio.
     */
    private String codigoFuncionarioNaEmpresa;
    
    /**
     * Senha de acesso ao sistema.
     */
    private String senha;
    private boolean ativo;
    @Column(unique = true)
    /**
     * Login de acesso ao sistema.
     */
    private String login;
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    /**
     * Data de cadastro do funcion�rio no sistema.
     */
    private Date dataCadastro;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    /**
     * Lista com todos os votos que o funcion�rio j� realizou nas elei��es dentro do sistema.
     */
    private List<Voto> votosFuncionario;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "funcionario_permissao",
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"funcionario", "permissao"})},
            joinColumns = @JoinColumn(name = "funcionario"))

    @Column(name = "permissao", length = 50)
    /**
     * Lista com as permiss�es do funcion�rio dentro do sistema.
     */
    private Set<String> permissao = new HashSet<String>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeMaiusculo() {
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<String> getPermissao() {
        return permissao;
    }

    public void setPermissao(Set<String> permissao) {
        this.permissao = permissao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Voto> getVotosFuncionario() {
        return votosFuncionario;
    }

    public void setVotosFuncionario(List<Voto> votosFuncionario) {
        this.votosFuncionario = votosFuncionario;
    }

    public String getCodigoFuncionarioNaEmpresa() {
        return codigoFuncionarioNaEmpresa;
    }

    public void setCodigoFuncionarioNaEmpresa(String codigoFuncionarioNaEmpresa) {
        this.codigoFuncionarioNaEmpresa = codigoFuncionarioNaEmpresa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private static final long serialVersionUID = -4009532420773848278L;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 83 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 83 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 83 * hash + (this.codigoFuncionarioNaEmpresa != null ? this.codigoFuncionarioNaEmpresa.hashCode() : 0);
        hash = 83 * hash + (this.ativo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
