package br.com.bscpaz.sgt.repositories.sgt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bscpaz.sgt.entities.sgt.AssuntoAvaliado;

@Repository
public interface AssuntoAvaliadoRepository extends JpaRepository<AssuntoAvaliado, String> {

	public Optional<AssuntoAvaliado> findFirstByCodigo(String codigo);
	
}
