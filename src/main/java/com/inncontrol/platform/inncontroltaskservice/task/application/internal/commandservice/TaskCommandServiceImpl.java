package com.inncontrol.platform.inncontroltaskservice.task.application.internal.commandservice;

//import com.inncontrol.platform.inncontroltaskservice.shared.application.internal.outboundedservices.acl.ExternalEmployeeService;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.aggregates.Task;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.CompleteTaskCommand;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.CreateTaskCommand;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.DeleteTaskCommand;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.StartTaskCommand;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.valueobjects.EmployeeIdentifier;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.valueobjects.TaskInformation;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.valueobjects.TaskStatus;
import com.inncontrol.platform.inncontroltaskservice.task.domain.services.TaskCommandService;
import com.inncontrol.platform.inncontroltaskservice.task.infrastructure.persistence.jpa.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;
    //private final ExternalEmployeeService employeeService;

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {
        //var employeeId = employeeService.fetchEmployeeIdentifierByEmail(command.employeeEmail());

        var task = new Task(
                new TaskInformation(command.title(), command.description()),
                TaskStatus.SCHEDULED,
                command.dueDate(),
                new EmployeeIdentifier(0L),
                command.employeeEmail()
        );
        return Optional.of(taskRepository.save(task));
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
