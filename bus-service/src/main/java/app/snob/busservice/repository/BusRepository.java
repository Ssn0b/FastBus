package app.snob.busservice.repository;

import app.snob.busservice.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface  BusRepository extends JpaRepository<Bus, UUID> {
}
