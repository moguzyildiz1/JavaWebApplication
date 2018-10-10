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

	//************************************************************************
	//
	@RequestMapping("/readBorrowers")
	public List<Borrower> readBorrower(@RequestParam("name") String name) {
		return adminService.readBorrowers(name);
	}
	//************************************************************************
	//
	@RequestMapping("/readBorrowerByNo")
	public Borrower readBorrowerById(@RequestParam("cardNo") Integer cardNo) {
		return adminService.readBorrowerByNo(cardNo);
	}
	//************************************************************************
	//
	@RequestMapping("/editBorrower")
	public ErrorResponse editBorrower(@RequestParam("name") String bName, @RequestParam("cardNo") Integer bCardNo) {
		return adminService.editBorrower(bName,bCardNo);
	}
	// ************************************************************************
	//
	@RequestMapping("/deleteBorrower")
	public ErrorResponse deleteBorrower(@RequestParam("cardNo") Integer bCardNo) {
		return adminService.deleteBorrower(bCardNo);
	}
	// ************************************************************************
	//
	@RequestMapping("/saveBorrowerByParam")
	public ErrorResponse saveBorrowerByParam(@RequestParam("name") String bName, @RequestParam("address") String bAddress,
			@RequestParam("phone") String bPhone) {
		return adminService.saveBorrowerByParam(bName,bAddress,bPhone);
	}
	//************************************************************************
	//
	@RequestMapping(name = "/saveBorrower", method = RequestMethod.POST)
	public String saveBorrower(@RequestBody Borrower borrower) {
		return adminService.saveBorrower(borrower);
	}
}
