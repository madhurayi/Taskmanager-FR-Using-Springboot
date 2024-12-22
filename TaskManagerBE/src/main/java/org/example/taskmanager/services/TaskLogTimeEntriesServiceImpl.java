package org.example.taskmanager.services;

import org.example.taskmanager.dtos.CreateLogEntryDto;
import org.example.taskmanager.dtos.GetLogEntriesDto;
import org.example.taskmanager.models.Employee;
import org.example.taskmanager.models.Task;
import org.example.taskmanager.models.TaskLogTimeEntries;
import org.example.taskmanager.repositories.EmployeeRepository;
import org.example.taskmanager.repositories.TaskLogTimeEntriesRepository;
import org.example.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskLogTimeEntriesServiceImpl implements TaskLogTimeEntriesService {
     TaskLogTimeEntriesRepository taskLogTimeEntriesRepository;
     TaskRepository taskRepository;
     EmployeeRepository employeeRepository;
     public TaskLogTimeEntriesServiceImpl(TaskLogTimeEntriesRepository taskLogTimeEntriesRepository,TaskRepository taskRepository,EmployeeRepository employeeRepository) {
         this.taskLogTimeEntriesRepository = taskLogTimeEntriesRepository;
         this.taskRepository = taskRepository;
         this.employeeRepository = employeeRepository;
     }

    @Override
    public TaskLogTimeEntries createEntry(CreateLogEntryDto createLogEntryDto) {
       Task task=taskRepository.findByTaskNumber(createLogEntryDto.getTaskNumber());
        System.out.println("task number"+task.getTaskNumber());
        Optional<Employee> emp= employeeRepository.findByEmpId(createLogEntryDto.getLogEntryUserId());
         TaskLogTimeEntries newEntry = new TaskLogTimeEntries();
        if(emp.isPresent()){
            newEntry.setLogEntryUser(emp.get());
        }
        newEntry.setLogDate(createLogEntryDto.getLogDate());
         newEntry.setLogTime(createLogEntryDto.getLogTime());
         newEntry.setLogNote(createLogEntryDto.getLogNote());
         newEntry.setTask(task);
        System.out.println("task number"+newEntry.getTask().getTaskNumber());
         taskLogTimeEntriesRepository.save(newEntry);
        return newEntry;
    }

    @Override
    public List<GetLogEntriesDto> getLogEntriesByTaskNumber(String taskNumber) {
        List<TaskLogTimeEntries> logEntries=taskLogTimeEntriesRepository.findAllByTaskNumber(taskNumber);
        Task task= taskRepository.findByTaskNumber(taskNumber);
        String emp=task.getEmployee().getEmpId();
        List<GetLogEntriesDto> logEntriesDto=new ArrayList<>();
        for(TaskLogTimeEntries entry:logEntries){
            GetLogEntriesDto entryDto=new GetLogEntriesDto();
            entryDto.setId(entry.getId());
            entryDto.setLogDate(entry.getLogDate());
            entryDto.setLogTime(entry.getLogTime());
            entryDto.setLogNote(entry.getLogNote());
            entryDto.setTaskNumber(entry.getTask().getTaskNumber());
            entryDto.setLogUser(emp);
            logEntriesDto.add(entryDto);
        }
        return logEntriesDto;
    }

    @Override
    public Boolean deleteEntryByTaskNumber(String taskNumber) {
        Task task=taskRepository.findByTaskNumber(taskNumber);
        if(task!=null) {
            System.out.println("task");
            List<TaskLogTimeEntries> id=taskLogTimeEntriesRepository.findAllByTaskNumber(taskNumber);
            for(TaskLogTimeEntries entry:id){
                taskLogTimeEntriesRepository.delete(entry);
            }
            return true;
        }
        return false;
    }


}
