package com.alura.foro.api.domain.respuesta;

import java.time.LocalDateTime;

import com.alura.foro.api.domain.topico.Topico;
import com.alura.foro.api.domain.topico.TopicoRepository;
import com.alura.foro.api.domain.topico.TopicoService;
import com.alura.foro.api.domain.usuarios.Usuario;
import com.alura.foro.api.domain.usuarios.UsuarioService;
import com.alura.foro.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    private Boolean solucion = false;

    @Autowired
    TopicoService topicoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TopicoRepository topicoRepository;

    public Respuesta(DatosAgendarRespuesta datos, Long idTopico) {
        this.mensaje = datos.mensaje();
        var topico = (Topico) topicoRepository.getReferenceById(idTopico);
        this.topico = topico;
        var autor = usuarioService.getUsuarioAutenticado();
        this.autor = autor;

    }

    public void actualizarInformaciones(@Valid DatosActualizacionRespuesta datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

        if (datos.topico() != null) {
            try {
                Long Idtopico = datos.topico();
                Topico topico = topicoRepository.findById(Idtopico).orElse(null);

                if (topico != null) {
                    this.topico = topico;
                } else {
                    throw new ValidacionDeIntegridad("El id del topico no fue encontrado");
                }
            } catch (NumberFormatException e) {
                // Maneja el caso en el que `datos.curso()` no sea un número válido.
            }
        }

        if (datos.fechaCreacion() != null) {
            this.fechaCreacion = datos.fechaCreacion();
        }

        if (datos.solucion() != null) {
            this.solucion = datos.solucion();
        }

    }


}