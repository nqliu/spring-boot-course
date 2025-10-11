package top.nql.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.nql.websocket.handler.SimpleTimeWebSocketHandler;
import top.nql.websocket.handler.TestWebSocketHandler;

/**
 * @author alani
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 测试的 WebSocket 端点
        registry.addHandler(new TestWebSocketHandler(), "/ws/test")
                .setAllowedOrigins("*");

        registry.addHandler(new SimpleTimeWebSocketHandler(), "/ws/time").setAllowedOrigins("*");
    }
}