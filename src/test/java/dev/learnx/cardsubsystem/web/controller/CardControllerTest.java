package dev.learnx.cardsubsystem.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.learnx.cardsubsystem.domain.Card;
import dev.learnx.cardsubsystem.service.CardService;
import dev.learnx.cardsubsystem.web.model.CardDto;
import dev.learnx.cardsubsystem.web.model.CardIssuerEnum;
import dev.learnx.cardsubsystem.web.model.CardStatusEnum;
import dev.learnx.cardsubsystem.web.model.CardTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @MockBean
    CardService cardService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CardDto cardDtoObject;
    Random random = new Random();
    public final String CARD_1_UPC = "0631234200036";

    @BeforeEach
    public void setUp() {
        cardDtoObject = CardDto.builder()
                .id(UUID.randomUUID())
                .cardNumber("370000000000002")
                .cardStatus(CardStatusEnum.ACTIVE.name())
                .cardType(CardTypeEnum.CREDIT.name())
                .cardIssuer(CardIssuerEnum.AMERICAN_EXPRESS.name())
                .upc(CARD_1_UPC)
                .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build();
    }

    @Test
    public void getCard() throws Exception {
        given(cardService.getById(any(UUID.class))).willReturn(cardDtoObject);

        mockMvc.perform(get("/api/v1/card/" + cardDtoObject.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(cardDtoObject.getId().toString())))
                .andExpect(jsonPath("$.cardNumber", is("370000000000002")));
    }

    @Test
    public void createCard() throws Exception {
        CardDto cardDto = cardDtoObject;
        cardDto.setId(null);
        String requestJson = objectMapper.writeValueAsString(cardDto);

        CardDto savedCardDto = CardDto.builder()
                .id(UUID.randomUUID())
                .cardNumber("370000000000002")
                .cardStatus(CardStatusEnum.ACTIVE.name())
                .cardType(CardTypeEnum.CREDIT.name())
                .cardIssuer(CardIssuerEnum.AMERICAN_EXPRESS.name())
                .upc(CARD_1_UPC)
                .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build();

        given(cardService.saveNewCard(any())).willReturn(savedCardDto);

        mockMvc.perform(post("/api/v1/card/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateCard() throws Exception {
        //given
        CardDto cardDto = cardDtoObject;
        cardDto.setId(null);
        String requestJson = objectMapper.writeValueAsString(cardDto);

        //when
        mockMvc.perform(put("/api/v1/card/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isNoContent());

        then(cardService).should().updateCard(any(), any());
    }

}
