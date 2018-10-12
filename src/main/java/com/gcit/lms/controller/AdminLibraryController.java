package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Library;
import com.gcit.lms.service.LibrarianService;

@RestController
@RequestMapping("/admin/library/*")
public class AdminLibraryController {

	@Autowired
	LibrarianService libService;

	@RequestMapping(value = "/readLibraries", produces = { "application/json", "application/xml" })
	public List<Library> readAllLibrarys(@RequestParam("name") String name) {
		return libService.readLibraries(name);
	}

	@RequestMapping(value = "/readLibraryById", produces = { "application/json", "application/xml" })
	public Library readLibraryById(@RequestParam("id") Integer id) {

		return libService.readLibraryById(id);
	}

	@RequestMapping(name = "/saveLibrary", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public Library saveLibrary(@RequestBody Library library) {
		return libService.saveLibrary(library);
	}

}
