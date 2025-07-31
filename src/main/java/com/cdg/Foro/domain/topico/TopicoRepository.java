package com.cdg.Foro.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensage(@NotBlank(message = "El título no puede estar vacío") String titulo, @NotBlank(message = "El mensage no puede estar vacío") String mensage);
}
