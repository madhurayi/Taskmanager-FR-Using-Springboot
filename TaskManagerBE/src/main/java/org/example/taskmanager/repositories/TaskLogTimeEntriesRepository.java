package org.example.taskmanager.repositories;

import org.example.taskmanager.models.TaskLogTimeEntries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskLogTimeEntriesRepository extends JpaRepository<TaskLogTimeEntries, Long> {
        public TaskLogTimeEntries save(TaskLogTimeEntries taskLogTimeEntries);

        @Query("select b from TaskLogTimeEntries b inner join Task r on b.task.id = r.id where r.taskNumber=:taskNumber")
        public List<TaskLogTimeEntries> findAllByTaskNumber(String taskNumber);

//        @Query("delete from TaskLogTimeEntries a where a.task.tasknumber=:tasknumber")
//        public void deleteAllByTaskNumber(String tasknumber);

//        void deleteByTaskNumber(String tasknumber);
}
