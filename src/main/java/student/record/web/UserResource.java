package student.record.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student.record.model.Authority;
import student.record.model.User;
import student.record.repository.UserRepository;
import student.record.web.rest.vm.ManagedUserVM;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Inject
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<ManagedUserVM>> getAllUsers()
            throws URISyntaxException {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName("ROLE_USER");
        authorities.add(authority);
        List<User> users = userRepository.findByAuthorities_Name("ROLE_USER");
        List<ManagedUserVM> managedUserVMs = users.stream()
                .map(ManagedUserVM::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(managedUserVMs, HttpStatus.OK);
    }

}
