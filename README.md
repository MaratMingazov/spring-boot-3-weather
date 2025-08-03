# Spring Boot 3 Weather API

A modern Kotlin-based Spring Boot 3 application providing weather data via **REST**, **gRPC**, and **WebSocket** endpoints.

Powered by the custom starter [spring-boot-3-starter-weather](https://github.com/maratmingazov/spring-boot-3-starter-weather), and built on OpenWeatherMap.

---

## ✨ Features

- ✅ REST API: `/weather` via Spring `@RestController`
- ✅ gRPC API: `WeatherService` (proto-based)
- ✅ WebSocket API: `/weather-ws`
- ✅ Integrated Swagger UI (`http://localhost:8080/swagger-ui.html`)
- ✅ Configurable default city and API key via `application.yml`
- ✅ Based on OpenWeatherMap public API

---

## ⚙ Requirements

- JDK 17+
- Gradle 8+
- Internet connection (for OpenWeatherMap API)

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/YOUR_GITHUB/spring-boot-3-weather.git
cd spring-boot-3-weather
```

### 2. Set your OpenWeatherMap API key
Register at https://openweathermap.org/api and get your free API key.

Then update your `application.yml`:

```yaml
openweathermap-starter:
  sdkKey: your_api_key_here
  city: Amsterdam

grpc:
  server:
    port: 9090
```

### 3. Build and run the project
```bash
./gradlew clean build
./gradlew bootRun
```

The application will start at `http://localhost:8080` and gRPC will be available on port `9090`.

---

## 🌍 REST API

### Endpoint
```
GET http://localhost:8080/weather
```

### Query Parameters
| Name | Description         | Required |
|------|---------------------|----------|
| city | Name of the city    | No       |

### Examples

**1. Default city:**
```bash
curl http://localhost:8080/weather
```

**2. Specific city:**
```bash
curl "http://localhost:8080/weather?city=Paris"
```

### Postman:
- Create a new GET request
- URL: `http://localhost:8080/weather?city=Amsterdam`

---

## 📘 Swagger UI

Available at:
```
http://localhost:8080/swagger-ui.html
```
Use this to explore the REST API interactively.

---

## 📡 gRPC API

### Proto definition
- Located in: `src/main/proto/weather.proto`

### Regenerate gRPC classes
```bash
./gradlew generateProto
```

### Run gRPC server
Port: `9090` (configured in `application.yml`)

### Test with grpcurl
```bash
grpcurl -plaintext -d '{"city":"Paris"}' localhost:9090 weather.WeatherService/GetWeather
```

### Postman (Pro Version):
- Create new gRPC request
- Method: `weather.WeatherService/GetWeather`
- Server: `localhost:9090`
- Payload:
```json
{
  "city": "Berlin"
}
```

---

## 🔌 WebSocket API

### Endpoint
```
ws://localhost:8080/weather-ws
```

### How to test in Postman
1. Click `New` > `WebSocket Request`
2. URL: `ws://localhost:8080/weather-ws`
3. Click **Connect**
4. Send message: `Paris`

**Expected Response:** JSON with temperature data.

You can also use browser devtools:
```js
const socket = new WebSocket("ws://localhost:8080/weather-ws");
socket.onmessage = e => console.log(e.data);
socket.onopen = () => socket.send("Amsterdam");
```

---

## 🌐 OpenWeatherMap Integration

This app uses the library:
```kotlin
implementation("com.github.prominence:openweathermap-api:2.4.1")
```

You must register at [https://openweathermap.org](https://openweathermap.org) and get an API key to use this.

Set it in `application.yml` under:
```yaml
openweathermap-starter:
  sdkKey: your_api_key_here
```

---

## 🧪 Development & Debugging

### Useful Commands
```bash
./gradlew bootRun            # run application
./gradlew generateProto      # regenerate gRPC classes
./gradlew clean build        # build project
```

### Build Configuration
Key versions:
```kotlin
val grpcVersion = "1.63.0"
val protobufVersion = "3.25.3"
val kotlinVersion = "2.0.0"
val springBootVersion = "3.4.4"
```

---

## 🧱 Architecture Overview

- `RestWeatherController.kt` – REST endpoint using Spring Web
- `GrpcWeatherController.kt` – gRPC controller annotated with `@GrpcService`
- `WebSocketWeatherController.kt` – WebSocket handler using `TextWebSocketHandler`
- `application.yml` – configures gRPC port and weather SDK key

---

## 🎓 Contribution

Feel free to fork and contribute:
- Add new endpoints (e.g., forecasts)
- Improve WebSocket push
- Add caching layer or database storage

Pull requests are welcome.

---

## 🧾 License
MIT. Free to use and modify.

---

## ✉️ Contact
Developed by [Marat Mingazov](https://github.com/maratmingazov).

For questions or contributions, open an issue or reach out on GitHub.