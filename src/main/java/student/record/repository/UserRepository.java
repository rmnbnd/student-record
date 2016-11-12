package student.record.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import student.record.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByLogin(String login);

    @Query(value = "select distinct user from User user left join fetch user.authorities auth where auth.name = :role")
    List<User> findUserByRole(@Param("role") String role);

}
