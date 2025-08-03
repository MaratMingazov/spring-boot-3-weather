package com.maratmingazov.spring_boot_3_weather

import maratmingazovr.spring_boot_3_starter_weather.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RestWeatherController(
    private val weatherService: WeatherService
) {

    @GetMapping("/weather")
    fun getWeather(@RequestParam(required = false) city: String?): String {
        return if (city != null) {
            weatherService.getTemperature(city)
        } else {
            weatherService.getTemperature() // default city
        }
    }
}