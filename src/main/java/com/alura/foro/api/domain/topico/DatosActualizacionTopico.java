package com.alura.foro.api.domain.topico;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(


        @NotNull
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String curso,
        List<Long> idsRespuestas


) {

}