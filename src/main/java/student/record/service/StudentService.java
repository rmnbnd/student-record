package student.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.record.model.Student;
import student.record.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student findByLogin(String login) {
        return studentRepository.findOneByUserLogin(login);
    }

    public void removeLink(String login, Long linkId) {
        Student student = studentRepository.findOneByUserLogin(login);
        if (student == null) {
            return;
        }
        student.setLinks(student.getLinks().stream()
                .filter(link -> !link.getId().equals(linkId))
                .collect(Collectors.toList())
        );
        studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }
}
