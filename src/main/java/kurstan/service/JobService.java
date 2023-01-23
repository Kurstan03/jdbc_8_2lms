package kurstan.service;

import kurstan.model.Job;

import java.util.List;

/**
 * @author kurstan
 * @created at 22.01.2023 16:13
 */
public interface JobService {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
