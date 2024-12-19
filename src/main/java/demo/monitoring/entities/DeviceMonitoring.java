package demo.monitoring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "device_monitoring")
public class DeviceMonitoring {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column
    private String description;

    @Column
    private String address;

    @Column
    private Long consumption;

    @Column
    private UUID personId;

    @Column
    private UUID deviceId;

    public DeviceMonitoring(String description, String address, Long consumption, UUID personId, UUID deviceId) {
        this.description = description;
        this.address = address;
        this.consumption = consumption;
        this.personId = personId;
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "DeviceMonitoring{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", consumption=" + consumption +
                ", personId=" + personId +
                ", deviceId=" + deviceId +
                '}';
    }
}
