package br.com.fiap.insights.repository;


import br.com.fiap.insights.model.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {

    List<Opiniao> findByComentarioContaining(String comentario);
    
}
