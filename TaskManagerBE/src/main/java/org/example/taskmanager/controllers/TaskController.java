package org.example.taskmanager.controllers;

import org.example.taskmanager.dtos.CreateTaskDto;
import org.example.taskmanager.dtos.UpdateTaskDto;
import org.example.taskmanager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDto createTaskDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(createTaskDto));
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTask(@RequestParam String taskNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.deleteTaskByTaskNumber(taskNumber));
    }

    @PatchMapping("/{taskNumber}")
    public ResponseEntity<?> updateTaskByTaskNumber(@PathVariable String taskNumber, @RequestBody UpdateTaskDto updateTaskDto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTaskByTaskNumber(taskNumber, updateTaskDto));
    }

    @PatchMapping("/changestatus/{taskNumber}")
    public ResponseEntity<?> updateTaskStatusByTaskNumber(@PathVariable String taskNumber, @RequestBody String status) {
        String status1=status.replace("\"","");
        System.out.println("status from fe"+status1);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTaskStatusByTaskNumber(taskNumber, status1));
    }

    @GetMapping("/priority")
    public ResponseEntity<?> getAllTasksByPriority(@RequestParam(name = "priority") String priority) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByPriority(priority));
    }

    @GetMapping("/status")
    public ResponseEntity<?> getAllTasksByStatus(@RequestParam(name="status") String status) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByStatus(status));
    }

    @GetMapping("/statusAndPriority")
    public ResponseEntity<?> getAllTasksByStatusAndPriority(@RequestParam(name="priority") String priority, @RequestParam(name="status") String status) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByPriorityAndStatus(priority,status));
    }
    @GetMapping("/{taskNumber}")
    public ResponseEntity<?> getTaskByTaskNumber(@PathVariable String taskNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskByTaskNumber(taskNumber));
    }

    @GetMapping("newTaskNumber")
    public ResponseEntity<?> getNewTaskNumber(){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getNewTaskNumber());
    }
}
