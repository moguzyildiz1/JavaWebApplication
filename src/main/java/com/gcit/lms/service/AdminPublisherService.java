package com.gcit.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Publisher;
import com.gcit.lms.repositories.PublisherRepository;
import com.gcit.lms.util.ErrorResponse;

@Service
public class AdminPublisherService {

	@Autowired
	PublisherRepository publisherRepo;

	//****************************************************************************************
	//
	public List<Publisher> readPublishers(@RequestParam String name) {
		List<Publisher> publishers = new ArrayList<>();
		try {
			if (!name.isEmpty()) {
				publishers = publisherRepo.readPublishers(name);
			} else {
				publishers = (List<Publisher>) publisherRepo.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publishers;
	}
	//****************************************************************************************
	//
	public Publisher readPublisherById(Integer id) {
		Publisher publisher = new Publisher();
		try {
			publisher = publisherRepo.readPublisherById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publisher;
	}
	//************************************************************************
	//		
	public ErrorResponse editPublisher(String pName, Integer pId) {
		ErrorResponse resp = new ErrorResponse();
		try {
			publisherRepo.editPublisher(pName, pId);
			resp.setErrorMessage("Successfully editted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//		
	public ErrorResponse deletePublisher(Integer pId) {
		ErrorResponse resp = new ErrorResponse();
		try {
			publisherRepo.deleteById(pId);
			resp.setErrorMessage("Successfully deleted.");
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//
	public ErrorResponse savePublisherByParam(String pName, String pAddress, String pPhone) {
		ErrorResponse resp = new ErrorResponse();
		Integer newId;
		Publisher publisher=new Publisher();
		publisher.setPubName(pName);
		publisher.setPubAddress(pAddress);
		publisher.setPubPhone(pPhone);
		try {
			newId=(publisherRepo.saveAndFlush(publisher)).getPublisherId();
			resp.setErrorMessage("Publisher added sucessfully with id: "+newId);
		} catch (Exception e) {
			resp.setErrorMessage("Exception occurred. Failed to update.");
			e.printStackTrace();
		}
		return resp;
	}
	//************************************************************************
	//
	public Integer savePublisherWithId(String pName, String pAddress, String pPhone) {
		
		Integer newId=0;
		Publisher publisher=new Publisher();
		publisher.setPubName(pName);
		publisher.setPubAddress(pAddress);
		publisher.setPubPhone(pPhone);
		try {
			newId=(publisherRepo.saveAndFlush(publisher)).getPublisherId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}
	//****************************************************************************************
	//
	public String savePublisher(Publisher publisher) {
		String returnString = "";
		try {
			publisherRepo.save(publisher);
			returnString = "Publisher saved sucessfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
