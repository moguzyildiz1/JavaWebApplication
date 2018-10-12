package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Book;
import com.gcit.lms.service.AdminBookService;
import com.gcit.lms.util.ErrorResponse;

@RestController
@RequestMapping("/admin/book/*")
public class AdminBookController {

	@Autowired
	AdminBookService adminService;

	// ****************************************************************************
	//
	@RequestMapping(value = "/readBooks", produces = { "application/json", "application/xml" })
	public List<Book> readBooks(@RequestParam("title") String title) {
		return adminService.readBooks(title);
	}

	// ****************************************************************************
	//
	@RequestMapping(value = "/readBookById", produces = { "application/json", "application/xml" })
	public Book readBookById(@RequestParam("id") Integer id) {
		return adminService.readBookById(id);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/editBook", produces = { "application/json", "application/xml" })
	public ErrorResponse editBook(@RequestParam("title") String bTitle, @RequestParam("id") Integer bId) {
		return adminService.editBook(bTitle, bId);
	}

	// **********************************************************************************************
	//
	@RequestMapping(value = "/initBook", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public Book initBook() {
		return new Book();
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/deleteBook", produces = { "application/json", "application/xml" })
	public ErrorResponse deleteBook(@RequestParam("id") Integer bId) {
		return adminService.deleteBook(bId);
	}

	// ****************************************************************************
	//
	@RequestMapping(name = "/saveBook", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" }, consumes = "text/html")
	public Book saveBook(@RequestBody Book book) {
		return adminService.saveBook(book);
	}
}
