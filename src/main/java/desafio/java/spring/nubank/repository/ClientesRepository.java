package desafio.java.spring.nubank.repository;

import desafio.java.spring.nubank.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Contato, Long> {

}
