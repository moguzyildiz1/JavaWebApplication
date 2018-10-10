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

	@RequestMapping("/readAuthors")
	public List<Author> readAllAuthors(@RequestParam("name") String name) {
		return adminAutService.readAuthors(name);
	}
	//***************************************************************************
	//
	@RequestMapping("/readAuthorById")
	public Author readAuthorById(@RequestParam("id") Integer id) {
		return adminAutService.readAuthorById(id);
	}
	//***************************************************************************
	//
	@RequestMapping("/saveAuthorWithId")
	public Integer saveAuthorWithId(@RequestParam("name") String name) {
		return adminAutService.saveAuthorWithId(name);
	}	
	//***************************************************************************
	//
	@RequestMapping("/initAuthor")
	public Author initAuthor() {
		return new Author();
	}
	//	@RequestMapping("/saveAuthorByName")
	//	public List<Author> readAuthorsByName(@RequestParam("name") String name) {
	//
	//		return adminAutService.readAuthorsByName(name);
	//	}
	@RequestMapping(name = "/saveAuthor", method = RequestMethod.POST)
	public String saveAuthor(@RequestBody Author author) {
		return adminAutService.saveAuthor(author);
	}
	
}
