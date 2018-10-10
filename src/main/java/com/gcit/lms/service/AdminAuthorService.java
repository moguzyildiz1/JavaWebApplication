package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.repositories.AuthorRepository;

@Service
public class AdminAuthorService {

	@Autowired
	AuthorRepository authorRepo;

	//***********************************************************************************************
	//
	public List<Author> readAuthors(@RequestParam String name) {
		List<Author> authors = new ArrayList<>();
		try {
			if (!name.isEmpty()) {
				authors = authorRepo.readAuthors(name);
			} else {
				authors = (List<Author>) authorRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authors;
	}
	//**********************************************************************************************
	//
	public Integer saveAuthorWithId(String aName) {
		
		Integer newId=0;
		Author author =new Author();
		author.setAuthorName(aName);
		try {
			newId=(authorRepo.saveAndFlush(author)).getAuthorId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}

	//***********************************************************************************************
	//
	public Author readAuthorById(Integer id) {
		Author author = new Author();
		try {
			author = authorRepo.readAuthorsById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}
	//***********************************************************************************************
	//
	public String saveAuthor(Author author) {
		String returnString = "";
		try {
			authorRepo.save(author);
			// if (author.getAuthorId() != null && author.getAuthorName() !=
			// null) {
			// authorRepo.save(author);
			// returnString = "Auther updated sucessfully";
			// } else if (author.getAuthorId() != null) {
			// adao.deleteAuthor(author);
			// returnString = "Auther deleted sucessfully";
			// } else {
			// adao.addAuthor(author);
			// returnString = "Auther saved sucessfully";
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

}
