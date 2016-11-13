package student.record.repository;

import org.springframework.data.repository.CrudRepository;
import student.record.model.Student;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findOneByUserLogin(String login);

}
