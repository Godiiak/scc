package com.currencyconverter.scc.controllers;

import com.currencyconverter.scc.entities.converter.HistoryEntry;
import com.currencyconverter.scc.servicies.HistoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	private final HistoryService historyService;

	public TestController(HistoryService historyService) {
		this.historyService = historyService;
	}

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@GetMapping("/history")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@ResponseBody
	public List<HistoryEntry> getHistory(HttpServletRequest request){
		return historyService.getUserHistory(request);
	}
}
