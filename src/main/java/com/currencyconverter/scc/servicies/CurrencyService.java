package com.currencyconverter.scc.servicies;

import com.currencyconverter.scc.configurations.ServerConfig;
import com.currencyconverter.scc.entities.converter.Currency;
import com.currencyconverter.scc.entities.converter.CurrencyXMLHandler;
import com.currencyconverter.scc.entities.converter.Rate;
import com.currencyconverter.scc.repositories.CurrencyRepository;
import com.currencyconverter.scc.repositories.RateRepository;
import org.aeonbits.owner.ConfigFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RateRepository rateRepository;

    public CurrencyService(CurrencyRepository currencyRepository, RateRepository rateRepository) {
        this.currencyRepository = currencyRepository;
        this.rateRepository = rateRepository;
    }

    @PostConstruct
    void getCurrentRates(){
        ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverConfig.hostname()))
                .build();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        CurrencyXMLHandler handler = new CurrencyXMLHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(client.send(request, HttpResponse.BodyHandlers.ofInputStream()).body(), handler);
            currencyRepository.saveAll(handler.getCurrencies());
        } catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String convertCurrency(String fromCurrencyId, String toCurrencyId, String value){
        LocalDate nowLocalDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String nowDate = nowLocalDate.format(formatter);
        Rate rateFromCurrency = rateRepository.findByCurrencyIdAndDate(fromCurrencyId, nowDate);
        Rate rateToCurrency = rateRepository.findByCurrencyIdAndDate(toCurrencyId, nowDate);
        return BigDecimal.valueOf(rateFromCurrency.getValue())
                .multiply(BigDecimal.valueOf(rateToCurrency.getNominal()))
                .multiply(BigDecimal.valueOf(Double.parseDouble(value)))
                .divide(BigDecimal.valueOf(rateToCurrency.getValue()), RoundingMode.HALF_DOWN)
                .divide(BigDecimal.valueOf(rateFromCurrency.getNominal()), RoundingMode.HALF_DOWN)
                .setScale(2, RoundingMode.HALF_DOWN)
                .toString();
    }

    public Iterable<Currency> getCurrenciesList(){
        return currencyRepository.findAll();
    }

    public String getCurrencyById(String id){
        return currencyRepository.findById(id).get().getName();
    }
}
