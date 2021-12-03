package br.com.bscpaz.sgt.repositories.sgt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bscpaz.sgt.entities.sgt.Assunto;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, String> {
	
	public Optional<Assunto> findFirstByCodigo(String codigo);
	
}
