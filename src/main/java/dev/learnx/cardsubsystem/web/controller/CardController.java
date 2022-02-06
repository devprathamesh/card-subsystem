package dev.learnx.cardsubsystem.web.controller;

import dev.learnx.cardsubsystem.service.CardService;
import dev.learnx.cardsubsystem.web.model.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
