package org.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataFinal;
    private int prioridade;
    private String categoria;
    private TaskStatus status;

    public Task(int id, String nome, String descricao, LocalDate dataFinal, int prioridade, String categoria, TaskStatus status){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataFinal = dataFinal;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = (dataFinal != null) ? dataFinal.format(formatador) : "Sem data";

        return "ID: " + id +
                " | Nome: " + nome +
                " | Prioridade: " + prioridade +
                " | Status: " + status +
                " | Categoria: " + categoria +
                " | Término: " + dataFormatada +
                "\n   Descrição: " + descricao;
    }
}
