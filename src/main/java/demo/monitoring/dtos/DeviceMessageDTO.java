package demo.monitoring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceMessageDTO {
    private String message;
    private UUID deviceId;
    private String description;
    private String address;
    private Long consumption;
    private UUID personId;

    @Override
    public String toString() {
        return "DeviceMessageDTO{" +
                "message='" + message + '\'' +
                ", deviceId=" + deviceId +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", consumption=" + consumption +
                ", personId=" + personId +
                '}';
    }
}
