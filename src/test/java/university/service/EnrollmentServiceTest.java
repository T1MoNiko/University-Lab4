package university.service;

import org.junit.jupiter.api.Test;
import university.model.Course;
import university.model.Enrollment;
import university.model.Student;
import university.repository.Repository;
import university.repository.InMemoryCourseRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnrollmentServiceTest {

    @Test
    void studentCanEnrollInCourse() {
        Repository repository =
                new InMemoryCourseRepository();

        EnrollmentService service =
                new EnrollmentService(repository);

        Student student =
                new Student(1, "Ivan");

        Course course =
                new Course(1, "Java");

        Enrollment enrollment =
                new Enrollment(student, course);

        service.enroll(enrollment);

        assertEquals(
                1,
                repository.findAll().size()
        );
    }

    @Test
    void studentCanEnrollInDifferentCourses() {

        Repository repository =
                new InMemoryCourseRepository();

        EnrollmentService service =
                new EnrollmentService(repository);

        Student student =
                new Student(1, "Ivan");

        Course javaCourse =
                new Course(1, "Java");

        Course databaseCourse =
                new Course(2, "Databases");

        service.enroll(
                new Enrollment(student, javaCourse)
        );

        service.enroll(
                new Enrollment(student, databaseCourse)
        );

        assertEquals(
                2,
                repository.findAll().size()
        );
    }

    @Test
    void studentCannotEnrollInSameCourseTwice() {

        Repository repository =
                new InMemoryCourseRepository();

        EnrollmentService service =
                new EnrollmentService(repository);

        Student student =
                new Student(1, "Ivan");

        Course course =
                new Course(1, "Java");

        service.enroll(
                new Enrollment(student, course)
        );

        assertThrows(
                IllegalStateException.class,
                () -> service.enroll(
                        new Enrollment(student, course)
                )
        );
    }

    @Test
    void studentCannotEnrollWithNullEnrollment() {

        Repository repository =
                new InMemoryCourseRepository();

        EnrollmentService service =
                new EnrollmentService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.enroll(null)
        );

        assertEquals(0, repository.findAll().size());
    }
}