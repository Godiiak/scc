package com.currencyconverter.scc.servicies;

import com.currencyconverter.scc.entities.converter.HistoryEntry;
import com.currencyconverter.scc.repositories.HistoryEntryRepository;
import com.currencyconverter.scc.repositories.UserRepository;
import com.currencyconverter.scc.security.jwt.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HistoryService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final HistoryEntryRepository historyEntryRepository;

    public HistoryService(JwtUtils jwtUtils, UserRepository userRepository, HistoryEntryRepository historyEntryRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.historyEntryRepository = historyEntryRepository;
    }

    public List<HistoryEntry> getUserHistory(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            String userName = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7));
            if(userRepository.findByUsername(userName).isPresent()){
                long id = userRepository.findByUsername(userName).get().getId();
                return historyEntryRepository.findAllByUserId(id);
            }
        }
        return null;
    }

    public void addHistoryEntry(String header, String fromCurrencyId, String toCurrencyId, String amount, String result) {
        LocalDate nowLocalDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String nowDate = nowLocalDate.format(formatter);

        HistoryEntry historyEntry = new HistoryEntry();
        historyEntry.setFromCurrency(fromCurrencyId);
        historyEntry.setToCurrency(toCurrencyId);
        historyEntry.setAmount(amount);
        historyEntry.setResult(result);
        historyEntry.setDate(nowDate);
        if(userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(header.substring(7))).isPresent())
        historyEntry.setUserId(userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(header.substring(7))).get().getId());
        historyEntryRepository.save(historyEntry);
    }
}
