package org.example.taskmanager.controllers;

import org.example.taskmanager.dtos.CreateLogEntryDto;
import org.example.taskmanager.models.TaskLogTimeEntries;
import org.example.taskmanager.services.TaskLogTimeEntriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logentries")
public class TaskLogTimeEntriesController {
    TaskLogTimeEntriesService taskLogTimeEntriesService;
    public TaskLogTimeEntriesController(TaskLogTimeEntriesService taskLogTimeEntriesService) {
        this.taskLogTimeEntriesService = taskLogTimeEntriesService;
    }

    @PostMapping
    public ResponseEntity<?> createLogEntry(@RequestBody CreateLogEntryDto createLogEntryDto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskLogTimeEntriesService.createEntry(createLogEntryDto));
    }

    @GetMapping("/{taskNumber}")
    public ResponseEntity<?> getAllLogEntriesByTaskNumber(@PathVariable String taskNumber) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskLogTimeEntriesService.getLogEntriesByTaskNumber(taskNumber));
    }


}
