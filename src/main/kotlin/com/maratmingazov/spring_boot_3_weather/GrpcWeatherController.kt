package com.maratmingazov.spring_boot_3_weather

import io.grpc.stub.StreamObserver
import maratmingazovr.spring_boot_3_starter_weather.WeatherService
import maratmingazovr.spring_boot_3_weather.weather.grpc.WeatherRequest
import maratmingazovr.spring_boot_3_weather.weather.grpc.WeatherResponse
import maratmingazovr.spring_boot_3_weather.weather.grpc.WeatherServiceGrpc
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class GrpcWeatherController (
    private val weatherService: WeatherService
) : WeatherServiceGrpc.WeatherServiceImplBase() {

    override fun getWeather(
        request: WeatherRequest,
        responseObserver: StreamObserver<WeatherResponse>
    ) {

        val city = request.city.takeIf { request.hasCity() } ?: "DefaultCity"
        val json = if (request.hasCity()) {
            weatherService.getTemperature(request.city)
        } else {
            weatherService.getTemperature()
        }

        val response = WeatherResponse.newBuilder()
            .setJson(json)
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}