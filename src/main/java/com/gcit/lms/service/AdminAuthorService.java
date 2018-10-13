package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.repositories.AuthorRepository;

@Service
public class AdminAuthorService {

	@Autowired
	AuthorRepository authorRepo;

	// ***********************************************************************************************
	//
	public ResponseEntity<?> readAuthors(@RequestParam String name) {
		List<Author> authors = new ArrayList<>();
		try {
			if (!name.isEmpty()) {
				authors = authorRepo.readAuthors(name);
				return new ResponseEntity<>(authors,HttpStatus.ACCEPTED);
			} else {
				authors = (List<Author>) authorRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(authors,HttpStatus.OK);
	}

	// **********************************************************************************************
	//
	public Integer saveAuthorWithId(String aName) {

		Integer newId = 0;
		Author author = new Author();
		author.setAuthorName(aName);
		try {
			newId = (authorRepo.saveAndFlush(author)).getAuthorId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}
	// ***********************************************************************************************
	//
	public ResponseEntity<?> readAuthorById(Integer id) {
		Author author = new Author();
		try {
			author = authorRepo.readAuthorsById(id);
			return new ResponseEntity<>(author,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(author,HttpStatus.OK);
	}
	// ***********************************************************************************************
	//
	public ResponseEntity<?> deleteAuthor(Author author) {
		try {
			authorRepo.delete(author);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(author, HttpStatus.ACCEPTED);
	}
	// ***********************************************************************************************
	//
	public ResponseEntity<?> saveAuthor(Author author) {
		try {
			authorRepo.save(author);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(author, HttpStatus.CREATED);
	}
}
