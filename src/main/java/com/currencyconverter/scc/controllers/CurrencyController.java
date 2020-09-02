package com.currencyconverter.scc.controllers;

import com.currencyconverter.scc.entities.converter.Currency;
import com.currencyconverter.scc.entities.converter.Request;
import com.currencyconverter.scc.servicies.CurrencyService;
import com.currencyconverter.scc.servicies.HistoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CurrencyController {
    private final CurrencyService currencyService;
    private final HistoryService historyService;

    public CurrencyController(CurrencyService currencyService, HistoryService historyService) {
        this.currencyService = currencyService;
        this.historyService = historyService;
    }

    @PostMapping("/convert_currency")
    @ResponseBody
    public String convertCurrencies(@RequestBody Request body, @RequestHeader(name = "Authorization", required = false) String header){
        String result = currencyService.convertCurrency(body.getFromCurrencyId(), body.getToCurrencyId(), body.getAmount());
        if(header != null){
            historyService.addHistoryEntry(header,
                    currencyService.getCurrencyById(body.getFromCurrencyId()),
                    currencyService.getCurrencyById(body.getToCurrencyId()),
                    body.getAmount(),
                    result);
        }
        return result;
    }

    @GetMapping("/get_currencies_list")
    public Iterable<Currency> getCurrenciesList(){
        return currencyService.getCurrenciesList();
    }
}
