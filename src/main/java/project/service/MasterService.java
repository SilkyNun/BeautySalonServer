package project.service;

import lombok.AllArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;
import project.entity.Master;
import project.exceptions.NotFoundException;
import project.repository.MasterRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service @AllArgsConstructor
public class MasterService {
    private MasterRepository masterRepository;

    public enum SortOrder {
        ASC,
        DESC;

        public static SortOrder toSortOrder(String order) {
            if (order.equalsIgnoreCase(SortOrder.DESC.name())) {
                return SortOrder.DESC;
            }
            return SortOrder.ASC;
        }
    }

    public enum SortType {
        PERCENT,
        DATE;

        public static SortType toSortType(String type) {
            if (type.equalsIgnoreCase(SortType.DATE.name())) {
                return SortType.DATE;
            }
            return SortType.PERCENT;
        }
    }

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

    public Iterable<Master> getAllMastersSorted(SortType type) {
        return ((List<Master>) masterRepository.findAll()).stream()
                .sorted(defineSortType(type))
                .toList();
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

    public void deleteMasterById(Long id) {
        masterRepository.deleteById(id);
    }

    private Comparator<Master> defineSortType(SortType type) {
        return switch (type) {
            case DATE -> Comparator.comparing(Master::getCreatedAt).reversed();
            case PERCENT -> Comparator.comparing(Master::getPercent).reversed();
        };
    }

}
