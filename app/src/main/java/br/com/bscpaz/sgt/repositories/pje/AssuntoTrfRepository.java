package br.com.bscpaz.sgt.repositories.pje;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bscpaz.sgt.entities.pje.AssuntoTrf;

@Repository
public interface AssuntoTrfRepository extends JpaRepository<AssuntoTrf, Integer> {
	
	public Optional<AssuntoTrf> findFirstByCodAssuntoTrf(String codAssuntoTrf);

	public Optional<AssuntoTrf> findFirstByCodAssuntoTrfSuperior(String codAssuntoTrfSuperior);

}
