package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcit.lms.entity.Book;
import com.gcit.lms.repositories.BookRepository;
import com.gcit.lms.util.ErrorResponse;

@Service
public class AdminBookService {

	@Autowired
	BookRepository bookRepo;

	//****************************************************************************
	//
	public List<Book> readBooks(String title) {
		List<Book> books = new ArrayList<>();
		try {
			if (!title.isEmpty()) {
				books = bookRepo.readBooks(title);
			} else {
				books = bookRepo.findAll();
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	//************************************************************************
	//		
	public ErrorResponse deleteBook(Integer bId) {
		ErrorResponse resp = new ErrorResponse();
		try {
			bookRepo.deleteById(bId);
			resp.setErrorMessage("Successfully deleted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//****************************************************************************
	//
	public Book readBookById(Integer id) {
		Book book =new Book();
		try {
			if (id == null || id == 0) {
				return null;
			} else {
				book = bookRepo.readBookById(id);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	//************************************************************************
	//		
	public ErrorResponse editBook(String bTitle, Integer bId) {
		ErrorResponse resp = new ErrorResponse();
		try {
			bookRepo.editBook(bTitle, bId);
			resp.setErrorMessage("Successfully editted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//****************************************************************************
	//
	public Book saveBook(Book book) {
		Book returnedBook = new Book();
		try {
			returnedBook=bookRepo.saveAndFlush(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnedBook;
	}
}
