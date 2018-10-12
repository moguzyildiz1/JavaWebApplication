package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	AdminAuthorService adminAutService;

	// ***************************************************************************
	//
	@RequestMapping(value = "/readAuthors", produces = { "application/json", "application/xml" })
	public List<Author> readAllAuthors(@RequestParam("name") String name) {
		return adminAutService.readAuthors(name);
	}

	// ***************************************************************************
	//
	@RequestMapping(value = "/readAuthorById", produces = { "application/json", "application/xml" })
	public Author readAuthorById(@RequestParam("id") Integer id) {
		return adminAutService.readAuthorById(id);
	}

	// ***************************************************************************
	//
	@RequestMapping(value = "/saveAuthorWithId", produces = { "application/json", "application/xml" })
	public Integer saveAuthorWithId(@RequestParam("name") String name) {
		return adminAutService.saveAuthorWithId(name);
	}

	// ***************************************************************************
	//
	@RequestMapping("/initAuthor")
	public Author initAuthor() {
		return new Author();
	}

	// @RequestMapping("/saveAuthorByName")
	// public List<Author> readAuthorsByName(@RequestParam("name") String name) {
	//
	// return adminAutService.readAuthorsByName(name);
	// }
	@RequestMapping(name = "/saveAuthor", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public String saveAuthor(@RequestBody Author author) {
		return adminAutService.saveAuthor(author);
	}

}
