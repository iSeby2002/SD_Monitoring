package demo.monitoring.services;

import demo.monitoring.dtos.DeviceMessageDTO;
import demo.monitoring.entities.DeviceMonitoring;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceMonitoringConsumer {
    private final DeviceMonitoringService deviceMonitoringService;

    @Autowired
    public DeviceMonitoringConsumer(DeviceMonitoringService deviceMonitoringService) {
        this.deviceMonitoringService = deviceMonitoringService;
    }

    @RabbitListener(queues = "device-monitoring-queue")
    public void consumeMessage(DeviceMessageDTO deviceMessageDTO) {
        switch (deviceMessageDTO.getMessage()) {
            case "add":
                System.out.println("\n" + "DEVICE MONITORING - ADD:" + deviceMessageDTO + "\n");
                DeviceMonitoring deviceMonitoring = new DeviceMonitoring();
                deviceMonitoring.setDescription(deviceMessageDTO.getDescription());
                deviceMonitoring.setAddress(deviceMessageDTO.getAddress());
                deviceMonitoring.setConsumption(deviceMessageDTO.getConsumption());
                deviceMonitoring.setPersonId(deviceMessageDTO.getPersonId());
                deviceMonitoring.setDeviceId(deviceMessageDTO.getDeviceId());
                deviceMonitoringService.addDeviceMonitoring(deviceMonitoring);
                break;
            case "update":
                System.out.println("\n" + "DEVICE MONITORING - UPDATE:" + deviceMessageDTO + "\n");
                DeviceMonitoring updateDeviceMonitoring = new DeviceMonitoring();
                updateDeviceMonitoring.setDescription(deviceMessageDTO.getDescription());
                updateDeviceMonitoring.setAddress(deviceMessageDTO.getAddress());
                updateDeviceMonitoring.setConsumption(deviceMessageDTO.getConsumption());
                updateDeviceMonitoring.setPersonId(deviceMessageDTO.getPersonId());
                updateDeviceMonitoring.setDeviceId(deviceMessageDTO.getDeviceId());
                deviceMonitoringService.updateDeviceMonitoring(updateDeviceMonitoring);
                break;
            case "delete":
                System.out.println("\n" + "DEVICE MONITORING - DELETE:" + deviceMessageDTO + "\n");
                deviceMonitoringService.deleteDeviceMonitoring(deviceMessageDTO.getDeviceId());
                break;
            case "deleteAll":
                System.out.println("\n" + "DEVICE MONITORING - DELETE ALL:" + deviceMessageDTO + "\n");
                deviceMonitoringService.deleteAllDeviceMonitorings(deviceMessageDTO.getPersonId());
                break;
            default:
                System.out.println("\n" + "DEVICE MONITORING - UNKNOWN:" + deviceMessageDTO + "\n");
        }
    }
}
