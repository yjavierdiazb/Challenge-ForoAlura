package com.alura.foro.api.controller;

import com.alura.foro.api.domain.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.api.domain.topico.DatosActualizacionTopico;
import com.alura.foro.api.domain.topico.DatosAgendarTopico;
import com.alura.foro.api.domain.topico.DatosDetalleTopico;
import com.alura.foro.api.domain.topico.DatosListaTopico;
import com.alura.foro.api.domain.topico.Topico;
import com.alura.foro.api.domain.topico.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosAgendarTopico datos, UriComponentsBuilder uriBuilder) {
        var topico = new Topico(datos);
        var usuario = usuarioService.getUsuarioAutenticado();
        topico.setAutor(usuario);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Query("""
    		select * from Topico
    		where id=:id
    		""")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = repository.findById(id).get();
        repository.delete(topico);
        System.out.println("Se eliminó 1 topico");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        var page = repository.findAll(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionTopico datos) {
        var topico = repository.getReferenceById(datos.id());
        topico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

}