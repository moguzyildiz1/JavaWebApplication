lmsApp.controller("adminBorrowerController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {

	$scope.borrower;
	$scope.addedBorrowerName;
	$scope.addedBorrowerAddress;
	$scope.addedBorrowerPhone;
	$scope.passBorrower;
	$scope.resultMessage;
	// **********************************************************************
	if ($location.path() === '/borrowerpage') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.READ_BORROWERS+"?name")
				.then(
						function(response) {
							//console.log(response);
							$scope.borrowers = response;
							$scope.pagination = Pagination.getNew(20);
							$scope.pagination.numPages = Math
							.ceil($scope.borrowers.length
									/ $scope.pagination.perPage);
						})
	}
	// **********************************************************************
	//
	$scope.editBorrower = function() {	

		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.EDIT_BORROWER
				+"?name="+$scope.passBorrower.borrowerName
				+"&cardNo="+$scope.passBorrower.cardNo)
				.then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/borrowerpage";
				})
				$window.location.href = "#/borrowerpage";
	}
	// *****************************************************************
	//
	$scope.saveBorrower = function() {
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.SAVE_BORROWER
				+"?name="+$scope.addedBorrowerName
				+"&address="+$scope.addedBorrowerAddress
				+"&phone="+$scope.addedBorrowerPhone).then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/borrowerpage";
				})
				$window.location.href = "#/borrowerpage";
	}
	// *****************************************************************
	//
	$scope.passToBorrower = function(pBorrower) {
		console.log("pBorrower: "+pBorrower.borrowerName);
		$scope.passBorrower=pBorrower;
		console.log("passBorrower: "+$scope.passBorrower.borrowerName);
	}
	// *****************************************************************
	//
	$scope.deleteBorrower = function(borrower) {
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.DELETE_BORROWER
				+"?cardNo="+borrower.cardNo).then(function(response) {
					$scope.resultMessage=response.data;		
					$window.location.href ="#/borrowerpage";
				})
				$window.location.href ="#/borrowerpage";
	}
})