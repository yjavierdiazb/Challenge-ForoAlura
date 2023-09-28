package com.alura.foro.api.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

//	Topico topico = null;
//	public Topico getTopicoByTitulo (String titulo) {
//		var totalDeTopicos = topicoRepository.findAll();
//
//		for (Topico topico: totalDeTopicos) {
//			if (topico.getTitulo().equals(titulo)==true) {
//				return topico;
//			}
//		}
//		if (topico==null) {
//			throw new ValidacionDeIntegridad("No se encontró ningún tópico con ese título");
//		}
//		return null;
//	}

}
