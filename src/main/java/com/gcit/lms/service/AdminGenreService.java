package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.repositories.GenreRepository;
import com.gcit.lms.util.ResultServe;

@Service
public class AdminGenreService {

	@Autowired
	GenreRepository genreRepo;

	// ************************************************************************
	//
	public ResponseEntity<ResultServe<List<Genre>>> readGenres(@RequestParam String name) {

		ResultServe<List<Genre>> result = new ResultServe<>();
		List<Genre> genres = new ArrayList<>();
		try {
			if (!name.isEmpty()) {
				genres = genreRepo.readGenres(name);
				result.setData(genres);
				result.setMessage("Genre: "+genres.get(0).getGenreName()+" returned successfully.");
			} else {
				genres = (List<Genre>) genreRepo.findAll();
				result.setData(genres);
				result.setMessage("All genres returned successfully.");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// ************************************************************************
	//
	public ResponseEntity<ResultServe<Genre>> readGenreById(Integer id) {
		
		ResultServe<Genre> result = new ResultServe<>();
		try {
			result.setData(genreRepo.readGenreById(id));
			result.setMessage("Genre returned successfully");			
			if (result.getData() == null) {
				return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	// ************************************************************************
	//
	public ResponseEntity<ResultServe<Genre>> editGenre(String genreName, Integer genreId) {
		
		ResultServe<Genre> result = new ResultServe<>();
		try {
			genreRepo.editGenre(genreName, genreId);
			result.setData(genreRepo.readGenreById(genreId));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// ************************************************************************
	//
	public ResponseEntity<ResultServe<Genre>> deleteGenre(Integer id) {

		ResultServe<Genre> result = new ResultServe<>();
		try {
			result.setData(genreRepo.readGenreById(id));
			result.setMessage("Genre: "+result.getData().getGenreId()+" successfully deleted.");
			genreRepo.deleteById(id);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// ************************************************************************
	//
	public ResponseEntity<ResultServe<Genre>> saveGenreByName(String gName) {
		
		ResultServe<Genre> result = new ResultServe<>();
		Integer newId;
		Genre genre = new Genre();
		genre.setGenreName(gName);
		try {
			newId = (genreRepo.saveAndFlush(genre)).getGenreId();
			genre = genreRepo.readGenreById(newId);
			result.setData(genre);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	// ************************************************************************
	//
	public Integer saveGenreWithId(String gName) {

		Integer newId = 0;
		Genre genre = new Genre();
		genre.setGenreName(gName);
		try {
			newId = (genreRepo.saveAndFlush(genre)).getGenreId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}

	// ************************************************************************
	//
	public ResponseEntity<ResultServe<Genre>> saveGenre(Genre genre) {
		
		ResultServe<Genre> result = new ResultServe<>();
		try {
			if (genreRepo.findByName(genre.getGenreName()).getGenreId() != null) {
				result.setMessage("Genre is already exists");
				return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
			}else
				result.setData(genreRepo.saveAndFlush(genre));
		}catch (Exception e) {
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	// ************************************************************************
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
