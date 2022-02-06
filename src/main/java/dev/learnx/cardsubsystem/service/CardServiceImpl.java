package dev.learnx.cardsubsystem.service;

import dev.learnx.cardsubsystem.domain.Card;
import dev.learnx.cardsubsystem.repository.CardRepository;
import dev.learnx.cardsubsystem.web.exception.CardNotFoundException;
import dev.learnx.cardsubsystem.web.mappers.CardMapper;
import dev.learnx.cardsubsystem.web.model.CardDto;
import dev.learnx.cardsubsystem.web.model.CardStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public CardDto getById(UUID cardId) {
        return cardMapper.cardToCardDto(
                cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new)
        );    }

    @Override
    public CardDto saveNewCard(CardDto cardDto) {
        return null;
    }

    @Override
    public CardDto updateCard(UUID cardId, CardDto cardDto) {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        card.setCardStatus(CardStatusEnum.valueOf(cardDto.getCardStatus()));
        card.setBalance(cardDto.getBalance());
        return cardMapper.cardToCardDto(cardRepository.save(card));
    }

    @Override
    public CardDto getByUpc(String upc) {
        return cardMapper.cardToCardDto(cardRepository.findByUpc(upc));
    }
}
