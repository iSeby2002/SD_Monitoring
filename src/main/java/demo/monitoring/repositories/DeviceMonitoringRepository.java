package demo.monitoring.repositories;

import demo.monitoring.entities.DeviceMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceMonitoringRepository extends JpaRepository<DeviceMonitoring, UUID> {
    DeviceMonitoring findDeviceMonitoringById(UUID id);
    DeviceMonitoring findDeviceMonitoringByDeviceId(UUID deviceId);
    List<DeviceMonitoring> findDeviceMonitoringsByPersonId(UUID personId);
}
