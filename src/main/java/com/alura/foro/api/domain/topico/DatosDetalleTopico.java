package com.alura.foro.api.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, StatusTopico status, String autor, String curso, String idRespuestas) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(), topico.getAutor().getLogin(), topico.getCurso().getNombre(), topico.idsRespuestasComoStr());
    }

}