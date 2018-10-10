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
	public String saveBorrower(Borrower borrower) {
		String returnString = "";
		try {
			borrowerRepo.save(borrower);
			returnString = "Auther saved sucessfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

	//	public String addLoan(RegisterBookDTO registerbto) {
	//
	//		Borrower borrower = borrowerRepo.readBorrowerByNo(Integer.getInteger(registerbto.getCardNo()));
	//		Book book = bookRepo.readBookById(Integer.getInteger(registerbto.getBookId()));
	//		Library library = libraryRepo.readLibraryById(Integer.getInteger(registerbto.getBranchId()));
	//
	//		if (borrower == null) {
	//			return "Unregistered User Card No";
	//		} else {
	//			Loan loan = new Loan();
	//			loan.setBorrower(borrower);
	//			loan.setBook(book);
	//			loan.setLibrary(library);
	//			java.sql.Date dateOut = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	//			java.sql.Date dueDate = addDays(dateOut,7);
	//			loan.setDateOut(dateOut);
	//			loan.setDueDate(dueDate);
	//			borrower.getLoans().add(loan);
	//		}
	//		return "You successfully check-out book";
	//	}

	// Helper method to adding day to sql.Date objects
	public Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return new Date(c.getTimeInMillis());
	}
}
