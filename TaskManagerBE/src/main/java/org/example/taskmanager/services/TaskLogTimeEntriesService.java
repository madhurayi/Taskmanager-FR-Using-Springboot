package org.example.taskmanager.services;

import org.example.taskmanager.dtos.CreateLogEntryDto;
import org.example.taskmanager.dtos.GetLogEntriesDto;
import org.example.taskmanager.models.TaskLogTimeEntries;

import java.util.List;

public interface TaskLogTimeEntriesService {
    public TaskLogTimeEntries createEntry(CreateLogEntryDto createLogEntryDto);
    public List<GetLogEntriesDto> getLogEntriesByTaskNumber(String taskNumber);

    public Boolean deleteEntryByTaskNumber(String taskNumber);
}
