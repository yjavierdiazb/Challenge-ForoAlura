package com.alura.foro.api.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListaRespuesta(Long id, String mensaje, LocalDateTime fechaCreacion,
                                  String autor, Boolean solucion) {
    public DatosListaRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(),
                respuesta.getAutor().getLogin(), respuesta.getSolucion());
    }

}