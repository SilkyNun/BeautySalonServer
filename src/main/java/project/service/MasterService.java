package project.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.Master;
import project.exceptions.NotFoundException;
import project.repository.MasterRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service @AllArgsConstructor
public class MasterService {
    private MasterRepository masterRepository;


    public Master addMaster(Master master) {
        master.setCreatedAt(LocalDateTime.now());
        master.setUpdatedAt(LocalDateTime.now());
        return masterRepository.save(master);
    }

    public Iterable<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    public Master getMasterById(Long id) throws NotFoundException {
        Optional<Master> master = masterRepository.findById(id);
        if (master.isEmpty()) {
            throw new NotFoundException(String.format("Master with id %d not found", id));
        }
        return master.get();
    }

    public Master updateMaster(Master master) {
        Master masterUpd = getMasterById(master.getId());
        masterUpd.setName(master.getName() == null ? masterUpd.getName() : master.getName());
        masterUpd.setPercent(master.getPercent() == null ? masterUpd.getPercent() : master.getPercent());
        masterUpd.setTel(master.getTel() == null ? masterUpd.getTel() : master.getTel());
        masterUpd.setOrders(master.getOrders().isEmpty() ? masterUpd.getOrders() : master.getOrders());
        masterUpd.setAccounts(master.getAccounts().isEmpty() ? masterUpd.getAccounts() : master.getAccounts());
        masterUpd.setCreatedAt(master.getCreatedAt() == null ? masterUpd.getCreatedAt() : master.getCreatedAt());
        masterUpd.setUpdatedAt(LocalDateTime.now());
        return masterRepository.save(masterUpd);
    }

    public void deleteMaster(Master master) {
        masterRepository.delete(master);
    }

}
