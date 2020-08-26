package com.currencyconverter.scc.entities.converter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CURRENCIES")
public class Currency {
    @Id
    private String id;
    private String numeCode;
    private String charCode;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private List<Rate> rates;

    public Currency() {
        rates = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeCode() {
        return numeCode;
    }

    public void setNumeCode(String numeCode) {
        this.numeCode = numeCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", numeCode='" + numeCode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                ", rates=" + rates +
                '}';
    }
}
