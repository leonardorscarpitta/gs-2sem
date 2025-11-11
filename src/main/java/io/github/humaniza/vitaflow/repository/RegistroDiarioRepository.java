package io.github.humaniza.vitaflow.repository;

import io.github.humaniza.vitaflow.model.RegistroDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Integer> {
}
