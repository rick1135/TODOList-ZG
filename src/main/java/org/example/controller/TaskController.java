package org.example.controller;

import org.example.model.Task;
import org.example.model.TaskStatus;
import org.example.service.TaskService;

import java.time.LocalDate;
import java.util.List;

public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public Task criarTarefa(String nome, String descricao, LocalDate dataFinal, int prioridade, String categoria, TaskStatus status){
        return taskService.criarTarefa(nome, descricao, dataFinal, prioridade, categoria, status);
    }

    public List<Task> listarTarefas(){
        return taskService.listarTarefas();
    }

    public List<Task> listarPorCategoria(String categoria){
        return taskService.listarPorCategoria(categoria);
    }

    public List<Task> listarPorPrioridade(int prioridade){
        return taskService.listarPorPrioridade(prioridade);
    }

    public List<Task> listarPorStatus(TaskStatus status){
        return taskService.listarPorStatus(status);
    }

    public boolean excluirTarefa(int id){
        return taskService.excluirTarefa(id);
    }
}
