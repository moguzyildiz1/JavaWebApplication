package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.repositories.GenreRepository;
import com.gcit.lms.util.GenreAlreadyExistsException;

@Service
public class AdminGenreService {

	@Autowired
	GenreRepository genreRepo;
	
	//************************************************************************
	//
	public ResponseEntity<?> readGenres(@RequestParam String name) {
		List<Genre> genres = new ArrayList<>();
		try {
			if (!name.isEmpty()) {
				genres = genreRepo.readGenres(name);
			} else {
				genres = (List<Genre>) genreRepo.findAll();
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(genres,HttpStatus.ACCEPTED);
	}
	//************************************************************************
	//		
	public ResponseEntity<?> readGenreById(Integer id) {
		Genre genre = new Genre();
		try {
			genre = genreRepo.readGenreById(id);
			if(genre==null) {
				return new ResponseEntity<>(genre,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(genre,HttpStatus.ACCEPTED);
	}
	//************************************************************************
	//		
	public ResponseEntity<?> editGenre(String genreName, Integer genreId) {
		Genre genre = new Genre();
		try {
			genreRepo.editGenre(genreName, genreId);
			genre=genreRepo.readGenreById(genreId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(genre,HttpStatus.ACCEPTED);
	}
	//************************************************************************
	//		
	public ResponseEntity<?> deleteGenre(Integer id) {
		Genre genre = new Genre();
		try {
			genre=genreRepo.readGenreById(id);
			genreRepo.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(genre,HttpStatus.ACCEPTED);
	}
	//************************************************************************
	//
	public ResponseEntity<?> saveGenreByName(String gName) {
		Integer newId;
		Genre genre=new Genre();
		genre.setGenreName(gName);
		try {
			newId=(genreRepo.saveAndFlush(genre)).getGenreId();
			genre=genreRepo.readGenreById(newId);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(genre,HttpStatus.ACCEPTED);
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
	public ResponseEntity<?> saveGenre(Genre genre, UriComponentsBuilder ucBuilder) {
	    if (genreRepo.findByName(genre.getGenreName()).getGenreId()!=null) {
	        throw new GenreAlreadyExistsException();
	    }
	    genre=genreRepo.saveAndFlush(genre);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/admin/genre/createGenre?id=").buildAndExpand(genre.getGenreId()).toUri());
	    return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	//************************************************************************
	//
//	public Genre saveGenre(Genre genre) {
//		Genre returnedGenre= new Genre();
//		try {
//			returnedGenre=genreRepo.saveAndFlush(genre);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return returnedGenre;
//	}
}
