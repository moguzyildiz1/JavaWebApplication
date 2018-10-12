package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminPublisherService;
import com.gcit.lms.util.ErrorResponse;

@RestController
@RequestMapping("/admin/publisher/*")
public class AdminPublisherController {

	@Autowired
	AdminPublisherService adminService;

	// ****************************************************************************************
	//
	@RequestMapping(value = "/readPublishers", produces = { "application/json", "application/xml" })
	public List<Publisher> readPublishers(@RequestParam("name") String name) {
		return adminService.readPublishers(name);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/editPublisher", produces = { "application/json", "application/xml" })
	public ErrorResponse editPublisher(@RequestParam("name") String pName, @RequestParam("id") Integer pId) {
		return adminService.editPublisher(pName, pId);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/savePublisherByParam", produces = { "application/json", "application/xml" })
	public ErrorResponse savePublisherByParam(@RequestParam("name") String pName,
			@RequestParam("address") String pAddress, @RequestParam("phone") String pPhone) {
		return adminService.savePublisherByParam(pName, pAddress, pPhone);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/savePublisherWithId", produces = { "application/json", "application/xml" })
	public Integer savePublisherWithId(@RequestParam("name") String pName, @RequestParam("address") String pAddress,
			@RequestParam("phone") String pPhone) {
		return adminService.savePublisherWithId(pName, pAddress, pPhone);
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/initPublisher", produces = { "application/json", "application/xml" })
	public Publisher initPublisher() {
		return new Publisher();
	}

	// ************************************************************************
	//
	@RequestMapping(value = "/deletePublisher", produces = { "application/json", "application/xml" })
	public ErrorResponse deletePublisher(@RequestParam("id") Integer pId) {
		return adminService.deletePublisher(pId);
	}

	// ****************************************************************************************
	//
	@RequestMapping(value = "/readPublisherById", produces = { "application/json", "application/xml" })
	public Publisher readPublisherById(@RequestParam("id") Integer id) {
		return adminService.readPublisherById(id);
	}

	// ****************************************************************************************
	//
	@RequestMapping(name = "/savePublisher", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public Publisher savePublisher(@RequestBody Publisher publisher) {
		return adminService.savePublisher(publisher);
	}
}
