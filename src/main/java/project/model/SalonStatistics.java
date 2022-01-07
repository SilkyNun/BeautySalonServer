package project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalonStatistics {
    private Double income;
    private List<MasterStatistics> masters = new ArrayList<>();
}
