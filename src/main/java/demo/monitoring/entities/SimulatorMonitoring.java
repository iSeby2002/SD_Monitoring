package demo.monitoring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "simulator_monitoring")
public class SimulatorMonitoring {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column
    private Timestamp timestamp;

    @Column
    private UUID idDevice;

    @Column
    private Double value;

    public SimulatorMonitoring(Timestamp timestamp, UUID idDevice, Double value) {
        this.timestamp = timestamp;
        this.idDevice = idDevice;
        this.value = value;
    }

    @Override
    public String toString() {
        return "SimulatorMonitoring{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", idDevice=" + idDevice +
                ", value=" + value +
                '}';
    }
}
