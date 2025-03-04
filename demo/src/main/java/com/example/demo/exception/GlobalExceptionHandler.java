package com.example.demo.exception;

import com.example.demo.model.ApiResponse;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

// Esta clase captura todas las excepciones que ocurran en nuestra aplicación
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja errores de mensajes no encontrados
    @ExceptionHandler(NoSuchMessageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ApiResponse<Void>> handleNoSuchMessageException(NoSuchMessageException ex) {
        return Mono.just(ApiResponse.error("Traducción no encontrada: " + ex.getMessage()));
    }

    // Maneja cualquier otro error inesperado
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ApiResponse<Void>> handleGenericException(Exception ex) {
        return Mono.just(ApiResponse.error("Error interno del servidor: " + ex.getMessage()));
    }
}