package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Master;

import java.util.List;

public interface MasterRepository extends CrudRepository<Master, Long> {
    List<Master> findByName(String name);
}
