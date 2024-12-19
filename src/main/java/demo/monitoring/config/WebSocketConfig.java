package demo.monitoring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@CrossOrigin(origins = {
        "http://frontend.localhost",
        "http://user.localhost",
        "http://device.localhost",
        "http://monitoring.localhost",
        "http://localhost:3000",
        "http://localhost:8080",
        "http://localhost:8081",
        "http://localhost:8082",
        "https://heroic-boba-6ce237.netlify.app"})
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Destinațiile pentru mesajele trimise clientului
        config.setApplicationDestinationPrefixes("/app"); // Prefix pentru mesajele primite de server
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Endpoint-ul WebSocket
                .setAllowedOriginPatterns("*") // Permite accesul din orice origine
                .withSockJS(); // Fallback pentru clienți fără suport WebSocket
    }
}
