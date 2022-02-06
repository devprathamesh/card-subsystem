package dev.learnx.cardsubsystem.web.model;

public enum CardTypeEnum {
    CONSUMER("consumer"), CORPORATE("corporate"), CREDIT("credit"), DEBIT("debit");

    private String type;

    CardTypeEnum(String type) {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }
}
