package com.gcit.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Author;
import com.gcit.lms.service.AdminAuthorService;

@RestController
@RequestMapping("/admin/author/*")
public class AdminAuthorController {

	@Autowired
	AdminAuthorService adminService;

	// ***************************************************************************
	//
	@RequestMapping(value = "/readAuthors", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> readAllAuthors(@RequestParam("name") String name) {
		return adminService.readAuthors(name);
	}

	// ***************************************************************************
	//
	@RequestMapping(value = "/readAuthorById", produces = { "application/json", "application/xml" })
	public ResponseEntity<?>  readAuthorById(@RequestParam("id") Integer id) {
		return adminService.readAuthorById(id);
	}

	// ***************************************************************************
	//
	@RequestMapping(value = "/saveAuthorWithId", produces = { "application/json", "application/xml" })
	public Integer saveAuthorWithId(@RequestParam("name") String name) {
		return adminService.saveAuthorWithId(name);
	}

	// ***************************************************************************
	//
	@RequestMapping("/initAuthor")
	public Author initAuthor() {
		return new Author();
	}
	// ***************************************************************************
	//
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<?> deleteAuthor(@RequestBody Author author) {
		return adminService.deleteAuthor(author);
	}
	// ***************************************************************************
	//
	@RequestMapping(value = "/saveAuthor", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<?> saveAuthor(@RequestBody Author author) {
		return adminService.saveAuthor(author);
	}

}
