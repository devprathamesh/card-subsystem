package dev.learnx.cardsubsystem.web.controller;

import dev.learnx.cardsubsystem.service.CardService;
import dev.learnx.cardsubsystem.web.model.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class CardController {

    private final CardService cardService;

    @GetMapping("card/{cardId}")
    public ResponseEntity<CardDto> geCardById(@PathVariable("cardId") UUID cardId){
        return new ResponseEntity<>(cardService.getById(cardId), HttpStatus.OK);
    }

    @GetMapping("cardUpc/{upc}")
    public ResponseEntity<CardDto> getCardByUpc(@PathVariable("upc") String upc){
        return new ResponseEntity<>(cardService.getByUpc(upc), HttpStatus.OK);
    }

    @PutMapping("card/{cardId}")
    public ResponseEntity updateCardById(@PathVariable("cardId") UUID cardId, @RequestBody @Validated CardDto cardDto){
        return new ResponseEntity<>(cardService.updateCard(cardId, cardDto), HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "card")
    public ResponseEntity saveNewCard(@RequestBody @Validated CardDto cardDto){

        CardDto savedCard = cardService.saveNewCard(cardDto);

        return ResponseEntity
                .created(UriComponentsBuilder
                        .fromHttpUrl("http://127.0.0.1:8080/api/v1/card/" + savedCard.getId().toString())
                        .build().toUri())
                .build();
    }
}
