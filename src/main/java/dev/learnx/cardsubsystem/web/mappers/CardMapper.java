package dev.learnx.cardsubsystem.web.mappers;

import dev.learnx.cardsubsystem.domain.Card;
import dev.learnx.cardsubsystem.web.model.CardDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CardMapper {

    CardDto cardToCardDto(Card card);

    Card cardDtoToCard(CardDto cardDto);
}

