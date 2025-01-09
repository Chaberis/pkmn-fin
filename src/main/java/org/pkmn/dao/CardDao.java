package org.pkmn.dao;
import org.pkmn.entities.CardEntity;
import org.pkmn.entities.StudentEntity;
import org.pkmn.models.Card;
import org.pkmn.models.Student;
import org.pkmn.repositories.CardRepository;
import org.pkmn.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class CardDao {
    private final CardRepository cardentityrepository;
    private final StudentRepository studentEntityRepository;

    @SneakyThrows
    public List<CardEntity> getAll() {
        return cardentityrepository.findAll();
    }

    @SneakyThrows
    public CardEntity getByName(String name) {
        return cardentityrepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @SneakyThrows
    public CardEntity getByPokemonOwnerId(Student student) {
        StudentEntity studentEntity = studentEntityRepository.findBySurNameAndFirstNameAndFamilyNameAndGroup(
                student.getSurName(), student.getFirstName(), student.getFamilyName(), student.getGroup()).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));

        UUID id = studentEntity.getId();
        return cardentityrepository.findByPokemonOwnerId(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @SneakyThrows
    public CardEntity getById(UUID id) {
        return cardentityrepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @SneakyThrows
    public CardEntity saveCard(CardEntity cardEntity) {
        return cardentityrepository.save(cardEntity);
    }

    public boolean cardExists(Card card) {
        return cardentityrepository.findByName(card.getName()).isPresent();
    }
}