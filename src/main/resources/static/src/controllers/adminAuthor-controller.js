lmsApp.controller("authorController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {

	$scope.addedAuthorName;
	$scope.addedAuthor;
	$scope.author;
	$scope.searchAuthor;
	//**********************************************************************
	if ($location.path() === '/authorpage') {
		lmsService
		.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS)
		.then(function(result) {
			$scope.authors = result;
			$scope.pagination = Pagination.getNew(30);
			$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
		})
		lmsService.initAuthor(lmsConstants.LMS_HOST + lmsConstants.INIT_AUTHOR)
		.then(function(result) {
			console.log(result);
			$scope.author = result;
			$scope.addedAuthor=result;
		});		
	} else {

		lmsService.initAuthor(lmsConstants.LMS_HOST + lmsConstants.INIT_AUTHOR)
				.then(function(result) {
					$scope.author = result;
				});
		lmsService
		.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_BOOKS)
		.then(function(result) {
			$scope.books = result;
			$scope.totalBooks = $scope.books.length;
		})
	}
	//**********************************************************************
	$scope.deleteAuthor = function(authorId) {
		var delAuthor = {
			authorId : authorId
		}
		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.SAVE_AUTHOR,
				delAuthor).then(
				function() {
					lmsService.getAll(
							lmsConstants.LMS_HOST
									+ lmsConstants.READ_ALL_AUTHORS).then(
							function(data) {
								$scope.authors = data;
								$scope.pagination = Pagination.getNew(10);
								$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
							})
				})
	}
	//**********************************************************************
	$scope.saveAuthor = function() {
		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.SAVE_AUTHOR,
				$scope.author).then(function() {
					$window.location.href ="#/authorpage";
				})
				$window.location.href ="#/authorpage";
	}
	//**********************************************************************
	$scope.nameToAuthor = function() {

		$scope.addedAuthor.authorName=$scope.addedAuthorName;
		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.SAVE_AUTHOR,
				$scope.addedAuthor).then(function() {
					$window.location.href ="#/authorpage";
				})
				$window.location.href ="#/authorpage";
	}
	//**********************************************************************
	$scope.editAuthorModal = function(author){		
		$scope.author = author;
		console.log($scope.author);
	}	
	//**********************************************************************
	$scope.searchAuthors = function(){
		lmsService
		.getAll(lmsConstants.LMS_HOST + lmsConstants.READ_ALL_AUTHORS+$scope.searchAuthor)
		.then(function(result) {
			$scope.authors = result;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
			$window.location.href ="#/authorpage";
		})
	}

})