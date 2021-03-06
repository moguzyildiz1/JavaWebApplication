package com.gcit.lms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.service.AdminGenreService;
import com.gcit.lms.util.ForbiddenException;

@RestController
@RequestMapping("/admin/genre/*")
public class AdminGenreController {

	@Autowired
	AdminGenreService adminService;

	// ************************************************************************
	//
	@RequestMapping(value = "/", produces = { "application/json", "application/xml" })
	@ResponseBody
	public ResponseEntity<?> sendViaResponseEntity() {
	    throw new ForbiddenException();
	}
	// ************************************************************************
	//
	@RequestMapping(value = "/readGenres", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> readGenres(@RequestParam("name") String name) {
		return adminService.readGenres(name);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/readGenreById", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> readGenreById(@RequestParam("id") Integer id) {
		return adminService.readGenreById(id);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/initGenre", produces = { "application/json", "application/xml" })
	public Genre initGenre() {
		return new Genre();
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/deleteGenre", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> deleteGenre(@RequestParam("id") Integer id) {
		return adminService.deleteGenre(id);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/editGenre", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> editGenre(@RequestParam("name") String gName, @RequestParam("id") Integer gId) {
		return adminService.editGenre(gName, gId);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/saveGenreByName", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> saveGenreByName(@RequestParam("name") String gName) {
		return adminService.saveGenreByName(gName);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/saveGenreWithId", produces = { "application/json", "application/xml" })
	public Integer saveGenreWithId(@RequestParam("name") String gName) {
		return adminService.saveGenreWithId(gName);
	}
	//************************************************************************
	//Returns custom http response message 
	@RequestMapping(value = "/saveGenre", method = RequestMethod.POST, produces = { "application/json",
	"application/xml" })
	public ResponseEntity<?> saveGenre(@Valid @RequestBody Genre genre) {
	    return adminService.saveGenre(genre);
	}
	// ************************************************************************
	//
//	@RequestMapping(value = "/saveGenre", method = RequestMethod.POST, produces = { "application/json",
//			"application/xml" })
//	public ResponseEntity<?> saveGenre(@RequestBody Genre genre) {
//		return adminService.saveGenre(genre);
//	}
}
