package com.gcit.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Inventory;
import com.gcit.lms.entity.Library;
import com.gcit.lms.service.LibrarianService;

@RestController
@RequestMapping("/librarian/*")
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	// ***************************************************************************************
	//
	@RequestMapping("/readBranches")
	public List<Library> readAllLibraries(@RequestParam("name") String name) {
		return librarianService.readLibraries(name);
	}

	// ***************************************************************************************
	//
	@RequestMapping("/readBranchById")
	public Library readLibraryById(@RequestParam("id") Integer id) {
		return librarianService.readLibraryById(id);
	}

	// ***************************************************************************************
	//
	@RequestMapping("/editBranchParams")
	public String editLibrary(@RequestParam("id") Integer id, @RequestParam("bName") String bName,
			@RequestParam("bAddress") String bAddress) {
		return librarianService.editLibraryParams(id, bName, bAddress);
	}

	// ***************************************************************************************
	//
	@RequestMapping("/editInventory")
	public ResponseEntity<String> editInventory(@RequestParam("branchId") Integer branchId,
			@RequestParam("bookId") Integer bookId, @RequestParam("noCopy") Integer noCopy) {
		return librarianService.editInventory(branchId, bookId, noCopy);
	}

	// ***************************************************************************************
	//
	@RequestMapping("/readInventoryById")
	public List<Inventory> showInventory(@RequestParam("branchId") Integer branchId) {
		return librarianService.readInventoryById(branchId);
	}

	// ***************************************************************************************
	//
	@RequestMapping(name = "/editBranch", method = RequestMethod.POST)
	public String editLibrary(@RequestBody Library Library) {
		return librarianService.editLibrary(Library);
	}
}
