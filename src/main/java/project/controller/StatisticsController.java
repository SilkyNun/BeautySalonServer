package project.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.model.MasterStatistics;
import project.model.SalonStatistics;
import project.service.StatisticsService;

import static project.service.StatisticsService.*;

@RestController
@CrossOrigin
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {
    private StatisticsService statisticsService;

    @GetMapping("{id}")
    public ResponseEntity<MasterStatistics> getMasterStatistics(@PathVariable("id") Long id) {
        return new ResponseEntity<>(statisticsService.getMasterStatistics(id), HttpStatus.OK);
    }

    @GetMapping("/day")
    public ResponseEntity<SalonStatistics> getSalonTodayStatistics() {
        return new ResponseEntity<>(statisticsService.getSalonStatistics(Period.TODAY), HttpStatus.OK);
    }

    @GetMapping("/month")
    public ResponseEntity<SalonStatistics> getSalonMonthStatistics() {
        return new ResponseEntity<>(statisticsService.getSalonStatistics(Period.MONTH), HttpStatus.OK);
    }
}
