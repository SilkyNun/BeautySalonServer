package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
