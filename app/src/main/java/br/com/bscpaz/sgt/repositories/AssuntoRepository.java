package br.com.bscpaz.sgt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bscpaz.sgt.entities.Assunto;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, String> {
	
}
