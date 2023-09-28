package com.alura.foro.api.domain.respuesta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionRespuesta(

        @NotNull
        Long id,
        String mensaje,
        Long topico,
        LocalDateTime fechaCreacion,
        Boolean solucion

) {

}