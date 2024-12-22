package org.example.taskmanager.services;

import org.example.taskmanager.dtos.CreateTaskDto;
import org.example.taskmanager.dtos.GetTasksDto;
import org.example.taskmanager.dtos.UpdateTaskDto;
import org.example.taskmanager.models.Employee;
import org.example.taskmanager.models.Task;
import org.example.taskmanager.repositories.EmployeeRepository;
import org.example.taskmanager.repositories.TaskLogTimeEntriesRepository;
import org.example.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskLogTimeEntriesRepository taskLogTimeEntriesRepository;
    public TaskRepository taskRepository;
    public EmployeeRepository employeeRepository;
    public TaskLogTimeEntriesService taskLogTimeEntriesService;
    public TaskServiceImpl(TaskRepository taskRepository, EmployeeRepository employeeRepository, TaskLogTimeEntriesRepository taskLogTimeEntriesRepository, TaskLogTimeEntriesService taskLogTimeEntriesService) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.taskLogTimeEntriesRepository = taskLogTimeEntriesRepository;
        this.taskLogTimeEntriesService = taskLogTimeEntriesService;
    }

    @Override
    public Task createTask(CreateTaskDto createTaskDto) {
        List<Task> tasks = taskRepository.findAll();
        Task newTask = new Task();
        newTask.setTaskDueDate(createTaskDto.getDuedate());
        newTask.setDescription(createTaskDto.getDescription());
        newTask.setStatus("Yet To Start");
        newTask.setPriority(createTaskDto.getPriority());
        newTask.setTitle(createTaskDto.getTitle());
        System.out.println("empid"+createTaskDto.getAssignedto());
        System.out.println(employeeRepository.findByEmpId(createTaskDto.getAssignedto()).get().getEmpId());
        Optional<Employee> emp=employeeRepository.findByEmpId(createTaskDto.getAssignedto());
        System.out.println("result"+emp.get().getEmpId());
        newTask.setEmployee(emp.get());
        System.out.println("result2"+newTask.getEmployee().getEmpId());
        if(tasks.isEmpty()) {
            newTask.setTaskNumber("TASK-1");
            taskRepository.save(newTask);
            return newTask;
        }
        Task lastTask=tasks.get(tasks.size()-1);
        String[] maxTaskIdArr=lastTask.getTaskNumber().split("-");
        int num= Integer.parseInt(maxTaskIdArr[maxTaskIdArr.length-1])+1;
        String maxTaskID="TASK-"+(num);
        newTask.setTaskNumber(maxTaskID);
        taskRepository.save(newTask);
        return newTask;
    }

    @Override
    public List<GetTasksDto> getAllTasks() {
        List<Task> allTasks=taskRepository.findAll();
        List<GetTasksDto> tasksDto=new ArrayList<>();
        for(Task task:allTasks) {
            GetTasksDto getTasksDto= new GetTasksDto();
            getTasksDto.setDuedate(task.getTaskDueDate());
            getTasksDto.setTasknumber(task.getTaskNumber());
            getTasksDto.setDescription(task.getDescription());
            getTasksDto.setPriority(task.getPriority());
            getTasksDto.setTitle(task.getTitle());
            getTasksDto.setStatus(task.getStatus());
            getTasksDto.setId(task.getId());
            getTasksDto.setEmpId(task.getEmployee().getEmpId());
            tasksDto.add(getTasksDto);

        }

        return tasksDto;
    }

    @Override
    public Boolean deleteTaskByTaskNumber(String taskNUmber) {
        taskLogTimeEntriesService.deleteEntryByTaskNumber(taskNUmber);
        System.out.println("log entries deleted");
        Task task=taskRepository.findByTaskNumber(taskNUmber);
        if(task!=null) {
            taskRepository.delete(task);
            return true;
        }
        return false;
    }

    @Override
    public GetTasksDto updateTaskByTaskNumber(String taskNumber, UpdateTaskDto updateTaskDto) {
        Task task=taskRepository.findByTaskNumber(taskNumber);
        System.out.println("updated task number"+updateTaskDto.getEmpId()+updateTaskDto.getDuedate());

        System.out.println("updated task number"+task.getTaskNumber()+task.getEmployee().getEmpId());
          if(updateTaskDto.getTitle()!=null) {
              task.setTitle(updateTaskDto.getTitle());
          }
          if(updateTaskDto.getDuedate()!=null) {
              task.setTaskDueDate(updateTaskDto.getDuedate());
          }
          if(updateTaskDto.getPriority()!=null) {
              task.setPriority(updateTaskDto.getPriority());
          }

          if(!Objects.equals(updateTaskDto.getEmpId(), task.getEmployee().getEmpId())) {
              Optional<Employee> emp= employeeRepository.findByEmpId(updateTaskDto.getEmpId());
              if(emp.isPresent()) {
                  task.setEmployee(emp.get());
              }
          }
        System.out.println("updated task number"+task.getTaskNumber()+task.getEmployee().getEmpId());
          taskRepository.save(task);

        GetTasksDto getTasksDto= GetTasksDto.builder().id(task.getId())
                .tasknumber(task.getTaskNumber())
                .description(task.getDescription())
                .priority(task.getPriority())
                .title(task.getTitle())
                .status(task.getStatus())
                .empId(task.getEmployee().getEmpId())
                .duedate(task.getTaskDueDate())
                .build();
        return getTasksDto;
    }

    @Override
    public GetTasksDto updateTaskStatusByTaskNumber(String taskNumber,String status) {
        Task task=taskRepository.findByTaskNumber(taskNumber);
        if(task!=null) {
            task.setStatus(status);
            taskRepository.save(task);
        }
        GetTasksDto getTasksDto= GetTasksDto.builder().id(task.getId())
                .tasknumber(task.getTaskNumber())
                .description(task.getDescription())
                .priority(task.getPriority())
                .title(task.getTitle())
                .status(task.getStatus())
                .empId(task.getEmployee().getEmpId())
                .duedate(task.getTaskDueDate())
                .build();
        return getTasksDto;
    }

    @Override
    public List<GetTasksDto> getTasksByPriority(String priority) {
        List<Task> allTasks=taskRepository.findAll();
        List<GetTasksDto> tasksDto=new ArrayList<>();
        for(Task task:allTasks) {
            if(task.getPriority().equals(priority)) {
                GetTasksDto getTasksDto= new GetTasksDto();
                getTasksDto.setDuedate(task.getTaskDueDate());
                getTasksDto.setTasknumber(task.getTaskNumber());
                getTasksDto.setDescription(task.getDescription());
                getTasksDto.setPriority(task.getPriority());
                getTasksDto.setTitle(task.getTitle());
                getTasksDto.setStatus(task.getStatus());
                getTasksDto.setId(task.getId());
                getTasksDto.setEmpId(task.getEmployee().getEmpId());
                tasksDto.add(getTasksDto);
                return tasksDto;
            }


        }
        return null;

    }

    @Override
    public List<GetTasksDto> getTasksByStatus(String status) {
        List<Task> allTasks=taskRepository.findAll();
        List<GetTasksDto> tasksDto=new ArrayList<>();
        for(Task task:allTasks) {
            if(task.getStatus().equals(status)) {
                GetTasksDto getTasksDto= new GetTasksDto();
                getTasksDto.setDuedate(task.getTaskDueDate());
                getTasksDto.setTasknumber(task.getTaskNumber());
                getTasksDto.setDescription(task.getDescription());
                getTasksDto.setPriority(task.getPriority());
                getTasksDto.setTitle(task.getTitle());
                getTasksDto.setStatus(task.getStatus());
                getTasksDto.setId(task.getId());
                getTasksDto.setEmpId(task.getEmployee().getEmpId());
                tasksDto.add(getTasksDto);
                return tasksDto;
            }


        }
        return null;
    }

    @Override
    public List<GetTasksDto> getTasksByPriorityAndStatus(String priority, String status) {
        List<Task> allTasks=taskRepository.findAll();
        List<GetTasksDto> tasksDto=new ArrayList<>();
        for(Task task:allTasks) {
            if(task.getPriority().equals(priority) && task.getStatus().equals(status)) {
                GetTasksDto getTasksDto= new GetTasksDto();
                getTasksDto.setDuedate(task.getTaskDueDate());
                getTasksDto.setTasknumber(task.getTaskNumber());
                getTasksDto.setDescription(task.getDescription());
                getTasksDto.setPriority(task.getPriority());
                getTasksDto.setTitle(task.getTitle());
                getTasksDto.setStatus(task.getStatus());
                getTasksDto.setId(task.getId());
                getTasksDto.setEmpId(task.getEmployee().getEmpId());
                tasksDto.add(getTasksDto);
                return tasksDto;
            }


        }
        return null;
    }

    @Override
    public GetTasksDto getTaskByTaskNumber(String taskNumber) {
        Task task=taskRepository.findByTaskNumber(taskNumber);
        GetTasksDto getTasksDto= new GetTasksDto();
        getTasksDto.setDuedate(task.getTaskDueDate());
        getTasksDto.setTasknumber(task.getTaskNumber());
        getTasksDto.setDescription(task.getDescription());
        getTasksDto.setPriority(task.getPriority());
        getTasksDto.setTitle(task.getTitle());
        getTasksDto.setStatus(task.getStatus());
        getTasksDto.setId(task.getId());
        getTasksDto.setEmpId(task.getEmployee().getEmpId());
        return getTasksDto;
    }

    @Override
    public String getNewTaskNumber() {
        List<Task> allTasks=taskRepository.findAll();
        List<Long> list = new ArrayList<>();
        if(allTasks.isEmpty()){
            return "TASK-1";
        }
        // Adding elements in List
        for (int i = 0; i < allTasks.size(); i++) {
            list.add(allTasks.get(i).getId());
        }
        System.out.println("max"+Collections.max(list));
        Long maxId=Collections.max(list);
        Optional<Task> task= taskRepository.findById(maxId);
        for(Task a : allTasks) {
            System.out.println(a.getTaskNumber());
        }
//        Task lastTask=allTasks.get(allTasks.size()-1);
        String[] maxTaskIdArr=task.get().getTaskNumber().split("-");

        System.out.println("after split");
        int num= Integer.parseInt(maxTaskIdArr[maxTaskIdArr.length-1])+1;
        String maxTaskID="TASK-"+(num);
        return maxTaskID;
    }


}
