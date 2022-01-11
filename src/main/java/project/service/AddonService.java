package project.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.Addon;
import project.exceptions.NotFoundException;
import project.repository.AddonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddonService {
    private AddonRepository addonRepository;

    public Addon addAddon(Addon addon) {
        return addonRepository.save(addon);
    }

    public Addon getAddonById(Long id) {
        Optional<Addon> addon = addonRepository.findById(id);
        if (addon.isEmpty()) {
            throw new NotFoundException("Addon with id " + id + " not found");
        }
        return addon.get();
    }

    public List<Addon> getAddonsByIds(List<Long> ids) {
        List<Addon> addons = new ArrayList<>();
        ids.forEach(id -> addons.add(getAddonById(id)));
        return addons;
    }

    public Iterable<Addon> getAllAddons() {
        return addonRepository.findAll();
    }

    public Addon updateAddon(Addon addon) {
        Addon old = getAddonById(addon.getId());
        old.setName(addon.getName());
        old.setPrice(addon.getPrice());
        return addonRepository.save(old);
    }

    public void deleteAddon(Addon addon) {
        addon.getOrders().forEach(order -> order.getAddons().remove(addon));
        addonRepository.delete(addon);
    }

    public void deleteAddonById(Long id) {
        Addon addon = getAddonById(id);
        addon.getOrders().forEach(order -> order.getAddons().remove(addon));
        addonRepository.deleteById(id);
    }
}
