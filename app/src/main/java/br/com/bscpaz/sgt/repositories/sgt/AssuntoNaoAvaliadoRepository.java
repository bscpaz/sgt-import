package br.com.bscpaz.sgt.repositories.sgt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bscpaz.sgt.entities.sgt.AssuntoNaoAvaliado;

@Repository
public interface AssuntoNaoAvaliadoRepository extends JpaRepository<AssuntoNaoAvaliado, String> {
	
}
