package dev.learnx.cardsubsystem.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {

    @Null
    private UUID id;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardStatus;

    private String upc;

    private BigDecimal balance;

    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
}
