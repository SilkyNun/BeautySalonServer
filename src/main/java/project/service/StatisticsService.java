package project.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.Master;
import project.entity.Order;
import project.model.MasterStatistics;
import project.model.SalonStatistics;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsService {
    private MasterService masterService;

    public enum Period {
        TODAY,
        MONTH
    }

    public MasterStatistics getMasterStatistics(Long masterId) {
        Master master = masterService.getMasterById(masterId);
        MasterStatistics masterStatistics = MasterStatistics.toMasterStatistics(master);

        Double todayIncome = todayIncome(master);
        Double monthIncome = monthIncome(master);

        masterStatistics.setTodayNetIncome(todayIncome * master.getPercent());
        masterStatistics.setMonthNetIncome(monthIncome * master.getPercent());

        return masterStatistics;
    }

    public SalonStatistics getSalonStatistics(Period period) {
        SalonStatistics salonStatistics = new SalonStatistics();

        List<Master> allMasters = (List<Master>) masterService.getAllMasters();

        List<MasterStatistics> masterStatistics = allMasters.stream()
                .map(master -> getMasterStatistics(master.getId()))
                .sorted(sortMasters(period))
                .toList();

        Double todayIncome = allMasters.stream()
                .map(master -> countIncome(master, period))
                .reduce(0d, Double::sum);

        salonStatistics.setIncome(todayIncome);
        salonStatistics.setMasters(masterStatistics);

        return salonStatistics;
    }

    private Comparator<MasterStatistics> sortMasters(Period period) {
        return switch (period) {
            case TODAY -> (ms1, ms2) -> ms2.getTodayNetIncome().compareTo(ms1.getTodayNetIncome());
            case MONTH -> (ms1, ms2) -> ms2.getMonthNetIncome().compareTo(ms1.getMonthNetIncome());
        };
    }

    private Double countIncome(Master master, Period period) {
        return switch (period) {
            case TODAY -> todayIncome(master);
            case MONTH -> monthIncome(master);
        };
    }

    private Double todayIncome(Master master) {
        return master.getOrders().stream()
                .filter(order -> order.getFinish().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .map(Order::getPrice)
                .reduce(0d, Double::sum);
    }

    private Double monthIncome(Master master) {
        return master.getOrders().stream()
                .filter(order -> order.getFinish().getMonthValue() == LocalDateTime.now().getMonthValue())
                .map(Order::getPrice)
                .reduce(0d, Double::sum);
    }

}
