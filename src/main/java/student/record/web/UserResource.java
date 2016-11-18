package student.record.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student.record.model.Link;
import student.record.model.Student;
import student.record.service.StudentService;
import student.record.service.UserService;
import student.record.web.rest.vm.ManagedUserVM;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

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
        Student student = studentService.findByLogin(login);
        ManagedUserVM dto = new ManagedUserVM(student);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<ManagedUserVM> updateUser(@RequestBody ManagedUserVM managedUserVM) {
        Student student = studentService.findByLogin(managedUserVM.getLogin().toLowerCase());
        if (managedUserVM.getLinks() != null) {
            student.setLinks(managedUserVM.getLinks().stream().map(linkDTO -> {
                Link link = new Link();
                link.setUrl(linkDTO.getUrl());
                return link;
            }).collect(Collectors.toList()));
        }
        studentService.updateStudent(student);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-applicationApp-alert", "A user is updated.");
        headers.add("X-applicationApp-params", managedUserVM.getLogin());
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ManagedUserVM(studentService.findByLogin(managedUserVM.getLogin().toLowerCase())));
    }

}
