package com.maratmingazov.spring_boot_3_weather

import maratmingazovr.spring_boot_3_starter_weather.WeatherService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
class Configuration {
    @Bean
    fun webSocketWeatherController(weatherService: WeatherService): WebSocketWeatherController {
        return WebSocketWeatherController(weatherService)
    }
}

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val webSocketWeatherController: WebSocketWeatherController
) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketWeatherController, "/weather-ws").setAllowedOrigins("*")
    }
}