package demo.monitoring.controllers;

import demo.monitoring.dtos.DeviceDateDTO;
import demo.monitoring.entities.SimulatorMonitoring;
import demo.monitoring.services.SimulatorMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://frontend.localhost",
        "http://user.localhost",
        "http://device.localhost",
        "http://monitoring.localhost",
        "http://chat.localhost",
        "http://localhost",
        "http://localhost:3000",
        "http://localhost:8080",
        "http://localhost:8081",
        "http://localhost:8082",
        "http://localhost:8083",
        "https://heroic-boba-6ce237.netlify.app"})
@RequestMapping("/simulatorMonitoring")
public class SimulatorMonitoringController {
    private final SimulatorMonitoringService simulatorMonitoringService;

    @Autowired
    public SimulatorMonitoringController(SimulatorMonitoringService simulatorMonitoringService) {
        this.simulatorMonitoringService = simulatorMonitoringService;
    }

    @PostMapping("/findSimulatorMonitoringsByDeviceAndDate")
    public ResponseEntity<List<SimulatorMonitoring>> findSimulatorMonitoringsByDeviceAndDate(@RequestBody DeviceDateDTO deviceDateDTO) {
        List<SimulatorMonitoring> simulatorMonitorings = simulatorMonitoringService.findSimulatorMonitoringsByDeviceAndDate(deviceDateDTO);
        return new ResponseEntity<>(simulatorMonitorings, HttpStatus.OK);
    }
}
