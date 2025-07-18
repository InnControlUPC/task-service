package com.github.inncontrol.task.application.internal.queryservice;

import java.util.*;

//import com.github.inncontrol.shared.application.internal.outboundedservices.acl.ExternalEmployeeService;
import com.github.inncontrol.task.domain.model.queries.*;
import com.github.inncontrol.task.domain.model.valueobjects.EmployeeIdentifier;
import org.springframework.stereotype.Service;

import com.github.inncontrol.task.domain.model.aggregates.Task;
import com.github.inncontrol.task.domain.services.TaskQueryService;
import com.github.inncontrol.task.infrastructure.persistence.jpa.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;
    //private final ExternalEmployeeService employeeService;

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.id());
    }

    @Override
    public List<Task> handle(GetAllTaskQuery query) {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> handle(GetAllTaskForEmployeeQuery query) {
        var employeeId = new EmployeeIdentifier(0L);//employeeService.fetchEmployeeIdentifierByEmail(query.employeeEmail()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return taskRepository.findAllByEmployee(employeeId);
    }

    @Override
    public List<Task> handle(GetAllTaskInWeekForEmployeeQuery query) {
        var employeeId = new EmployeeIdentifier(0L);//employeeService.fetchEmployeeIdentifierByEmail(query.employeeEmail()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        // Here we initialize a GregorianCalendar instance
        var calendar = new GregorianCalendar();

        // set the calendar to monday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // set the time to 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        Date start = calendar.getTime();

        // set the calendar to sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // set the time to 23:59:59
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        Date end = calendar.getTime();

        return taskRepository.finAllInDateRangeByEmployee(employeeId, start, end);
    }

    @Override
    public List<Task> handle(GetAllTaskFromDatesForEmployeeQuery query) {
        var employeeId = new EmployeeIdentifier(0L);//employeeService.fetchEmployeeIdentifierByEmail(query.employeeEmail()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return taskRepository.finAllInDateRangeByEmployee(employeeId, query.startDate(), query.endDate());
    }

    @Override
    public List<Task> handle(GetAllTaskForManagerQuery query) {
        var employeeId = new EmployeeIdentifier(0L);//employeeService.fetchEmployeeIdentifierByEmail(query.employeeEmail()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return taskRepository.findAllByManager(employeeId);
    }

    @Override
    public List<Task> handle(GetAllTaskInWeekForManagerQuery query) {
        var employeeId = new EmployeeIdentifier(0L);//employeeService.fetchEmployeeIdentifierByEmail(query.employeeEmail()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        // Here we initialize a GregorianCalendar instance
        var calendar = new GregorianCalendar();

        // set the calendar to monday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // set the time to 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        Date start = calendar.getTime();

        // set the calendar to sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // set the time to 23:59:59
        calendar.set(Calendar.HOUR_OF_DAY, 23);

        Date end = calendar.getTime();

        return taskRepository.finAllInDateRangeByManager(employeeId, start, end);
    }

    @Override
    public List<Task> handle(GetAllTaskFromDatesForManagerQuery query) {
        var employeeId = new EmployeeIdentifier(0L);//employeeService.fetchEmployeeIdentifierByEmail(query.employeeEmail()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return taskRepository.finAllInDateRangeByManager(employeeId, query.startDate(), query.endDate());
    }

}
