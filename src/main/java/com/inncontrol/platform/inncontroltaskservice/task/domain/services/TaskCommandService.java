package com.inncontrol.platform.inncontroltaskservice.task.domain.services;

import com.inncontrol.platform.inncontroltaskservice.task.domain.model.aggregates.Task;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.CompleteTaskCommand;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.CreateTaskCommand;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.DeleteTaskCommand;
import com.inncontrol.platform.inncontroltaskservice.task.domain.model.commands.StartTaskCommand;

import java.util.Optional;

public interface TaskCommandService {

    Optional<Task> handle(CreateTaskCommand command);

    void handle(StartTaskCommand command);

    void handle(CompleteTaskCommand command);

    void handle(DeleteTaskCommand command);
}
