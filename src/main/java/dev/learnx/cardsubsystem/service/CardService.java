package dev.learnx.cardsubsystem.service;

import dev.learnx.cardsubsystem.web.model.CardDto;

import java.util.UUID;

public interface CardService {

    CardDto getById(UUID cardId);

    CardDto saveNewCard(CardDto cardDto);

    CardDto updateCard(UUID cardId, CardDto cardDto);

    CardDto getByUpc(String upc);
}
