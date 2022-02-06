package dev.learnx.cardsubsystem.bootstrap;


import dev.learnx.cardsubsystem.domain.Card;
import dev.learnx.cardsubsystem.repository.CardRepository;
import dev.learnx.cardsubsystem.web.model.CardIssuerEnum;
import dev.learnx.cardsubsystem.web.model.CardStatusEnum;
import dev.learnx.cardsubsystem.web.model.CardTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Component
public class CardBootstrapLoader implements CommandLineRunner {
    private final CardRepository cardRepository;

    public static final String CARD_1_UPC = "0631234200036";
    public static final String CARD_2_UPC = "9122089364369";
    public static final String CARD_3_UPC = "0631234200001";
    public static final String CARD_4_UPC = "9122089364302";
    public static final String CARD_5_UPC = "0631234200003";
    public static final String CARD_6_UPC = "9122089364304";

    @Override
    public void run(String... args) throws Exception {
        loadCardObjects();
    }

    private synchronized void loadCardObjects() {
        log.debug("Loading initial data. Count is: {}", cardRepository.count());
        if (cardRepository.count() == 0) {
            Random random = new Random();
            cardRepository.save(Card.builder()
                    .cardNumber("370000000000002")
                    .cardStatus(CardStatusEnum.ACTIVE)
                    .cardType(CardTypeEnum.CREDIT)
                    .cardIssuer(CardIssuerEnum.AMERICAN_EXPRESS)
                    .upc(CARD_1_UPC)
                    .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build());
            cardRepository.save(Card.builder()
                    .cardNumber("6011601160116611")
                    .cardStatus(CardStatusEnum.ACTIVE)
                    .cardType(CardTypeEnum.DEBIT)
                    .cardIssuer(CardIssuerEnum.DISCOVER)
                    .upc(CARD_2_UPC)
                    .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build());
            cardRepository.save(Card.builder()
                    .cardNumber("5577000055770004")
                    .cardStatus(CardStatusEnum.ACTIVE)
                    .cardType(CardTypeEnum.CONSUMER)
                    .cardIssuer(CardIssuerEnum.MASTERCARD)
                    .upc(CARD_3_UPC)
                    .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build());
            cardRepository.save(Card.builder()
                    .cardNumber("4000620000000007")
                    .cardStatus(CardStatusEnum.ACTIVE)
                    .cardType(CardTypeEnum.CREDIT)
                    .cardIssuer(CardIssuerEnum.VISA)
                    .upc(CARD_4_UPC)
                    .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build());
            cardRepository.save(Card.builder()
                    .cardNumber("6445644564456445")
                    .cardStatus(CardStatusEnum.ACTIVE)
                    .cardType(CardTypeEnum.DEBIT)
                    .cardIssuer(CardIssuerEnum.DINERS)
                    .upc(CARD_5_UPC)
                    .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build());
            cardRepository.save(Card.builder()
                    .cardNumber("2222400070000005")
                    .cardStatus(CardStatusEnum.ACTIVE)
                    .cardType(CardTypeEnum.DEBIT)
                    .cardIssuer(CardIssuerEnum.MASTERCARD)
                    .upc(CARD_6_UPC)
                    .balance(new BigDecimal(BigInteger.valueOf(random.nextInt(10000)), 2)).build());
            log.debug("Card Records loaded: {}", cardRepository.count());
        }
    }
}
