package project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import project.entity.Master;
import project.exceptions.NotFoundException;
import project.service.MasterService;

import javax.validation.Valid;

import static project.service.MasterService.*;

@RestController
@RequestMapping("/masters")
@CrossOrigin
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

//    return list of masters sorted by creation date in ascending or descending order
    @GetMapping("/sorted")
    public ResponseEntity<Iterable<Master>> getAllMastersSortedByDate(
            @RequestParam(value = "type", defaultValue = "percent") String type) {
        return new ResponseEntity<>(masterService.getAllMastersSorted(SortType.toSortType(type)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Master> addMaster(@Valid @RequestBody Master master) {
        return new ResponseEntity<>(masterService.addMaster(master), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Master> updateMaster(@Valid @RequestBody Master master) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }

}
