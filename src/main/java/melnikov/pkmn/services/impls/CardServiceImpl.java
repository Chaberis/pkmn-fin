package melnikov.pkmn.services.impls;
import melnikov.pkmn.dao.CardDao;
import melnikov.pkmn.entities.CardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import melnikov.pkmn.services.CardService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import melnikov.pkmn.clients.PokemonCardResponse;
import melnikov.pkmn.clients.RestClient;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardDao cardDao;
    @Autowired
    private RestClient restClient;

    @Override
    public List<CardEntity> getAllCards() {
        return cardDao.findAll();
    }

    @Override
    public CardEntity getCardById(UUID id) {
        return cardDao.findById(id).orElse(null);
    }

    @Override
    public CardEntity saveCard(CardEntity card) {
        // Валидация уникальности
        if (!Objects.isNull(card.getId()) && cardDao.findById(card.getId()).isPresent()) {
            throw new RuntimeException("Card with this ID already exists.");
        }
        return cardDao.save(card);
    }

    @Override
    public CardEntity updateCard(UUID id, CardEntity card) {
        if (cardDao.findById(id).isEmpty()) {
            throw new RuntimeException("Card not found.");
        }
        card.setId(id);
        return cardDao.save(card);
    }

    @Override
    public void deleteCard(UUID id) {
        cardDao.deleteById(id);
    }

    @Override
    public List<CardEntity> getCardsByOwner(String firstName, String surName, String familyName) {
        return cardDao.findCardsByOwner(firstName, surName, familyName);
    }

    @Override
    public Optional<CardEntity> getCardsByName(String name) {
        return cardDao.findCardsByName(name);
    }


    @Override
    public String getCardImageByName(String cardName) {
        PokemonCardResponse response = restClient.getCardByName(cardName);
        if (response != null && !response.getData().isEmpty()) {
            return response.getData().get(0).getImages().getLarge(); // Получаем большое изображение карты
        }
        return null; // Если карта не найдена
    }
}