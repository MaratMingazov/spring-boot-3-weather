package com.maratmingazov.spring_boot_3_weather

import maratmingazovr.spring_boot_3_starter_weather.WeatherService
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class WebSocketWeatherController(
    private val weatherService: WeatherService
) : TextWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val city = message.payload.takeIf { it.isNotBlank() } ?: "DefaultCity"
        val json = weatherService.getTemperature(city)
        session.sendMessage(TextMessage(json))
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("WebSocket connected: ${session.id}")
    }
}