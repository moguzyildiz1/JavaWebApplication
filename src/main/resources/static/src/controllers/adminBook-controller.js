lmsApp.controller("bookController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination, bookDetails) {

	$scope.bookAuthors=[];
	$scope.bookGenres=[];
	$scope.passPublisher;
	$scope.passAuthor;
	$scope.passGenre;
	$scope.addedPublisherName;
	$scope.addedPublisherAddress;
	$scope.addedPublisherPhone;
	$scope.addedAuthorName;
	$scope.addedGenreName;
	$scope.addedBookName;
	
	$scope.searchTitle;
	$scope.addedBook={};
	$scope.passBook;
	
	//**********************************************************************
	if ($location.path() === '/bookpage') {
		lmsService
		.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_BOOKS)
		.then(function(result) {
			//console.log(result);
			$scope.books = result;
			$scope.pagination = Pagination.getNew(40);
			$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
		})		
		$scope.addedBook.bookId=null;
		$scope.addedBook.bookName=null;
		$scope.addedBook.publisher=null;
		$scope.addedBook.authors=null;
		$scope.addedBook.genres=null;
		$window.localStorage.setItem("addedBook",angular.toJson($scope.addedBook));
	}
	// ***************************************************************************
	// Read and show directly publisher list to add book publisher
	if ($location.path() === '/addbookpublisher') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.READ_PUBLISHERS+"?name")
				.then(
						function(response) {
							//console.log(response);
							$scope.publishers = response;
							$scope.pagination2 = Pagination.getNew(20);
							$scope.pagination2.numPages = Math
							.ceil($scope.publishers.length/$scope.pagination2.perPage);
						})
						lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.INIT_PUBLISHER)
						.then(function(response) {
							$scope.passPublisher=response;	
						})
	}
	// ***************************************************************************
	// 
	if ($location.path() === '/addbookauthor') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS)
				.then(
						function(response) {
							//console.log(response);
							$scope.authors = response;
							$scope.pagination3 = Pagination.getNew(30);
							$scope.pagination3.numPages = Math
									.ceil($scope.authors.length/$scope.pagination3.perPage);
						})
						lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.INIT_AUTHOR)
						.then(function(response) {
							$scope.passAuthor=response;
							//$scope.passAuthor.authorId=null;
							//console.log("passAuthor: "+$scope.passAuthor.authorName+" "+$scope.passAuthor.authorId);
						})
	}
	// ***************************************************************************
	// 
	if ($location.path() === '/addbookgenre') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.READ_GENRES+"?name")
				.then(
						function(response) {
							//console.log(response);
							$scope.genres = response;
							$scope.pagination4 = Pagination.getNew(20);
							$scope.pagination4.numPages = Math
									.ceil($scope.genres.length/$scope.pagination4.perPage);
						})
						lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.INIT_GENRE)
						.then(function(response) {
							$scope.passGenre=response;	
						})
	}
	// *****************************************************************
	//
	$scope.deleteBook = function(book) {
		console.log(book.title);
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.DELETE_BOOK
				+"?id="+book.bookId).then(function(response) {
					$scope.resultMessage=response.data;		
					$window.location.href = "#/bookpage";
				})
				$window.location.href = "#/bookpage";
	}
	// **********************************************************************
	//
	$scope.editBook = function() {			
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.EDIT_BOOK
				+"?title="+$scope.passBook.title
				+"&id="+$scope.passBook.bookId)
				.then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/bookpage";
				})
				$window.location.href = "#/bookpage";
	}
	// *****************************************************************
	//
//	$scope.saveBook = function() {
//		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.SAVE_BOOK
//				+"?name="+$scope.addedGenreName).then(function(response) {
//					$scope.resultMessage = response.data;
//					$window.location = "#/bookpage";
//				})
//	}
	//**********************************************************************
	//
	$scope.passToBook = function(pBook) {
		//console.log("pBook: "+pBook.title+" "+pBook.bookId);
		$scope.passBook=pBook;
		console.log("passBook: "+$scope.passBook.bookId);
	}
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	// *****************************************************************************
	// Add and set publisher of new book from addbookpublisher.html input form
	$scope.passToPublisher = function(pPublisher) {
		$scope.passPublisher=pPublisher;
		bookDetails.setPublisher($scope.passPublisher);
		//console.log("addedBook.publisher: "+$scope.addedBook.publisher.pubName);
	}
	// *****************************************************************************
	// Add and set publisher of new book from addbookpublisher.html input form
	$scope.addPassPublisher = function() {
		$scope.passPublisher.pubName=$scope.addedPublisherName;
		$scope.passPublisher.pubAddress=$scope.addedPublisherAddress;
		$scope.passPublisher.pubPhone=$scope.addedPublisherPhone;
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.SAVE_PUBLISHER_ID
				+"?name="+$scope.addedPublisherName
				+"&address="+$scope.addedPublisherAddress
				+"&phone="+$scope.addedPublisherPhone).then(function(response) {
					$scope.passPublisher.publisherId = response;					
					bookDetails.setPublisher($scope.passPublisher);
					console.log("settedpublisherId: "+bookDetails.getPublisher().publisherId );
				})		
	}
	// *****************************************************************************
	// 
	$scope.setNewAuthor = function() {
		$scope.passAuthor.authorName=$scope.addedAuthorName;
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.SAVE_AUTHOR_ID
				+"?name="+$scope.addedAuthorName).then(function(response) {
					$scope.passAuthor.authorId = response;
					console.log("passAuthorId: "+$scope.passAuthor.authorId);
				})		
	}
	// *****************************************************************************
	// Add and set publisher of new book from addbookpublisher.html input form
	$scope.addToAuthorsList = function(pAuthor) {
		console.log(pAuthor.authorName);
		$scope.bookAuthors.push(pAuthor);
	}
	// *****************************************************************************
	// Add and set publisher of new book from addbookpublisher.html input form
	$scope.setAuthorsList = function() {
		
		if($scope.bookAuthors.indexOf($scope.passauthor) === -1 && $scope.passAuthor.authorName!=null ) {			
			$scope.bookAuthors.push($scope.passAuthor);
		}
		$scope.addedBook.authors=$scope.bookAuthors;
	}
	// *****************************************************************************
	// 
	$scope.setNewGenre = function() {
		$scope.passGenre.genreName=$scope.addedGenreName;
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.SAVE_GENRE_ID
				+"?name="+$scope.addedGenreName).then(function(response) {
					$scope.passGenre.genreId = response;
					console.log("passGenreId: "+$scope.passGenre.genreId);
					$scope.addToGenresList($scope.passGenre);
				})		
	}
	// *****************************************************************************
	// Add and set publisher of new book from addbookpublisher.html input form
	$scope.addToGenresList = function(pGenre) {
		$scope.passGenre=pGenre;
		console.log("passGenre.genreID: "+$scope.passGenre.genreId);
		$scope.bookGenres.push($scope.passGenre);
	}
	// *****************************************************************************
	// 
	$scope.setGenresList = function() {
		
		if($scope.bookGenres.indexOf($scope.passGenre) === -1 && $scope.passGenre.genreName!=null) {
			$scope.bookGenres.push($scope.passGenre);
		}		
		//$window.sessionStorage.setItem('bg',$scope.bookGenres);
		bookDetails.setGenres($scope.bookGenres);
		console.log("settedGenres: "+bookDetails.getGenres()[0].genreName);
	}
	// *****************************************************************
	//
	$scope.saveBook = function() {
		
		$scope.addedBook.publisher=bookDetails.getPublisher();
		$scope.addedBook.genres=bookDetails.getGenres();
		$scope.addedBook.title=$scope.addedBookName;
		//$scope.addedBook.publisher= $scope.passPublisher;
		//$scope.addedBook.authors= $scope.bookAuthors;
		console.log("addedBook.name: "+$scope.addedBook.title);
		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.SAVE_BOOK,
				$scope.addedBook).then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/bookpage";
				})
				$window.location.href = "#/bookpage";
	}
})