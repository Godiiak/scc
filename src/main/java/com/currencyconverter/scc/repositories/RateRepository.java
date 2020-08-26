package com.currencyconverter.scc.repositories;

import com.currencyconverter.scc.entities.converter.Rate;
import org.springframework.data.repository.CrudRepository;

public interface RateRepository extends CrudRepository<Rate, Long> {
    Rate findByCurrencyIdAndDate (String currencyId, String date);
}
