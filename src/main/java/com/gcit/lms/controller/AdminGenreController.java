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

	//************************************************************************
	//
	@RequestMapping("/readGenres")
	public List<Genre> readGenres(@RequestParam("name") String name) {
		return adminService.readGenres(name);
	}
	//************************************************************************
	//
	@RequestMapping("/readGenreById")
	public Genre readGenreById(@RequestParam("id") Integer id) {
		return adminService.readGenreById(id);
	}
	//************************************************************************
	//
	@RequestMapping("/initGenre")
	public Genre initGenre() {
		return new Genre();
	}
	//************************************************************************
	//
	@RequestMapping("/deleteGenre")
	public ErrorResponse deleteGenre(@RequestParam("id") Integer id) {
		return adminService.deleteGenre(id);
	}
	//************************************************************************
	//
	@RequestMapping("/editGenre")
	public ErrorResponse editGenre(@RequestParam("name") String gName, @RequestParam("id") Integer gId) {
		return adminService.editGenre(gName,gId);
	}
	//************************************************************************
	//
	@RequestMapping("/saveGenreByName")
	public ErrorResponse saveGenreByName(@RequestParam("name") String gName) {
		return adminService.saveGenreByName(gName);
	}
	//************************************************************************
	//
	@RequestMapping("/saveGenreWithId")
	public Integer saveGenreWithId(@RequestParam("name") String gName) {
		return adminService.saveGenreWithId(gName);
	}
	//************************************************************************
	//
	@RequestMapping(name = "/saveGenre", method = RequestMethod.POST)
	public String saveGenre(@RequestBody Genre genre) {
		return adminService.saveGenre(genre);
	}	
}
