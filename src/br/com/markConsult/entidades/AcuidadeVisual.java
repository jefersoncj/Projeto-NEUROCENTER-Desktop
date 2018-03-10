/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

import java.sql.Date;

/**
 *
 * @author Jeferson
 */
public class AcuidadeVisual {
    
    private Integer id;
    private Paciente paciente;
    private String tipoExame;
    private Integer olhoDireitoSemCorrecao;
    private Integer olhoEsquerdoSemCorrecao;
    private Integer olhoDireitoComCorrecao;
    private Integer olhoEsquerdoComCorrecao;
    private boolean vermelho;
    private boolean amarelo;
    private boolean verde;
    private String conclusao;
    private String observacao;
    private Date data;
    
    public AcuidadeVisual() {
    }

    public AcuidadeVisual(Integer id, Paciente paciente, String tipoExame, Integer olhoDireitoSemCorrecao, Integer olhoEsquerdoSemCorrecao, Integer olhoDireitoComCorrecao, Integer olhoEsquerdoComCorrecao, boolean vermelho, boolean amarelo, boolean verde, String conclusao, String observacao, Date data) {
        this.id = id;
        this.paciente = paciente;
        this.tipoExame = tipoExame;
        this.olhoDireitoSemCorrecao = olhoDireitoSemCorrecao;
        this.olhoEsquerdoSemCorrecao = olhoEsquerdoSemCorrecao;
        this.olhoDireitoComCorrecao = olhoDireitoComCorrecao;
        this.olhoEsquerdoComCorrecao = olhoEsquerdoComCorrecao;
        this.vermelho = vermelho;
        this.amarelo = amarelo;
        this.verde = verde;
        this.conclusao = conclusao;
        this.observacao = observacao;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public Integer getOlhoDireitoSemCorrecao() {
        return olhoDireitoSemCorrecao;
    }

    public void setOlhoDireitoSemCorrecao(Integer olhoDireitoSemCorrecao) {
        this.olhoDireitoSemCorrecao = olhoDireitoSemCorrecao;
    }

    public Integer getOlhoEsquerdoSemCorrecao() {
        return olhoEsquerdoSemCorrecao;
    }

    public void setOlhoEsquerdoSemCorrecao(Integer olhoEsquerdoSemCorrecao) {
        this.olhoEsquerdoSemCorrecao = olhoEsquerdoSemCorrecao;
    }

    public Integer getOlhoDireitoComCorrecao() {
        return olhoDireitoComCorrecao;
    }

    public void setOlhoDireitoComCorrecao(Integer olhoDireitoComCorrecao) {
        this.olhoDireitoComCorrecao = olhoDireitoComCorrecao;
    }

    public Integer getOlhoEsquerdoComCorrecao() {
        return olhoEsquerdoComCorrecao;
    }

    public void setOlhoEsquerdoComCorrecao(Integer olhoEsquerdoComCorrecao) {
        this.olhoEsquerdoComCorrecao = olhoEsquerdoComCorrecao;
    }

    public boolean isVermelho() {
        return vermelho;
    }

    public void setVermelho(boolean vermelho) {
        this.vermelho = vermelho;
    }

    public boolean isAmarelo() {
        return amarelo;
    }

    public void setAmarelo(boolean amarelo) {
        this.amarelo = amarelo;
    }

    public boolean isVerde() {
        return verde;
    }

    public void setVerde(boolean verde) {
        this.verde = verde;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
