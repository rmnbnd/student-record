package student.record.repository;

import org.springframework.data.repository.CrudRepository;
import student.record.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findOneByUserLogin(String login);

}
