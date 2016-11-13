package student.record.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student.record.model.Student;
import student.record.service.StudentService;
import student.record.web.rest.vm.ManagedUserVM;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private StudentService studentService;

    @GetMapping("/users")
    public ResponseEntity<List<ManagedUserVM>> getAllStudents()
            throws URISyntaxException {
        List<Student> students = studentService.findAll();
        List<ManagedUserVM> managedUserVMs = students.stream()
                .map(ManagedUserVM::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(managedUserVMs, HttpStatus.OK);
    }

    @GetMapping("/users/{login}")
    public ResponseEntity<ManagedUserVM> getUser(@PathVariable String login) {
        return studentService.findByLogin(login)
                .map(ManagedUserVM::new)
                .map(managedUserVM -> new ResponseEntity<>(managedUserVM, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
