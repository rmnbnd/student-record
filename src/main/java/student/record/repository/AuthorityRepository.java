package student.record.repository;


import org.springframework.data.repository.CrudRepository;
import student.record.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
