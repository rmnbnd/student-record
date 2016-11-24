package student.record.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student.record.service.StudentService;

@RestController
@RequestMapping("/api")
public class LinkResource {

    @Autowired
    private StudentService studentService;

    @DeleteMapping("/links/{login}/{linkId}")
    public ResponseEntity<Void> deleteLink(@PathVariable String login, @PathVariable String linkId) {
        studentService.removeLink(login, Long.valueOf(linkId));

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-app-alert", "A link is deleted");
        headers.add("X-app-params", linkId);
        return ResponseEntity.ok().headers(headers).build();
    }

}
