package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.service.AdminGenreService;
import com.gcit.lms.util.ErrorResponse;

@RestController
@RequestMapping("/admin/genre/*")
public class AdminGenreController {

	@Autowired
	AdminGenreService adminService;

	// ************************************************************************
	//
	@RequestMapping(value = "/readGenres", produces = { "application/json", "application/xml" })
	public List<Genre> readGenres(@RequestParam("name") String name) {
		return adminService.readGenres(name);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/readGenreById", produces = { "application/json", "application/xml" })
	public Genre readGenreById(@RequestParam("id") Integer id) {
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
	public ErrorResponse deleteGenre(@RequestParam("id") Integer id) {
		return adminService.deleteGenre(id);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/editGenre", produces = { "application/json", "application/xml" })
	public ErrorResponse editGenre(@RequestParam("name") String gName, @RequestParam("id") Integer gId) {
		return adminService.editGenre(gName, gId);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/saveGenreByName", produces = { "application/json", "application/xml" })
	public ErrorResponse saveGenreByName(@RequestParam("name") String gName) {
		return adminService.saveGenreByName(gName);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/saveGenreWithId", produces = { "application/json", "application/xml" })
	public Integer saveGenreWithId(@RequestParam("name") String gName) {
		return adminService.saveGenreWithId(gName);
	}

	// ************************************************************************
	//
	@RequestMapping(name = "/saveGenre", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public Genre saveGenre(@RequestBody Genre genre) {
		return adminService.saveGenre(genre);
	}
}
