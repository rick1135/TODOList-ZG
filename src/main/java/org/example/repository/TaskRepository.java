package org.example.repository;

import org.example.model.Task;
import org.example.model.TaskStatus;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final List<Task> tarefas = new ArrayList<>();
    private int proxId=1;
    private final String NOME_ARQUIVO="tarefas.txt";

    public TaskRepository(){
        carregarArquivo();
    }

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
        salvarNoArquivo();
    }

    public List<Task> listarTarefas(){
        return tarefas;
    }

    public Task buscarPorId(int id) {
        for(Task tarefa : tarefas){
            if(tarefa.getId()==id) return tarefa;
        }
        return null;
    }

    public boolean atualizar(Task tarefaAtualizada){
        for(int i=0; i<tarefas.size(); i++){
            if(tarefas.get(i).getId()==tarefaAtualizada.getId()){
                tarefas.remove(i);
                salvarRebalanceando(tarefaAtualizada);
                return true;
            }
        }
        return false;
    }

    public boolean excluirPorId(int id){
        for(int i=0; i<tarefas.size(); i++){
            if(tarefas.get(i).getId()==id){
                tarefas.remove(i);
                salvarNoArquivo();
                return true;
            }
        }
        return false;
    }

    private void salvarNoArquivo(){
        try(PrintWriter writer=new PrintWriter(new FileWriter(NOME_ARQUIVO))){
            for(Task t : tarefas){
                String linha = t.getId() + ";" +
                        t.getNome() + ";" +
                        t.getDescricao() + ";" +
                        (t.getDataFinal() != null ? t.getDataFinal() : "") + ";" +
                        t.getPrioridade() + ";" +
                        t.getCategoria() + ";" +
                        t.getStatus();
                writer.println(linha);
            }
        } catch (Exception e){
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    private void carregarArquivo(){
        File arquivo=new File(NOME_ARQUIVO);
        if(!arquivo.exists()) return;

        try(BufferedReader reader=new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha=reader.readLine())!=null){
                if(linha.trim().isEmpty()) continue;

                String[] partes=linha.split(";");

                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String descricao = partes[2];
                LocalDate dataFinal = (!partes[3].isEmpty()) ? LocalDate.parse(partes[3]) : null;
                int prioridade = Integer.parseInt(partes[4]);
                String categoria = partes[5];
                TaskStatus status = TaskStatus.valueOf(partes[6]);

                Task tarefa = new Task(id, nome, descricao, dataFinal, prioridade, categoria, status);
                tarefas.add(tarefa);

                if(id>=proxId) proxId=id+1;
            }
        } catch (Exception e){
            System.out.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }
}
