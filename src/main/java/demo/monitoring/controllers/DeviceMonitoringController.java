package demo.monitoring.controllers;

import demo.monitoring.entities.DeviceMonitoring;
import demo.monitoring.services.DeviceMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        "http://localhost:8083"})
@RequestMapping("/deviceMonitoring")
public class DeviceMonitoringController {
    private final DeviceMonitoringService deviceMonitoringService;

    @Autowired
    public DeviceMonitoringController(DeviceMonitoringService deviceMonitoringService) {
        this.deviceMonitoringService = deviceMonitoringService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDeviceMonitoring(@RequestBody DeviceMonitoring deviceMonitoring) {
        String msg = deviceMonitoringService.addDeviceMonitoring(deviceMonitoring);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDeviceMonitoring(@RequestBody DeviceMonitoring deviceMonitoring) {
        String msg = deviceMonitoringService.updateDeviceMonitoring(deviceMonitoring);
        if(msg.equals("Device monitoring updated successfully!")){
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<String> deleteDeviceMonitoring(@PathVariable UUID deviceId) {
        String msg = deviceMonitoringService.deleteDeviceMonitoring(deviceId);
        if(msg.equals("Device monitoring deleted successfully!")){
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAllDeviceMonitorings/{personId}")
    public ResponseEntity<String> deleteAllDeviceMonitorings(@PathVariable UUID personId) {
        String msg = deviceMonitoringService.deleteAllDeviceMonitorings(personId);
        if(msg.equals("Device monitorings deleted successfully!")){
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getDeviceMonitorings")
    public ResponseEntity<List<DeviceMonitoring>> getDeviceMonitorings() {
        List<DeviceMonitoring> deviceMonitorings = deviceMonitoringService.getDeviceMonitorings();
        return new ResponseEntity<>(deviceMonitorings, HttpStatus.OK);
    }

    @GetMapping("/getDeviceMonitorings/{personId}")
    public ResponseEntity<List<DeviceMonitoring>> getDeviceMonitoringsByPersonId(@PathVariable UUID personId) {
        List<DeviceMonitoring> deviceMonitorings = deviceMonitoringService.getDeviceMonitoringsByPersonId(personId);
        return new ResponseEntity<>(deviceMonitorings, HttpStatus.OK);
    }
}
