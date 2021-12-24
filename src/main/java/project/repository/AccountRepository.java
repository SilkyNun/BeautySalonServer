package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Account;
import project.entity.Master;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
