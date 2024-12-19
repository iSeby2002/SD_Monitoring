package demo.monitoring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimulatorMessageDTO {
    private Timestamp timestamp;
    private UUID idDevice;
    private Double value;

    @Override
    public String toString() {
        return "SimulatorMessageDTO{" +
                "timestamp=" + timestamp +
                ", idDevice=" + idDevice +
                ", value=" + value +
                '}';
    }
}
