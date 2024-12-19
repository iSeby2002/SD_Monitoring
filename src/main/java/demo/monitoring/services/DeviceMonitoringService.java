package demo.monitoring.services;

import demo.monitoring.entities.DeviceMonitoring;
import demo.monitoring.repositories.DeviceMonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceMonitoringService {
    private final DeviceMonitoringRepository deviceMonitoringRepository;

    @Autowired
    public DeviceMonitoringService(DeviceMonitoringRepository deviceMonitoringRepository) {
        this.deviceMonitoringRepository = deviceMonitoringRepository;
    }

    public String addDeviceMonitoring(DeviceMonitoring deviceMonitoring) {
        deviceMonitoringRepository.save(deviceMonitoring);
        return "Device monitoring added successfully!";
    }

    public String updateDeviceMonitoring(DeviceMonitoring deviceMonitoring) {
        DeviceMonitoring existingDeviceMonitoring = deviceMonitoringRepository.findDeviceMonitoringByDeviceId(deviceMonitoring.getDeviceId());
        if(existingDeviceMonitoring != null){
            existingDeviceMonitoring.setDescription(deviceMonitoring.getDescription());
            existingDeviceMonitoring.setAddress(deviceMonitoring.getAddress());
            existingDeviceMonitoring.setConsumption(deviceMonitoring.getConsumption());
            deviceMonitoringRepository.save(existingDeviceMonitoring);
            return "Device monitoring updated successfully!";
        }else{
            return "Device monitoring not found!";
        }
    }

    public String deleteDeviceMonitoring(UUID deviceId) {
        DeviceMonitoring existingDeviceMonitoring = deviceMonitoringRepository.findDeviceMonitoringByDeviceId(deviceId);
        if(existingDeviceMonitoring != null) {
            deviceMonitoringRepository.delete(existingDeviceMonitoring);
            return "Device monitoring deleted successfully!";
        }else{
            return "Device monitoring not found!";
        }
    }

    public String deleteAllDeviceMonitorings(UUID personId) {
        List<DeviceMonitoring> existingDeviceMonitorings = deviceMonitoringRepository.findDeviceMonitoringsByPersonId(personId);
        if (existingDeviceMonitorings.isEmpty()) {
            return "No device monitorings found to delete!";
        }
        deviceMonitoringRepository.deleteAll(existingDeviceMonitorings);
        return "Device monitorings deleted successfully!";
    }

    public List<DeviceMonitoring> getDeviceMonitorings() {
        return deviceMonitoringRepository.findAll();
    }

    public List<DeviceMonitoring> getDeviceMonitoringsByPersonId(UUID personId) {
        return deviceMonitoringRepository.findDeviceMonitoringsByPersonId(personId);
    }

    public DeviceMonitoring getDeviceMonitoringByDeviceId(UUID deviceId) {
        return deviceMonitoringRepository.findDeviceMonitoringByDeviceId(deviceId);
    }
}
