package melnikov.pkmn.controllers;
import melnikov.pkmn.models.Card;
import melnikov.pkmn.models.Student;
import melnikov.pkmn.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @GetMapping("/all")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/name/{name}")
    public Card getCardByName(@PathVariable String name) {
        return cardService.getCardByName(name);
    }

    @GetMapping("/owner")
    public Card getCardByPokemonOwner(@RequestBody Student student){
        return cardService.getCardByPokemonOwner(student);
    }

    @GetMapping("/id/{id}")
    public Card getCardById(@PathVariable UUID id) {
        return cardService.getCardById(id);
    }

    @PostMapping("")
    public Card saveCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @GetMapping("/image")
    public String getPokemonImage(@RequestParam String name, @RequestParam int number) {
        return cardService.getPokemonImage(name, number);
    }
}