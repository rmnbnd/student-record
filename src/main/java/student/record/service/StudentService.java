package student.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.record.model.Student;
import student.record.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public Optional<Student> findByLogin(String login) {
        return studentRepository.findOneByUserLogin(login);
    }
}
