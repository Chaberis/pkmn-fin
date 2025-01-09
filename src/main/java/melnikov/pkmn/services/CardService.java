package melnikov.pkmn.services;
import melnikov.pkmn.entities.CardEntity;
import java.util.*;
import java.util.List;
import java.util.UUID;

public interface CardService {
    List<CardEntity> getAllCards();
    CardEntity getCardById(UUID id);
    CardEntity saveCard(CardEntity card);
    CardEntity updateCard(UUID id, CardEntity card);
    void deleteCard(UUID id);
    List<CardEntity> getCardsByOwner(String firstName, String surName, String familyName);
    Optional<CardEntity> getCardsByName(String name);
    String getCardImageByName(String cardName); // Новый метод
}