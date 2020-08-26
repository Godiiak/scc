package com.currencyconverter.scc.entities.converter;

import javax.persistence.*;

@Entity(name = "RATES")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String date;
    private int nominal;
    private double value;
    @Column(name = "currency_id")
    private String currencyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency_id() {
        return currencyId;
    }

    public void setCurrency_id(String currency_id) {
        this.currencyId = currency_id;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", nominal=" + nominal +
                ", value=" + value +
                ", currencyId='" + currencyId + '\'' +
                '}';
    }
}
