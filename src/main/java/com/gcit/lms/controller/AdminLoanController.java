package com.gcit.lms.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Loan;
import com.gcit.lms.service.AdminLoanService;
import com.gcit.lms.util.ErrorResponse;

@RestController
@RequestMapping("/admin/loan/*")
public class AdminLoanController {

	@Autowired
	AdminLoanService adminService;
	
	// *************************************************************************
	//
	@RequestMapping("/readLoans")
	public List<Loan> readAllAuthors() {
		return adminService.readLoans();
	}
	
	// *************************************************************************
	//
	@RequestMapping("/editLoan")
	public ErrorResponse editLoan(@RequestParam("cardNo") Integer cardNo, @RequestParam("branchId") Integer branchId,
			@RequestParam("bookId") Integer bookId, @RequestParam("dueDate") Date dueDate) {
		return adminService.editLoan(cardNo, branchId, bookId, dueDate);
	}
}
