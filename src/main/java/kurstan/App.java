package kurstan;

import kurstan.model.Employee;
import kurstan.model.Job;
import kurstan.service.EmployeeService;
import kurstan.service.JobService;
import kurstan.service.impl.EmployeeServiceImpl;
import kurstan.service.impl.JobServiceImpl;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        JobService jobService = new JobServiceImpl();
//                       //create
//        jobService.createJobTable();
//                       //add
//        jobService.addJob(
//                new Job("Instructor", "Java", "Backend developer", 2)
//        );
//        jobService.addJob(
//                new Job("Mentor", "Java", "Backend developer", 1)
//        );
//        jobService.addJob(
//                new Job("Management", "Project manager", "manager", 2)
//        );
//        jobService.addJob(
//                new Job("Director", "director", "team lead", 5)
//        );
//        jobService.addJob(
//                new Job("Instructor", "JavaScript", "Frontend developer", 3)
//        );

                                //get job by id
//        System.out.println(jobService.getJobById(3L));

//                              //sort by experience
//        jobService.sortByExperience("desc").forEach(System.out::println);

//                              // get job by employee id
//        System.out.println(jobService.getJobByEmployeeId(2L));

                                //delete description column
//        jobService.deleteDescriptionColumn();
//_______________________________________________________________________________________________


        EmployeeService employeeService = new EmployeeServiceImpl();
//                         // create
//        employeeService.createEmployee();
//                         //add
//        employeeService.addEmployee(
//                new Employee("Aijamal", "Asangazieva", 20, "a@gmil.com", 1)
//        );
//        employeeService.addEmployee(
//                new Employee("Maksat", "Akylbekov", 25, "maksat@gmil.com", 5)
//        );
//        employeeService.addEmployee(
//                new Employee("Muhammed", "Toichubai uulu", 21, "m@gmil.com", 2)
//        );
//        employeeService.addEmployee(
//                new Employee("Rahim", "Kurbanov", 19, "r@gmil.com", 2)
//        );
//        employeeService.addEmployee(
//                new Employee("Dinara", "Bakirova", 27, "d@gmil.com", 4)
//        );
//        employeeService.addEmployee(
//                new Employee("Adinai", "Sharshekeeva", 23, "adinai@gmil.com", 3)
//        );

                             //get all employees
//        employeeService.getAllEmployees().forEach(System.out::println);

                             //find by email
//        System.out.println(employeeService.findByEmail("r@gmil.com"));

                              //get employee by id
//        for (Map.Entry<Employee, Job> employeeJobEntry : employeeService.getEmployeeById(3L).entrySet()) {
//            System.out.println(employeeJobEntry);
//        }

                               //get employee by position
//        System.out.println(employeeService.getEmployeeByPosition("Management"));
//
//
                               // update employee
//        employeeService.updateEmployee(
//                6L,
//                new Employee("Dinara", "Bakirova", 27, "dd@gmil.com", 4)
//        );

                                //clean table
//        employeeService.cleanTable();

                                // drop table
//        employeeService.dropTable();
    }
}
