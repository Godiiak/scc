package com.currencyconverter.scc.entities.converter;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyXMLHandler extends DefaultHandler {
    private static final String CURRENCIES= "ValCurs";
    private static final String DATE = "Date";
    private static final String CURRENCY = "Valute";
    private static final String CURRENCY_ID = "ID";
    private static final String CURRENCY_NUM_CODE = "NumCode";
    private static final String CURRENCY_CHAR_CODE = "CharCode";
    private static final String CURRENCY_NOMINAL = "Nominal";
    private static final String CURRENCY_NAME = "Name";
    private static final String CURRENCY_VALUE = "Value";

    private List<Currency> currencies;
    private Currency currency;
    private Rate rate;
    private String date;

    private String elementValue;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName){
            case CURRENCIES:
                currencies = new ArrayList<>();
                date = attributes.getValue(DATE);
                break;
            case CURRENCY:
                currency = new Currency();
                rate = new Rate();
                rate.setDate(date);
                currency.setId(attributes.getValue(CURRENCY_ID));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName){
            case CURRENCY:
                currency.getRates().add(rate);
                currencies.add(currency);
                break;
            case CURRENCY_NUM_CODE:
                currency.setNumeCode(elementValue);
                break;
            case CURRENCY_CHAR_CODE:
                currency.setCharCode(elementValue);
                break;
            case CURRENCY_NOMINAL:
                rate.setNominal(Integer.parseInt(elementValue));
                break;
            case CURRENCY_NAME:
                currency.setName(elementValue);
                break;
            case CURRENCY_VALUE:
                rate.setValue(Double.parseDouble(elementValue.replace(',', '.')));
                break;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
