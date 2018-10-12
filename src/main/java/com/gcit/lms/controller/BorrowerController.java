package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Loan;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

@RestController
@RequestMapping("/borrower/*")
public class BorrowerController {

	@Autowired
	LibrarianService librarianService;

	@Autowired
	BorrowerService borrowerService;
	
	@RequestMapping(value="/checkById", produces={"application/json","application/xml"})
	public boolean checkById(@RequestParam("cardNo") Integer cardNo) {
		return borrowerService.checkById(cardNo);
	}
	
	@RequestMapping(value="/availableBooks", produces={"application/json","application/xml"})
	public List<Book> readAvailableBooks(@RequestParam("branchId") Integer branchId) {
		return borrowerService.readAvailableBooks(branchId);
	}
	
	@RequestMapping(value="/readLoans", produces={"application/json","application/xml"})
	public List<Loan> readLoans(@RequestParam("cardNo") Integer cardNo) {
		return borrowerService.readLoans(cardNo);
	}

	@RequestMapping(value="/checkOut", produces={"application/json","application/xml"})
	public ResponseEntity<String> checkOutBook(@RequestParam("cardNo") Integer cardNo, @RequestParam("branchId") Integer branchId,
			@RequestParam("bookId") Integer bookId) {
		return borrowerService.checkOutBook(cardNo, branchId, bookId);
	}

	@RequestMapping(value="/checkIn", produces={"application/json","application/xml"})
	public String checkInBook(@RequestParam("cardNo") Integer cardNo, @RequestParam("branchId") Integer branchId,
			@RequestParam("bookId") Integer bookId) {
		return borrowerService.checkInBook(cardNo, branchId, bookId);
	}
}
