/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.entidade;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Luiz Henrique
 * Representa um Restaurante que no contexto � o local do almo�o.
 */
@Entity
@Table(name = "restaurante")
public class Restaurante implements Serializable {

    @Id
    @GeneratedValue
    /** 
     * Identificador �nico do Restaurante.
     */
    private Integer id;
    private String nome;
    /**
     * Pre�o em rela��o ao tipo de almo�o, caso exista diversas op��es diferentes deve ser informado um valor em m�dia do pre�o da refei��o do restaurante.
     */
    private float preco;
    private String endereco;
    @Column(name = "tipo_comida")
    /**
     * Relacionado ao G�nero da refei��o do restaurante. Exemplo: Japonesa, Chinesa, Sandu�ches.
     */
    private String tipoComida;
    @Column(name = "tipo_almoco")
    /**
     * Relacionado a forma de se servir no restaurante. Exemplo: Por kilo, a la carte, self service, fast food.
     */
    private String tipoAlmoco;
    @Column(name = "distancia")
    /**
     * Dist�ncia do local de trabalho at� o restaurante.
     */
    private float distanciaAPartirDoLocalDeTrabalho;
    @Column(name = "formas_pagamento")
    /**
     * Formas de pagamento que o restaurante oferece. Exemplo: Dinheiro, cart�o, cheque.
     */
    private String formasDePagamento;
    @Column(name = "possui_estacionamento")
    private boolean possuiEstacionamentoParticular;
    @Transient
    private int quantidadeVotosDia;

    @Column(name = "hora_abertura")
    private Time horaAbertura;

    @Column(name = "hora_fecha")
    private Time horaEncerramento;

    @Column(name = "horario_pico")
    /**
     * � o hor�rio onde existe o maior �ndice de movimento no dia, indicando filas, mesas lotadas entre outros.
     */
    private Time horarioDePico;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Voto.class)
    /**
     * Lista com os votos que o restaurante j� recebeu por parte dos funcion�rios.
     */
    private List<Voto> listaVotos;

    public Restaurante() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public float getDistanciaAPartirDoLocalDeTrabalho() {
        return distanciaAPartirDoLocalDeTrabalho;
    }

    public void setDistanciaAPartirDoLocalDeTrabalho(float distanciaAPartirDoLocalDeTrabalho) {
        this.distanciaAPartirDoLocalDeTrabalho = distanciaAPartirDoLocalDeTrabalho;
    }

    public String getFormasDePagamento() {
        return formasDePagamento;
    }

    public void setFormasDePagamento(String formasDePagamento) {
        this.formasDePagamento = formasDePagamento;
    }

    public boolean isPossuiEstacionamentoParticular() {
        return possuiEstacionamentoParticular;
    }

    public void setPossuiEstacionamentoParticular(boolean possuiEstacionamentoParticular) {
        this.possuiEstacionamentoParticular = possuiEstacionamentoParticular;
    }

    public Time getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(Time horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public Time getHoraEncerramento() {
        return horaEncerramento;
    }

    public void setHoraEncerramento(Time horaEncerramento) {
        this.horaEncerramento = horaEncerramento;
    }

    public Time getHorarioDePico() {
        return horarioDePico;
    }

    public void setHorarioDePico(Time horarioDePico) {
        this.horarioDePico = horarioDePico;
    }

    public List<Voto> getListaVotos() {
        return listaVotos;
    }

    public void setListaVotos(List<Voto> listaVotos) {
        this.listaVotos = listaVotos;
    }

    public String getTipoAlmoco() {
        return tipoAlmoco;
    }

    public void setTipoAlmoco(String tipoAlmoco) {
        this.tipoAlmoco = tipoAlmoco;
    }

    public int getQuantidadeVotosDia() {
        return quantidadeVotosDia;
    }

    public void setQuantidadeVotosDia(int quantidadeVotosDia) {
        this.quantidadeVotosDia = quantidadeVotosDia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 47 * hash + Float.floatToIntBits(this.preco);
        hash = 47 * hash + (this.endereco != null ? this.endereco.hashCode() : 0);
        hash = 47 * hash + (this.tipoComida != null ? this.tipoComida.hashCode() : 0);
        hash = 47 * hash + Float.floatToIntBits(this.distanciaAPartirDoLocalDeTrabalho);
        hash = 47 * hash + (this.formasDePagamento != null ? this.formasDePagamento.hashCode() : 0);
        hash = 47 * hash + (this.possuiEstacionamentoParticular ? 1 : 0);
        hash = 47 * hash + (this.horaAbertura != null ? this.horaAbertura.hashCode() : 0);
        hash = 47 * hash + (this.horaEncerramento != null ? this.horaEncerramento.hashCode() : 0);
        hash = 47 * hash + (this.horarioDePico != null ? this.horarioDePico.hashCode() : 0);
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
        final Restaurante other = (Restaurante) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    private static final long serialVersionUID = 4732855178243882785L;

}
