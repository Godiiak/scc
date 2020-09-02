package com.currencyconverter.scc.repositories;

import com.currencyconverter.scc.entities.converter.HistoryEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryEntryRepository extends CrudRepository<HistoryEntry, Long> {
    List<HistoryEntry> findAllByUserId(Long aLong);
}
