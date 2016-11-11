package student.record.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student.record.model.User;
import student.record.repository.UserRepository;
import student.record.web.rest.vm.ManagedUserVM;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Inject
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<ManagedUserVM>> getAllUsers()
            throws URISyntaxException {
        List<User> users = userRepository.findAllWithAuthorities();
        List<ManagedUserVM> managedUserVMs = users.stream()
                .map(ManagedUserVM::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(managedUserVMs, HttpStatus.OK);
    }

}
