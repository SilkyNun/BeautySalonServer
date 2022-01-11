package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Addon;

public interface AddonRepository extends CrudRepository<Addon, Long> {
}
