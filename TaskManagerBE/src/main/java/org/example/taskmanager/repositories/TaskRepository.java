package org.example.taskmanager.repositories;

import org.example.taskmanager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByEmployee_EmpId(String empId);
    public Task save(Task task);
    public List<Task> findAll();
    public Task findByTaskNumber(String taskNumber);
//    @Query("select t from Task t order by t.id")
//    public List<Task> getAllByOrderById();
}
