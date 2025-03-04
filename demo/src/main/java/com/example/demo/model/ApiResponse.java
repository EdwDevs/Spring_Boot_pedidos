package com.example.demo.model;

import java.time.LocalDateTime;

// Esta clase estandariza todas nuestras respuestas
public class ApiResponse<T> {
    private final boolean success;    // Indica si la operación fue exitosa
    private final T data;             // Contiene los datos solicitados
    private final String message;     // Mensaje informativo
    private final LocalDateTime timestamp; // Fecha y hora de la respuesta

    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Para respuestas exitosas con datos
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }

    // Para respuestas exitosas sin datos
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, null, message);
    }

    // Para respuestas de error
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message);
    }

    // Getters para todas las propiedades
    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}