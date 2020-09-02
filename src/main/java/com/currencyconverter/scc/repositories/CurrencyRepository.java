package com.currencyconverter.scc.repositories;

import com.currencyconverter.scc.entities.converter.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
