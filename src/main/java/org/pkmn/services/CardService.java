package org.pkmn.services;
import org.pkmn.models.Card;
import org.pkmn.models.Student;
import java.util.List;
import java.util.UUID;


public interface CardService {
    List<Card> getAllCards();
    Card getCardByName(String name);
    Card getCardByPokemonOwner(Student student);
    Card getCardById(UUID id);
    Card saveCard(Card card);
    String getPokemonImage(String name, int number);
}