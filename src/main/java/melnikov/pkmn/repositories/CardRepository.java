package melnikov.pkmn.repositories;
import org.springframework.stereotype.Repository;
import melnikov.pkmn.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    List<CardEntity> findAll();
    Optional<CardEntity> findByName(String name);
    Optional<CardEntity> findByPokemonOwnerId(UUID id);
    Optional<CardEntity> findById(UUID id);
    List<CardEntity> findByPokemonOwner(String firstName, String surName, String familyName);
}