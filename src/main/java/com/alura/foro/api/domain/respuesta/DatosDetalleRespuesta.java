package com.alura.foro.api.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(Long id, String mensaje, Long idTopico, LocalDateTime fechaCreacion,
                                    String autor, Boolean solucion) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getId(), respuesta.getFechaCreacion(),
                respuesta.getAutor().getLogin(), respuesta.getSolucion());
    }

}