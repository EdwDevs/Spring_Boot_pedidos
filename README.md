# API RESTful con Internacionalización y Programación Reactiva en Spring Boot

Este proyecto implementa una API RESTful utilizando Spring Boot 3+ con soporte para internacionalización (i18n) y programación reactiva mediante Spring WebFlux. La API permite responder en diferentes idiomas (español, inglés, francés) y maneja flujos de datos reactivos.

## 🎯 Objetivos del Proyecto

- ✅ Crear una API RESTful con Spring Boot 3+
- ✅ Implementar internacionalización (i18n) en las respuestas
- ✅ Utilizar Spring WebFlux para manejar respuestas de manera reactiva
- ✅ Realizar pruebas con Postman para verificar la funcionalidad

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3+**
- **Spring WebFlux** - Para programación reactiva
- **Spring Boot DevTools** - Para desarrollo
- **Spring Boot Validation** - Para validaciones
- **Spring Boot Security** - Para autenticación
- **Project Reactor** - Para programación reactiva (Mono, Flux)

## 📁 Estructura del Proyecto
src/main/java/com/example/demo/
├── config/
│ └── LocaleConfig.java # Configuración de internacionalización
├── controller/
│ ├── SaludoController.java # Controlador de saludos
│ └── ProductoController.java # Controlador de productos
├── model/
│ └── Producto.java # Modelo de datos de producto
├── service/
│ └── ProductoService.java # Servicio para productos
└── DemoApplication.java # Clase principal

src/main/resources/
├── messages_es.properties # Mensajes en español
├── messages_en.properties # Mensajes en inglés
├── messages_fr.properties # Mensajes en francés
└── application.properties # Configuración de la aplicación


## 🚀 Instalación y Ejecución

### Prerrequisitos
- JDK 17 o superior
- Maven 3.6 o superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos para ejecutar
1. Clona el repositorio:
   ```bash
   git clone https://github.com/EdwDevs/Spring_Boot_pedidos.git

- Navega al directorio del proyecto:
    ```bash
    cd Spring_Boot_pedidos
- Compila y ejecuta el proyecto:
    ```bash
    mvn spring-boot:run
- La aplicación estará disponible en: http://localhost:8080

📝 Funcionalidades Implementadas
1. Configuración de Internacionalización
   La aplicación permite responder en diferentes idiomas utilizando archivos de propiedades:

- messages_es.properties (Español)
    ```bash
  saludo=Hola, bienvenido a nuestra API Reactiva!
- messages_en.properties (Ingles)
    ```bash
  saludo=Hello, welcome to our Reactive API!
- messages_fr.properties (Frances)
    ```bash
  saludo=Bonjour, bienvenue sur notre API réactive!

- La configuración en application.properties:
    ```bash
  spring.messages.basename=messages
    spring.messages.encoding=UTF-8

- API RESTful con Spring WebFlux:

El controlador implementa endpoints reactivos utilizando Mono y Flux para manejar flujos de datos:

@RestController
@RequestMapping("/api")
public class SaludoController {
@Autowired
private MessageSource messageSource;

    @GetMapping("/saludo")
    public Mono<String> obtenerSaludo(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return Mono.just(messageSource.getMessage("saludo", null, locale));
    }
}

- Servicio de Productos:

  Se implementó un servicio de productos con Spring WebFlux que maneja una lista de productos de manera reactiva:
  @Service
  public class ProductoService {
  private List<Producto> productos = List.of(
  new Producto("1", "Laptop", 1200.0),
  new Producto("2", "Mouse", 25.0),
  new Producto("3", "Teclado", 45.0)
  );

  public Flux<Producto> obtenerProductos() {
  return Flux.fromIterable(productos);
  }
  }

📋 Ejemplos de Uso:

Obtener un saludo en diferentes idiomas
- Español:
GET http://localhost:8080/api/saludo?lang=es
Respuesta: "Hola, bienvenido a nuestra API Reactiva!"

- Inglés:
GET http://localhost:8080/api/saludo?lang=en
Respuesta: "Hello, welcome to our Reactive API!"

- Francés:
GET http://localhost:8080/api/saludo?lang=fr
Respuesta: "Bonjour, bienvenue sur notre API réactive!"

- Obtener la lista de productos
- Endpoint:
GET http://localhost:8080/api/productos

- Respuesta JSON:
    ```bash
  [
  {"id": "1", "nombre": "Laptop", "precio": 1200.0},
  {"id": "2", "nombre": "Mouse", "precio": 25.0},
  {"id": "3", "nombre": "Teclado", "precio": 45.0}
  ]

👨‍💻 Autor
EdwDevs

📄 Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para más detalles.