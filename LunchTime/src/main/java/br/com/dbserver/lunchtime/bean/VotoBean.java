/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.bean;

import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import br.com.dbserver.lunchtime.negocio.VotoRN;
import br.com.dbserver.lunchtime.util.ContextoUtil;
import br.com.dbserver.lunchtime.util.RNException;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 * Classe respons�vel por ser uma ponte entre a regra de neg�cio da entidade
 * Voto com as Views relacionadas a mesma. "Delegando" fun��es espec�ficas para
 * cada View.
 *
 * @author Luiz Henrique
 */
/**
 * Anota��o que reflete o nome a ser utilizado para chamar essa classe atrav�s
 * da View.
 */
@ManagedBean(name = "votoBean")
@ViewScoped
public class VotoBean {

    /**
     * Este � o objeto utilizado para manipular inser��es, edi��es e dele��es.
     */
    private Voto voto = new Voto();
    private List<Voto> lista;
    private List<Voto> listaVotosRestaurante;
    private List<Voto> listaVotosRestauranteDia;
    private List<Voto> listaVotosFuncionario;
    private Restaurante restauranteSelecionado;

    public String novo() {
        this.voto = new Voto();
        return "/publico/cadastrarVoto";
    }

    public void salvar() {
        if (restauranteSelecionado != null) {
            try {
                VotoRN votoRN = new VotoRN();
                ContextoBean contextoBean = ContextoUtil.getContextoBean();
                voto.setFuncionario(contextoBean.getFuncionarioLogado());
                votoRN.salvar(this.voto);
                enviaMensagemFaces(FacesMessage.SEVERITY_INFO, "Clique em 'Acompanhar Vota��o' no menu 'Elei��es' para mais informa��es.", "Voto realizado com sucesso!");
                novo();
            } catch (RNException ex) {
                enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Erro:");
            }
        } else {
            enviaMensagemFaces(FacesMessage.SEVERITY_WARN, "Erro de valida��o", "Escolha o restaurante em que voc� vai votar!");
        }
    }

    public void excluir() {
        VotoRN votoRN = new VotoRN();
        votoRN.excluir(this.voto);
        this.lista = null;
    }

    public List<Voto> getLista() {
        if (this.lista == null) {
            VotoRN votoRN = new VotoRN();
            this.lista = votoRN.listar();
        }
        return this.lista;
    }

    public List<Voto> getListaVotosRestauranteDia() {
        VotoRN votoRN = new VotoRN();
        this.listaVotosRestauranteDia = votoRN.listarVotosDoDia(restauranteSelecionado, new Date(System.currentTimeMillis()));
        return listaVotosRestauranteDia;
    }

    public void setListaVotosRestauranteDia(List<Voto> listaVotosRestauranteDia) {
        this.listaVotosRestauranteDia = listaVotosRestauranteDia;
    }

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }

    public List<Voto> getListaVotosRestaurante() {
        VotoRN votoRN = new VotoRN();
        this.listaVotosRestaurante = votoRN.listarVotosRestaurante(restauranteSelecionado);
        return listaVotosRestaurante;
    }

    public void setListaVotosRestaurante(List<Voto> listaVotosRestaurante) {
        this.listaVotosRestaurante = listaVotosRestaurante;
    }

    public List<Voto> getListaVotosFuncionario() {
        VotoRN votoRN = new VotoRN();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        this.listaVotosFuncionario = votoRN.listarVotosFuncionario(contextoBean.getFuncionarioLogado());
        return listaVotosFuncionario;
    }

    public void setListaVotosFuncionario(List<Voto> listaVotosFuncionario) {
        this.listaVotosFuncionario = listaVotosFuncionario;
    }

    public Restaurante getRestauranteSelecionado() {
        return restauranteSelecionado;
    }

    public void setRestauranteSelecionado(Restaurante restauranteSelecionado) {
        this.restauranteSelecionado = restauranteSelecionado;
    }

    /**
     * M�todo respons�vel por enviar mensagens para as views de acordo com as opera��es realizadas.
     * @param severidade � o grau da mensagem: erro, aviso, informativo.
     * @param titulo � o t�tulo da mensagem.
     * @param conteudo � o conte�do da mensagem.
     */
    private void enviaMensagemFaces(Severity severidade, String titulo, String conteudo) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(severidade, conteudo, titulo);
        context.addMessage(null, facesMessage);
    }

    /**
     * M�todo respons�vel por receber a requisi��o de sele��o de um restaurante na view e retornar o objeto selecionado.
     * @param event � o evento de sele��o o qual ir� recuperar o objeto selecionado.
     */
    public void onRowSelectRestaurante(SelectEvent event) {
        this.restauranteSelecionado = (Restaurante) event.getObject();
        voto.setRestaurante(this.restauranteSelecionado);
        enviaMensagemFaces(FacesMessage.SEVERITY_INFO, ((Restaurante) event.getObject()).getNome(), "Restaurante selecionado: ");
    }
}
