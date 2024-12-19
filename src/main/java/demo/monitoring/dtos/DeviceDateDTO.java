package demo.monitoring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDateDTO {
    private LocalDate date;
    private UUID deviceId;

    @Override
    public String toString() {
        return "DeviceDateDTO{" +
                "date=" + date +
                ", deviceId=" + deviceId +
                '}';
    }
}
