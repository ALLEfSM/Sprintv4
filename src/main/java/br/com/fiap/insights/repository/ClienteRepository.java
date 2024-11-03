package br.com.fiap.insights.repository;


import br.com.fiap.insights.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {



    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome% ")
    public List<Cliente> search( String nome);

    public List<Cliente> findByNome(@Param("nome") String nome);


}
