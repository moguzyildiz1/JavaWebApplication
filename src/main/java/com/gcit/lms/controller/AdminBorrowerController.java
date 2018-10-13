package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.AdminBorrowerService;
import com.gcit.lms.util.ErrorResponse;

@RestController
@RequestMapping("/admin/borrower/*")
public class AdminBorrowerController {

	@Autowired
	AdminBorrowerService adminService;

	// ************************************************************************
	//
	@RequestMapping(value = "/readBorrowers", produces = { "application/json", "application/xml" })
	public List<Borrower> readBorrower(@RequestParam("name") String name) {
		return adminService.readBorrowers(name);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/readBorrowerByNo", produces = { "application/json", "application/xml" })
	public Borrower readBorrowerById(@RequestParam("cardNo") Integer cardNo) {
		return adminService.readBorrowerByNo(cardNo);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/editBorrower", produces = { "application/json", "application/xml" })
	public ErrorResponse editBorrower(@RequestParam("name") String bName, @RequestParam("cardNo") Integer bCardNo) {
		return adminService.editBorrower(bName, bCardNo);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/deleteBorrower", produces = { "application/json", "application/xml" })
	public ErrorResponse deleteBorrower(@RequestParam("cardNo") Integer bCardNo) {
		return adminService.deleteBorrower(bCardNo);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/saveBorrowerByParam", produces = { "application/json", "application/xml" })
	public ErrorResponse saveBorrowerByParam(@RequestParam("name") String bName,
			@RequestParam("address") String bAddress, @RequestParam("phone") String bPhone) {
		return adminService.saveBorrowerByParam(bName, bAddress, bPhone);
	}

	// ************************************************************************
	//
	@RequestMapping(name = "/saveBorrower", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public Borrower saveBorrower(@RequestBody Borrower borrower) {
		return adminService.saveBorrower(borrower);
	}
}
