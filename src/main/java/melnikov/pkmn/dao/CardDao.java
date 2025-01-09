package melnikov.pkmn.dao;
import melnikov.pkmn.entities.CardEntity;
import melnikov.pkmn.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CardDao {
    @Autowired
    private final CardRepository cardRepository;

    public List<CardEntity> findAll() {
        return cardRepository.findAll();
    }

    public CardEntity save(CardEntity card) {
        return cardRepository.save(card);
    }

    public void deleteById(UUID id) {
        cardRepository.deleteById(id);
    }

    public List<CardEntity> findCardsByOwner(String firstName, String surName, String familyName) {
        return cardRepository.findByPokemonOwner(firstName, surName, familyName);
    }

    public Optional<CardEntity> findCardsByName(String name) {
        return cardRepository.findByName(name);
    }

    public Optional<CardEntity> findById(UUID id) {
        return cardRepository.findById(id);
    }
}