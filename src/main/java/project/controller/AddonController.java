package project.controller;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import project.entity.Addon;
import project.exceptions.NotFoundException;
import project.service.AddonService;

import javax.validation.Valid;
import java.util.ResourceBundle;

@RestController
@AllArgsConstructor
@RequestMapping("/addons")
public class AddonController {
    private AddonService addonService;

    @GetMapping
    public ResponseEntity<?> getAllAddons() {
        return ResponseEntity.ok().body(addonService.getAllAddons());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAddonById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(addonService.getAddonById(id));
    }

    @PostMapping
    public ResponseEntity<Addon> addAddon(@Valid @RequestBody Addon addon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addonService.addAddon(addon));
    }

    @PutMapping
    public ResponseEntity<Addon> updateAddon(@Valid @RequestBody Addon addon) {
        return ResponseEntity.ok().body(addonService.updateAddon(addon));
    }

    @PutMapping("{id}")
    public ResponseEntity<Addon> updateAddonById(@PathVariable("id") Long id,
                                                 @Valid @RequestBody Addon addon) {
        addon.setId(id);
        return ResponseEntity.ok().body(addonService.updateAddon(addon));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAddon(@RequestBody Addon addon) {
        addonService.deleteAddon(addon);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAddonById(@PathVariable("id") Long id) {
        addonService.deleteAddonById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }

}
