package student.record.repository;

import org.springframework.data.repository.CrudRepository;
import student.record.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByLogin(String login);

    List<User> findByAuthorities_Name(String name);

}
