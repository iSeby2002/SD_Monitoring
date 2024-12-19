package demo.monitoring.services;

import demo.monitoring.dtos.DeviceDateDTO;
import demo.monitoring.dtos.SimulatorMessageDTO;
import demo.monitoring.entities.SimulatorMonitoring;
import demo.monitoring.repositories.SimulatorMonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;

@Service
public class SimulatorMonitoringService {
    private final SimulatorMonitoringRepository simulatorMonitoringRepository;

    @Autowired
    public SimulatorMonitoringService(SimulatorMonitoringRepository simulatorMonitoringRepository) {
        this.simulatorMonitoringRepository = simulatorMonitoringRepository;
    }

    public void addSimulatorMonitoring(SimulatorMessageDTO simulatorMessageDTO) {
        SimulatorMonitoring existingSimulatorMonitoring = simulatorMonitoringRepository.findSimulatorMonitoringByTimestampAndIdDevice(simulatorMessageDTO.getTimestamp(), simulatorMessageDTO.getIdDevice());
        if(existingSimulatorMonitoring == null) {
            SimulatorMonitoring simulatorMonitoring = new SimulatorMonitoring();
            simulatorMonitoring.setTimestamp(simulatorMessageDTO.getTimestamp());
            simulatorMonitoring.setIdDevice(simulatorMessageDTO.getIdDevice());
            simulatorMonitoring.setValue(simulatorMessageDTO.getValue());
            simulatorMonitoringRepository.save(simulatorMonitoring);
        }else{
            existingSimulatorMonitoring.setTimestamp(simulatorMessageDTO.getTimestamp());
            existingSimulatorMonitoring.setIdDevice(simulatorMessageDTO.getIdDevice());
            existingSimulatorMonitoring.setValue(simulatorMessageDTO.getValue());
            simulatorMonitoringRepository.save(existingSimulatorMonitoring);
        }
    }

    public List<SimulatorMonitoring> findSimulatorMonitoringsByDeviceAndDate(DeviceDateDTO deviceDateDTO) {
        List<SimulatorMonitoring> simulatorMonitorings = simulatorMonitoringRepository.findSimulatorMonitoringsByIdDevice(deviceDateDTO.getDeviceId());

        return simulatorMonitorings.stream()
                .filter(monitoring -> isSameDate(monitoring.getTimestamp(), deviceDateDTO.getDate()))
                .collect(Collectors.toList());
    }

    private boolean isSameDate(Timestamp timestamp, LocalDate date) {
        LocalDate monitoringDate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return monitoringDate.equals(date);
    }
}
