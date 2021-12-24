package project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Master;
import project.exceptions.NotFoundException;
import project.service.MasterService;

@RestController
@RequestMapping("/masters")
@AllArgsConstructor
@Slf4j
public class MasterController {
    private MasterService masterService;

    @GetMapping
    public ResponseEntity<Iterable<Master>> getAllMasters() {
        return new ResponseEntity<>(masterService.getAllMasters(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMasterById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(masterService.getMasterById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Master> addMaster(@RequestBody Master master) {
        return new ResponseEntity<>(masterService.addMaster(master), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Master> updateMaster(@RequestBody Master master) {
        return new ResponseEntity<>(masterService.updateMaster(master), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMaster(@RequestBody Master master) {
        masterService.deleteMaster(master);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

}