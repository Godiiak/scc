package com.currencyconverter.scc.entities;

import org.springframework.stereotype.Component;

@Component
public class Request {
    private String fromCurrencyId;
    private String toCurrencyId;
    private String amount;

    public String getFromCurrencyId() {
        return fromCurrencyId;
    }

    public void setFromCurrencyId(String fromCurrencyId) {
        this.fromCurrencyId = fromCurrencyId;
    }

    public String getToCurrencyId() {
        return toCurrencyId;
    }

    public void setToCurrencyId(String toCurrencyId) {
        this.toCurrencyId = toCurrencyId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Request{" +
                "fromCurrencyId='" + fromCurrencyId + '\'' +
                ", toCurrencyId='" + toCurrencyId + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
