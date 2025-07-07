package com.github.inncontrol.task.application.internal.commandservice;

import java.util.Optional;

import com.github.inncontrol.shared.application.internal.outboundedservices.acl.ExternalEmployeeService;
import com.github.inncontrol.shared.interfaces.kafka.resource.PushNotificationResource;
import com.github.inncontrol.task.domain.model.commands.DeleteTaskCommand;
import com.github.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.domain.model.commands.CompleteTaskCommand;
import com.github.inncontrol.task.domain.model.commands.CreateTaskCommand;
import com.github.inncontrol.task.domain.model.commands.StartTaskCommand;
import com.github.inncontrol.task.domain.model.valueobjects.TaskInformation;
import com.github.inncontrol.task.domain.model.valueobjects.TaskStatus;
import com.github.inncontrol.task.domain.services.TaskCommandService;
import com.github.inncontrol.task.infrastructure.persistence.jpa.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;

    private final ExternalEmployeeService employeeService;

    private final KafkaTemplate<String, PushNotificationResource> kafkaTemplate;

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {
        var employeeId = employeeService.fetchEmployeeIdentifierByEmail(command.employeeEmail());
        if (employeeId.isEmpty()) {
            throw new IllegalArgumentException("Employee with email not found");
        }
        var managerId = employeeService.fetchEmployeeIdentifierByEmail(command.managerEmail());
        if (managerId.isEmpty()) {
            throw new IllegalArgumentException("Employee with email not found");
        }
        var task = new Task(
                new TaskInformation(command.title(), command.description()),
                TaskStatus.SCHEDULED,
                command.dueDate(),
                new EmployeeIdentifier(employeeId.get().id()),
                new EmployeeIdentifier(managerId.get().id()),
                command.employeeEmail(),
                command.managerEmail()
        );

        PushNotificationResource notification = new PushNotificationResource(
                "",
                command.employeeEmail(),
                "InnControl - Nueva tarea asignada",
                formatEmail(
                        command.title(),
                        command.description(),
                        command.dueDate().toString()
                ),
                "none",
                false
        );

        kafkaTemplate.send("notifications", notification);

        return Optional.of(taskRepository.save(task));
    }

    private String formatEmail(String title, String descriptio, String dueDate) {

        String template = """
                    <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            line-height: 1.6;
                            color: #333;
                        }
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                            border: 1px solid #ddd;
                            border-radius: 5px;
                            background-color: #f9f9f9;
                        }
                        .header {
                            text-align: center;
                            padding: 10px 0;
                            background-color: #4CAF50;
                            color: white;
                            border-radius: 5px 5px 0 0;
                        }
                        .content {
                            margin: 20px 0;
                        }
                        .footer {
                            text-align: center;
                            font-size: 12px;
                            color: #777;
                            margin-top: 20px;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>InnControl - Nueva tarea asignada</h1>
                        </div>
                        <div class="content">
                            <p>Hola,</p>
                            <p>Se te ha asignado una nueva tarea en el sistema <strong>InnControl</strong>.</p>
                            <p><strong>Título:</strong> {{title}}</p>
                            <p><strong>Descripción:</strong> {{description}}</p>
                            <p><strong>Fecha de vencimiento:</strong> {{dueDate}}</p>
                            <p>Por favor, revisa la tarea en el sistema y actúa en consecuencia.</p>
                        </div>
                        <div class="footer">
                            <p>Este es un correo automático, por favor no respondas.</p>
                        </div>
                    </div>
                </body>
                </html>
                """;
        return template
                .replace("{{title}}", title)
                .replace("{{description}}", descriptio)
                .replace("{{dueDate}}", dueDate);
    }

    @Override
    public void handle(StartTaskCommand command) {
        var task = taskRepository.findById(command.id());
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task with id not found");
        }
        var taskObject = task.get();
        taskObject.start();
        taskRepository.save(taskObject);
    }

    @Override
    public void handle(CompleteTaskCommand command) {
        var task = taskRepository.findById(command.id());
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task with id not found");
        }
        var taskObject = task.get();
        taskObject.complete();
        taskRepository.save(taskObject);
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        taskRepository.deleteById(command.id());
    }
}
