package university.repository;

import university.model.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCourseRepository implements Repository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }
}