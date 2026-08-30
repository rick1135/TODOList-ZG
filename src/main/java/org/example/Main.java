package org.example;

import org.example.controller.TaskController;
import org.example.repository.TaskRepository;
import org.example.service.TaskService;
import org.example.view.ConsoleView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        TaskController controller = new TaskController(service);

        ConsoleView view = new ConsoleView(controller);
        view.iniciar();
    }
}