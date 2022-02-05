package dev.learnx.cardsubsystem.service;

import dev.learnx.cardsubsystem.web.model.CardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    @Override
    public CardDto getById(UUID cardId) {
        return null;
    }

    @Override
    public CardDto saveNewCard(CardDto cardDto) {
        return null;
    }

    @Override
    public CardDto updateCard(UUID cardId, CardDto cardDto) {
        return null;
    }

    @Override
    public CardDto getByUpc(String upc) {
        return null;
    }

    @Override
    public void deleteCardById(UUID cardId) {

    }
}
