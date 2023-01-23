package kurstan.dao.impl;

import kurstan.config.DatabaseConnection;
import kurstan.dao.JobDao;
import kurstan.model.Job;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author kurstan
 * @created at 22.01.2023 16:04
 */
public class JobDaoImpl implements JobDao {
    private Connection connection;

    public JobDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createJobTable() {
        String query = """
                CREATE TABLE if NOT EXISTS jobs (
                id SERIAL PRIMARY KEY,
                position VARCHAR,
                profession VARCHAR,
                description  VARCHAR,
                experience INTEGER 
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addJob(Job job) {
        String query = """
                INSERT INTO jobs (position, profession, description, experience) 
                VALUES (?, ?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        String query = """
                SELECT * FROM jobs WHERE id = ?""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return getMyJob(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Job getMyJob(ResultSet resultSet) {
        Job job = new Job();
        try {
            while (resultSet.next()) {
                job.setId(resultSet.getLong(1));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));
            }
            return job;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        String query = null;
        if (ascOrDesc.equalsIgnoreCase("asc")){
            query = """
                SELECT * FROM jobs ORDER BY experience
                """;
        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
            query = """
                SELECT * FROM jobs ORDER BY experience DESC
                """;
        }

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            List<Job> jobs = new LinkedList<>();
            while (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getLong(1));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));

                jobs.add(job);
            }
            return jobs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        String query = """
                SELECT j.* FROM employees
                JOIN jobs j ON employees.job_id = j.id
                WHERE employees.id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getMyJob(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = """
                ALTER TABLE jobs DROP COLUMN description
                """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
