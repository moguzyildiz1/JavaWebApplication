package com.gcit.lms.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.repositories.BookRepository;
import com.gcit.lms.repositories.BorrowerRepository;
import com.gcit.lms.repositories.LibraryRepository;
import com.gcit.lms.util.ErrorResponse;

@Service
public class AdminBorrowerService {

	@Autowired
	BorrowerRepository borrowerRepo;

	@Autowired
	BookRepository bookRepo;

	@Autowired
	LibraryRepository libraryRepo;

	//***************************************************************
	//
	public List<Borrower> readBorrowers(String name) {
		List<Borrower> borrowers = new ArrayList<>();
		try {
			if (!name.isEmpty()) {
				borrowers = borrowerRepo.readBorrowers(name);
			} else {
				borrowers = (List<Borrower>) borrowerRepo.findAll();
			}
		} catch (Exception e) {
			borrowers = (List<Borrower>) borrowerRepo.findAll();
			e.printStackTrace();
		}
		return borrowers;
	}
	//************************************************************************
	//		
	public ErrorResponse editBorrower(String bName, Integer bId) {
		ErrorResponse resp = new ErrorResponse();
		try {
			borrowerRepo.editBorrower(bName, bId);
			resp.setErrorMessage("Successfully editted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//
	public Borrower readBorrowerByNo(Integer id) {
		Borrower borrowers = new Borrower();
		try {
			if (id == null || id == 0) {
				borrowers = (Borrower) borrowerRepo.findAll();
			} else {
				borrowers = borrowerRepo.readBorrowerByNo(id);
			}
			return borrowers;
		} catch (NullPointerException ne) {
			borrowers = (Borrower) borrowerRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return borrowers;
	}
	//************************************************************************
	//
	public ErrorResponse saveBorrowerByParam(String bName, String bAddress, String bPhone) {
		ErrorResponse resp = new ErrorResponse();
		Integer newCardNo;
		Borrower borrower=new Borrower();
		borrower.setBorrowerName(bName);
		borrower.setAddress(bAddress);
		borrower.setPhone(bPhone);
		try {
			newCardNo=(borrowerRepo.saveAndFlush(borrower)).getCardNo();
			resp.setErrorMessage("Publisher added sucessfully with id: "+newCardNo);
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//		
	public ErrorResponse deleteBorrower(Integer bCardNo) {
		ErrorResponse resp = new ErrorResponse();
		try {
			borrowerRepo.deleteById(bCardNo);
			resp.setErrorMessage("Successfully deleted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//	
	public Borrower saveBorrower(Borrower borrower) {
		Borrower returnB= new Borrower();
		try {
			returnB=borrowerRepo.saveAndFlush(borrower);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnB;
	}
	//************************************************************************
	// Helper method to adding day to sql.Date objects
	public Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return new Date(c.getTimeInMillis());
	}
}
