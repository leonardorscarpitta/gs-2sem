package io.github.humaniza.vitaflow.repository;

import io.github.humaniza.vitaflow.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
