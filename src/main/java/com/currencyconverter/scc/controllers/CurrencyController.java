package com.currencyconverter.scc.controllers;

import com.currencyconverter.scc.entities.Currency;
import com.currencyconverter.scc.entities.Request;
import com.currencyconverter.scc.servicies.CurrencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/convert_currency")
    @ResponseBody
    public String convertCurrencies(@RequestBody Request request){
        return currencyService.convertCurrency(request.getFromCurrencyId(), request.getToCurrencyId(), request.getAmount());
    }

    @GetMapping("/get_currencies_list")
    public Iterable<Currency> getCurrenciesList(){
        return currencyService.getCurrenciesList();
    }
}
