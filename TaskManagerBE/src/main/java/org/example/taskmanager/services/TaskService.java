package org.example.taskmanager.services;

import org.example.taskmanager.dtos.CreateTaskDto;
import org.example.taskmanager.dtos.GetTasksDto;
import org.example.taskmanager.dtos.UpdateTaskDto;
import org.example.taskmanager.models.Task;

import java.util.List;

public interface TaskService {
    Task createTask(CreateTaskDto createTaskDto);
    List<GetTasksDto> getAllTasks();
    public Boolean deleteTaskByTaskNumber(String taskNUmber);
    public GetTasksDto updateTaskByTaskNumber(String taskNumber, UpdateTaskDto updateTaskDto);
    public GetTasksDto updateTaskStatusByTaskNumber(String taskNumber,String status);
    public List<GetTasksDto> getTasksByPriority(String priority);
    public List<GetTasksDto> getTasksByStatus(String status);
    public List<GetTasksDto> getTasksByPriorityAndStatus(String priority, String status);
    public GetTasksDto getTaskByTaskNumber(String taskNumber);
    public String getNewTaskNumber();
}
