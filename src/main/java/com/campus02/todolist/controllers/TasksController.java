package com.campus02.todolist.controllers;

import com.campus02.todolist.model.tasks.TasksService;
import com.campus02.todolist.model.tasks.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService taskService;

    public TasksController(TasksService taskService) {
        this.taskService = taskService;
    }

    // FETCH
    @GetMapping("/fetch")
    public List<FetchTaskDto> fetchTaskInfo(@RequestHeader Integer userId) {
        return this.taskService.fetchTasksFromUser(userId).stream().map(FetchTaskDto::from).collect(Collectors.toList());
    }

    // SYNCHRONIZE
    @PostMapping("/sync")
    public SyncResponseDto synchronizeTasks (@RequestHeader Integer userId, @RequestBody SyncRequestDto syncRequest) {
        return this.taskService.synchronizeTasksFromUser(syncRequest, userId);
    }

}
