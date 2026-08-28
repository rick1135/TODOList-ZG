package org.example.service;

import org.example.model.Task;
import org.example.model.TaskStatus;
import org.example.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task criarTarefa(String nome, String descricao, LocalDate dataFinal, int prioridade, String categoria, TaskStatus status){
        if(nome==null || nome.trim().isEmpty()){
            System.out.println("Erro: informe o nome da tarefa!");
            return null;
        }

        if(prioridade<1||prioridade>5){
            System.out.printf("Erro: A prioridade deve ser de 1 a 5!");
            return null;
        }

        int id = taskRepository.gerarNovoId();
        Task novaTarefa = new Task(id, nome, descricao, dataFinal, prioridade, categoria, status);

        taskRepository.salvarRebalanceando(novaTarefa);
        return novaTarefa;
    }

    public List<Task> listarTarefas(){
        return taskRepository.listarTarefas();
    }

    public List<Task> listarPorCategoria(String categoria){
        List<Task> tarefasFiltradas = new ArrayList<>();
        for(Task tarefa : taskRepository.listarTarefas()){
            if(tarefa.getCategoria().equalsIgnoreCase(categoria))
                tarefasFiltradas.add(tarefa);
        }
        return tarefasFiltradas;
    }

    public List<Task> listarPorPrioridade(int prioridade){
        List<Task> tarefasFiltradas = new ArrayList<>();
        for(Task tarefa: taskRepository.listarTarefas()){
            if(tarefa.getPrioridade()==prioridade)
                tarefasFiltradas.add(tarefa);
        }
        return tarefasFiltradas;
    }

    public List<Task> listarPorStatus(TaskStatus status){
        List<Task> tarefasFiltradas = new ArrayList<>();
        for(Task tarefa:taskRepository.listarTarefas()){
            if(tarefa.getStatus()==status)
                tarefasFiltradas.add(tarefa);
        }
        return tarefasFiltradas;
    }

    public boolean excluirTarefa(int id){
        return taskRepository.excluirPorId(id);
    }
}
