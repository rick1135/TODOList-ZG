package org.example.repository;

import org.example.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final List<Task> tarefas = new ArrayList<>();
    private int proxId=1;

    public int gerarNovoId(){
        return proxId++;
    }

    public void salvarRebalanceando(Task novaTarefa){
        int posInserir=tarefas.size();

        for(int i=0; i<tarefas.size(); i++){
            if(novaTarefa.getPrioridade()>tarefas.get(i).getPrioridade()){
                posInserir=i;
                break;
            }
        }

        tarefas.add(posInserir, novaTarefa); //add move os elementos para direita, abrindo espaço para novo elemento
    }

    public List<Task> listarTarefas(){
        return tarefas;
    }

    public boolean excluirPorId(int id){
        for(int i=0; i<tarefas.size(); i++){
            if(tarefas.get(i).getId()==id){
                tarefas.remove(i);
                return true;
            }
        }
        return false;
    }
}
