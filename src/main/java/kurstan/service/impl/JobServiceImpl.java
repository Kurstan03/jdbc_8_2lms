package kurstan.service.impl;

import kurstan.dao.JobDao;
import kurstan.dao.impl.JobDaoImpl;
import kurstan.model.Job;
import kurstan.service.JobService;

import java.util.List;

/**
 * @author kurstan
 * @created at 22.01.2023 16:15
 */
public class JobServiceImpl implements JobService {
    JobDao jobDao = new JobDaoImpl();
    @Override
    public void createJobTable() {
        jobDao.createJobTable();
        System.out.println("Table jobs created");
    }

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);
        System.out.println("job by position " + job.getPosition() + " added");
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();
        System.out.println("Column 'description' deleted");
    }
}
