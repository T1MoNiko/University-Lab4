package university.service;

import university.model.Enrollment;
import university.repository.Repository;

public class EnrollmentService {

    private final Repository repository;

    public EnrollmentService(Repository repository) {
        this.repository = repository;
    }

    public void enroll(Enrollment enrollment) {

        for (Enrollment existingEnrollment : repository.findAll()) {

            boolean sameStudent =
                    existingEnrollment.getStudent().getId()
                            == enrollment.getStudent().getId();

            boolean sameCourse =
                    existingEnrollment.getCourse().getId()
                            == enrollment.getCourse().getId();

            if (sameStudent && sameCourse) {
                throw new IllegalStateException(
                        "Student is already enrolled in this course"
                );
            }
        }

        repository.save(enrollment);
    }
}