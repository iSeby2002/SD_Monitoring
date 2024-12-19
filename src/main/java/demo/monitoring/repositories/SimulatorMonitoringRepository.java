package demo.monitoring.repositories;

import demo.monitoring.entities.SimulatorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface SimulatorMonitoringRepository extends JpaRepository<SimulatorMonitoring, UUID> {
    SimulatorMonitoring findSimulatorMonitoringById(UUID id);
    SimulatorMonitoring findSimulatorMonitoringByTimestamp(Timestamp timestamp);
    List<SimulatorMonitoring> findSimulatorMonitoringsByIdDevice(UUID idDevice);
    SimulatorMonitoring findSimulatorMonitoringByTimestampAndIdDevice(Timestamp timestamp, UUID idDevice);
}
