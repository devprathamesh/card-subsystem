package dev.learnx.cardsubsystem.repository;

import dev.learnx.cardsubsystem.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    Card findByUpc(String upc);
}
