package project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.entity.Master;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MasterStatistics {
    private Long id;
    private String name;
    private String tel;
    private Double percent;
    private Double todayNetIncome;
    private Double monthNetIncome;

    public static MasterStatistics toMasterStatistics(Master master) {
        MasterStatistics masterStatistics = new MasterStatistics();
        masterStatistics.setId(master.getId());
        masterStatistics.setName(master.getName());
        masterStatistics.setPercent(master.getPercent());
        masterStatistics.setTel(master.getTel());

        return masterStatistics;
    }
}
