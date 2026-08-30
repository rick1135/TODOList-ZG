package org.example.view;

import org.example.controller.TaskController;
import org.example.model.Task;
import org.example.model.TaskStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private TaskController controller;
    private Scanner scanner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ConsoleView(TaskController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar(){
        int opcao = -1;

        while (opcao!=0){
            System.out.println("\n----- Bem-vindo ao TODO List");
            System.out.println("1 - Criar Tarefa");
            System.out.println("2 - Listar todas tarefas ordenadas por prioridade)");
            System.out.println("3 - Listar tarefas por categoria");
            System.out.println("4 - Listar tarefas por prioridade");
            System.out.println("5 - Listar tarefas por status");
            System.out.println("6 - Excluir tarefa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try{
                opcao=Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println("Insira um número válido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    criarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    listarPorCategoria();
                    break;
                case 4:
                    listarPorPrioridade();
                    break;
                case 5:
                    listarPorStatus();
                    break;
                case 6:
                    excluirTarefa();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void criarTarefa(){
        try {
            System.out.println("Nome da tarefa: ");
            String nome = scanner.nextLine();

            System.out.println("Descrição: ");
            String descricao = scanner.nextLine();

            System.out.println("Data de término (dia/mês/ano): ");
            String dataStr = scanner.nextLine();
            LocalDate date = LocalDate.parse(dataStr, formatter);

            System.out.println("Prioridade (de 1 a 5): ");
            int prioridade = Integer.parseInt(scanner.nextLine());

            System.out.println("Categoria: ");
            String categoria = scanner.nextLine();

            System.out.println("Escolha o Status:");
            System.out.println("1 - TODO (A fazer)");
            System.out.println("2 - DOING (Fazendo)");
            System.out.println("3 - DONE (Finalizada)");
            System.out.print("Opção: ");
            int statusOpcao = Integer.parseInt(scanner.nextLine());

            TaskStatus status = TaskStatus.TODO;
            if (statusOpcao == 2) {
                status = TaskStatus.DOING;
            } else if (statusOpcao == 3) {
                status = TaskStatus.DONE;
            }

            Task criada = controller.criarTarefa(nome, descricao, date, prioridade, categoria, status);
            if(criada!=null) System.out.println("\n Tarefa criada");
        } catch (Exception e){
            System.out.println("Erro ao criar tarefa!");
        }
    }

    private void listarTarefas(){
        exibirTarefas(controller.listarTarefas(), "Todas tarefas em ordem de prioridade");
    }

    private void listarPorCategoria(){
        System.out.println("Informe a categoria: ");
        String categoria = scanner.nextLine();
        exibirTarefas(controller.listarPorCategoria(categoria), "Tarefas da categoria " + categoria);
    }

    private void listarPorPrioridade(){
        System.out.println("Escolha uma prioridade de 1 a 5: ");
        try {
            int prioridade = Integer.parseInt(scanner.nextLine());
            exibirTarefas(controller.listarPorPrioridade(prioridade), "Tarefas com prioridade " + prioridade);
        } catch (Exception e){
            System.out.println("Prioridade escolhida é inválida!");
        }
    }

    private void listarPorStatus(){
        System.out.println("Escolha o status:");
        System.out.println("1 - TODO");
        System.out.println("2 - DOING");
        System.out.println("3 - DONE");
        System.out.print("Opção: ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            TaskStatus status = TaskStatus.TODO;
            if(opcao==2) status=TaskStatus.DOING;
            else if(opcao==3) status=TaskStatus.DONE;

            exibirTarefas(controller.listarPorStatus(status), "Tarefas com status " + status);
        } catch (Exception e){
            System.out.println("Opção inválida!");
        }
    }

    private void excluirTarefa(){
        System.out.println("Insira o ID da tarefa a ser remov: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean sucesso = controller.excluirTarefa(id);
            if(sucesso) System.out.println("Tarefa removida com sucesso!");
            else System.out.println("Tarefa com ID " + id + " não encontrada!");
        } catch(Exception e){
            System.out.println("ID inválido!");
        }
    }

    private void exibirTarefas(List<Task> tarefas, String titulo){
        System.out.println("\n----------");
        System.out.println(titulo);
        System.out.println("----------");
        if (tarefas.isEmpty()) {
            System.out.println("(Nenhuma tarefa encontrada)");
        } else {
            for (Task t : tarefas) {
                System.out.println(t);
                System.out.println("----------");
            }
        }
    }



}
