
package university.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import university.model.Enrollment;
import university.repository.Repository;

public class EnrollmentService {

    private static final Logger logger =
            LoggerFactory.getLogger(EnrollmentService.class);

    private final Repository repository;

    public EnrollmentService(Repository repository) {
        this.repository = repository;
    }

    public void enroll(Enrollment enrollment) {

        if (enrollment == null) {
            logger.error("Enrollment failed: enrollment is null");

            throw new IllegalArgumentException(
                    "Enrollment must not be null"
            );
        }

        for (Enrollment existingEnrollment : repository.findAll()) {

            boolean sameStudent =
                    existingEnrollment.getStudent().getId()
                            == enrollment.getStudent().getId();

            boolean sameCourse =
                    existingEnrollment.getCourse().getId()
                            == enrollment.getCourse().getId();

            if (sameStudent && sameCourse) {

                logger.warn(
                        "Student {} is already enrolled in course {}",
                        enrollment.getStudent().getId(),
                        enrollment.getCourse().getId()
                );

                throw new IllegalStateException(
                        "Student is already enrolled in this course"
                );
            }
        }

        repository.save(enrollment);

        logger.info(
                "Student {} successfully enrolled in course {}",
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId()
        );
    }
}
