package university;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;
import university.repository.Repository;
import university.repository.InMemoryCourseRepository;
import university.service.EnrollmentService;

public class Main {

    public static void main(String[] args) {

        Repository repository =
                new InMemoryCourseRepository();

        EnrollmentService service =
                new EnrollmentService(repository);

        Student student =
                new Student(1, "Ivan Ivanov");

        Course course =
                new Course(1, "Java Programming");

        Enrollment firstEnrollment =
                new Enrollment(student, course);

        Enrollment secondEnrollment =
                new Enrollment(student, course);

        service.enroll(firstEnrollment);

        System.out.println(
                "First enrollment successful"
        );

        try {

            service.enroll(secondEnrollment);

            System.out.println(
                    "Second enrollment successful"
            );

        } catch (IllegalStateException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}