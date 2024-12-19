package demo.monitoring.services;

import demo.monitoring.dtos.SimulatorMessageDTO;
import demo.monitoring.entities.DeviceMonitoring;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SimulatorMonitoringConsumer {

    private final DeviceMonitoringService deviceMonitoringService;
    private final SimulatorMonitoringService simulatorMonitoringService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SimulatorMonitoringConsumer(DeviceMonitoringService deviceMonitoringService, SimulatorMonitoringService simulatorMonitoringService, SimpMessagingTemplate messagingTemplate) {
        this.deviceMonitoringService = deviceMonitoringService;
        this.simulatorMonitoringService = simulatorMonitoringService;
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "simulator-monitoring-queue")
    public void consumeMessage(SimulatorMessageDTO simulatorMessageDTO) {
        System.out.println("\n" + simulatorMessageDTO + "\n");

        simulatorMonitoringService.addSimulatorMonitoring(simulatorMessageDTO);

        DeviceMonitoring deviceMonitoring = deviceMonitoringService.getDeviceMonitoringByDeviceId(simulatorMessageDTO.getIdDevice());
        if(simulatorMessageDTO.getValue() > deviceMonitoring.getConsumption()){
            Map<String, UUID> payload = new HashMap<>();
            payload.put("message", simulatorMessageDTO.getIdDevice());
            // Trimite mesajul prin WebSocket
            messagingTemplate.convertAndSend("/topic/alerts", payload);
        }
    }
}
