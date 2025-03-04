package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class SaludoController {

    @Autowired
    private MessageSource messageSource;

    // Lista de idiomas soportados para validación
    private static final List<String> SUPPORTED_LANGUAGES = Arrays.asList("en", "es", "fr");

    @GetMapping("/saludo")
    public Mono<ApiResponse<String>> obtenerSaludo(
            @RequestParam(name = "lang", required = false) String language) {

        // Validación del idioma solicitado
        if (language != null && !SUPPORTED_LANGUAGES.contains(language)) {
            return Mono.just(ApiResponse.error(
                    "Idioma no soportado. Idiomas disponibles: " + SUPPORTED_LANGUAGES));
        }

        // Seleccionamos el idioma (por defecto inglés si no se especifica)
        Locale locale = (language == null) ? Locale.ENGLISH : new Locale(language);

        try {
            // Obtenemos el mensaje traducido
            String mensaje = messageSource.getMessage("saludo", null, locale);

            // Aseguramos que el idioma reportado coincida con el idioma real usado
            String idiomaNombre;
            switch(locale.getLanguage()) {
                case "es":
                    idiomaNombre = "español";
                    break;
                case "fr":
                    idiomaNombre = "francés";
                    break;
                default:
                    idiomaNombre = "inglés";
            }

            return Mono.just(ApiResponse.success(mensaje,
                    "Mensaje obtenido correctamente en " + idiomaNombre));
        } catch (NoSuchMessageException e) {
            // Si ocurre algún error, respondemos en inglés
            String defaultMessage = messageSource.getMessage("saludo", null, Locale.ENGLISH);
            return Mono.just(ApiResponse.error(
                    "Error al obtener traducción. Mensaje en inglés: " + defaultMessage));
        }
    }

    // Nuevo endpoint para obtener los idiomas disponibles
    @GetMapping("/idiomas")
    public Mono<ApiResponse<List<String>>> obtenerIdiomasDisponibles() {
        return Mono.just(ApiResponse.success(
                SUPPORTED_LANGUAGES,
                "Idiomas soportados por la aplicación"));
    }
}