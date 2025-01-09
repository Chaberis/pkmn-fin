package melnikov.pkmn.controllers;
import melnikov.pkmn.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.HttpStatus;
import melnikov.pkmn.entities.CardEntity;
import melnikov.pkmn.entities.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    @Autowired
    private final CardService cardService;

    @GetMapping("")
    public List<CardEntity> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CardEntity> getCardById(@PathVariable UUID id) {
        CardEntity card = cardService.getCardById(id);
        return card != null ? ResponseEntity.ok(card) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<CardEntity> createCard(@RequestBody CardEntity card) {
        CardEntity savedCard = cardService.saveCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public CardEntity updateCard(@PathVariable UUID id, @RequestBody CardEntity card) {
        return cardService.updateCard(id, card);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner")
    public List<CardEntity> getCardsByOwner(@RequestBody StudentEntity ownerRequest) {
        return cardService.getCardsByOwner(ownerRequest.getFirstName(), ownerRequest.getSurName(), ownerRequest.getFatherName());
    }

    @GetMapping("/{name}")
    public Optional<CardEntity> getCardsByName(@PathVariable String name) {
        return cardService.getCardsByName(name);
    }

    @GetMapping("/card/image")
    public ResponseEntity<String> getCardImageByName(@RequestParam String cardName) {
        String imageUrl = cardService.getCardImageByName(cardName);
        return imageUrl != null ? ResponseEntity.ok(imageUrl) : ResponseEntity.notFound().build();
    }
}