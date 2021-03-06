/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Luiz Henrique
 * Representa um Voto que no contexto de elei��o � o m�todo de escolha do local para almo�ar. 
 */
@Entity
@Table(name = "voto")
public class Voto implements Serializable{

    @Id
    @GeneratedValue
    /** 
     * Identificador �nico do voto.
     */
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    /**
     * O funcion�rio � aquele que realiza o voto.
     */
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    /**
     * Restaurante � o local votado pelo funcion�rio para almo�ar.
     */
    private Restaurante restaurante;

    @Column(name = "data_voto", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    /**
     * � o dia em que o voto foi feito.
     */
    private Date dataVoto;

    public Voto() {
        dataVoto = new Date(System.currentTimeMillis());
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Date getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(Date dataVoto) {
        this.dataVoto = dataVoto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + (this.funcionario != null ? this.funcionario.hashCode() : 0);
        hash = 23 * hash + (this.restaurante != null ? this.restaurante.hashCode() : 0);
        hash = 23 * hash + (this.dataVoto != null ? this.dataVoto.hashCode() : 0);
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
        final Voto other = (Voto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    private static final long serialVersionUID = 360555769958757173L;
}
