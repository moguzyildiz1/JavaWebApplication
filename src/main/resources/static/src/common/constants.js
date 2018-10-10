lmsApp.constant("lmsConstants", {	
	LMS_HOST: "http://localhost:8080/lms/",	
	INIT_PUBLISHER: "admin/publisher/initPublisher",
	INIT_GENRE: "admin/genre/initGenre",
	
	READ_ALL_AUTHORS:"admin/author/readAuthors?name=",
	SAVE_AUTHOR: "saveAuthor",
	SAVE_AUTHOR_ID: "admin/author/saveAuthorWithId",
	INIT_AUTHOR: "admin/author/initAuthor",		
	
	EDIT_BRANCH:"admin/library/editBranch",
	SHOW_LIBRARIES: "librarian/readBranches",
	INIT_LIBRARY: "librarian/initLibrary",
	READ_INVENTORIES:"librarian/readInventoryById",
	EDIT_INVENTORY:"librarian/editInventory",	
	
	READ_GENRES: "admin/genre/readGenres",
	SAVE_GENRE: "admin/genre/saveGenreByName",
	SAVE_GENRE_ID: "admin/genre/saveGenreWithId",
	EDIT_GENRE: "admin/genre/editGenre",
	DELETE_GENRE: "admin/genre/deleteGenre",
	
	READ_BOOKS: "admin/book/readBooks?title=",
	INIT_BOOK: "admin/book/initBook",
	EDIT_BOOK: "admin/book/editBook",
	DELETE_BOOK: "admin/book/deleteBook",
	SAVE_BOOK: "admin/book/saveBook",
	
	READ_PUBLISHERS: "admin/publisher/readPublishers",
	SAVE_PUBLISHER: "admin/publisher/savePublisherByParam",
	SAVE_PUBLISHER_ID: "admin/publisher/savePublisherWithId",
	EDIT_PUBLISHER: "admin/publisher/editPublisher",
	DELETE_PUBLISHER: "admin/publisher/deletePublisher",	
	
	READ_BORROWERS: "admin/borrower/readBorrowers",
	SAVE_BORROWER: "admin/borrower/saveBorrowerByParam",
	EDIT_BORROWER: "admin/borrower/editBorrower",
	DELETE_BORROWER: "admin/borrower/deleteBorrower",
	CHECK_OUT:"borrower/checkOut",
	CHECK_IN:"borrower/checkIn",
	BORROWER_CHECK: "borrower/checkById",
	READ_INVENTORY:"borrower/availableBooks",
	READ_LOANS:"borrower/readLoans",
	
	READ_ALL_LOANS: "admin/loan/readLoans",
	EDIT_LOAN: "admin/loan/editLoan",	
})