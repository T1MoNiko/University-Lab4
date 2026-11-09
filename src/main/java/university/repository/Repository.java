package university.repository;

import university.model.Enrollment;

import java.util.List;

public interface Repository {

    void save(Enrollment enrollment);

    List<Enrollment> findAll();
}