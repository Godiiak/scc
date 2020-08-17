package com.currencyconverter.scc.repositories;

import com.currencyconverter.scc.entities.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
}
