package kurstan.dao;

import kurstan.model.Job;

import java.util.List;

/**
 * @author kurstan
 * @created at 22.01.2023 16:04
 */
public interface JobDao {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
