package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.repositories.GenreRepository;
import com.gcit.lms.util.ErrorResponse;

@Service
public class AdminGenreService {

	@Autowired
	GenreRepository genreRepo;
	
	//************************************************************************
	//
	public List<Genre> readGenres(@RequestParam String name) {
		List<Genre> genres = new ArrayList<>();
		try {
			if (!name.isEmpty()) {
				genres = genreRepo.readGenres(name);
			} else {
				genres = (List<Genre>) genreRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genres;
	}
	//************************************************************************
	//		
	public Genre readGenreById(Integer id) {
		Genre genre = new Genre();
		try {
			genre = genreRepo.readGenreById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genre;
	}
	//************************************************************************
	//		
	public ErrorResponse editGenre(String genreName, Integer genreId) {
		ErrorResponse resp = new ErrorResponse();
		try {
			genreRepo.editGenre(genreName, genreId);
			resp.setErrorMessage("Successfully editted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//		
	public ErrorResponse deleteGenre(Integer id) {
		ErrorResponse resp = new ErrorResponse();
		try {
			genreRepo.deleteById(id);
			resp.setErrorMessage("Successfully deleted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//
	public ErrorResponse saveGenreByName(String gName) {
		ErrorResponse resp = new ErrorResponse();
		Integer newId;
		Genre genre=new Genre();
		genre.setGenreName(gName);
		try {
			newId=(genreRepo.saveAndFlush(genre)).getGenreId();
			resp.setErrorMessage("Genre adder sucessfully with id: "+newId);
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//
	public Integer saveGenreWithId(String gName) {

		Integer newId=0;
		Genre genre =new Genre();
		genre.setGenreName(gName);
		try {
			newId=(genreRepo.saveAndFlush(genre)).getGenreId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}
	//************************************************************************
	//
	public String saveGenre(Genre genre) {
		String returnString = "";
		try {
			genreRepo.save(genre);
			returnString = "Auther saved sucessfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
