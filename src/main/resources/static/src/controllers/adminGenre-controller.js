lmsApp.controller("adminGenreController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {
	
	$scope.genre;
	$scope.addedGenreName;
	$scope.passGenre;
	$scope.resultMessage;
	// **********************************************************************
	if ($location.path() === '/genrepage') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.READ_GENRES+"?name")
				.then(
						function(response) {
							//console.log(response);
							$scope.genres = response;
							$scope.pagination = Pagination.getNew(10);
							$scope.pagination.numPages = Math
									.ceil($scope.genres.length
											/ $scope.pagination.perPage);
						})
	}
	// **********************************************************************
	//
	$scope.editGenre = function() {	
		
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.EDIT_GENRE
				+"?name="+$scope.passGenre.genreName
				+"&id="+$scope.passGenre.genreId)
				.then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/genrepage";
				})
				$window.location.href = "#/genrepage";
	}
	// *****************************************************************
	//
	$scope.saveGenre = function() {
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.SAVE_GENRE
				+"?name="+$scope.addedGenreName).then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/genrepage";
				})
				$window.location.href = "#/genrepage";
	}
	// *****************************************************************
	//
	$scope.passToGenre = function(pGenre) {
		console.log("pGenre: "+pGenre.genreName);
		$scope.passGenre=pGenre;
		console.log("passGenre: "+$scope.passGenre.genreName);
	}
	// *****************************************************************
	//
	$scope.deleteGenre = function(genre) {
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.DELETE_GENRE
				+"?id="+genre.genreId).then(function(response) {
					$scope.resultMessage=response.data;		
					$window.location.href = "#/genrepage";
				})
				$window.location.href = "#/genrepage";
	}
})