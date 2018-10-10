lmsApp.controller("librarianController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {
	
	$scope.branch;
	$scope.inventories;
	$scope.resultMessage;
	$scope.passInventory;
	// **********************************************************************
	if ($location.path() === '/librarian') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.SHOW_LIBRARIES + "?name")
				.then(
						function(result) {
							$scope.branches = result;
							$scope.pagination = Pagination.getNew(5);
							$scope.pagination.numPages = Math
									.ceil($scope.branches.length
											/ $scope.pagination.perPage);
						})
	}
	// *****************************************************************
	//
	$scope.readInventoryById = function(branch) {
		$scope.branch=branch;
		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.READ_INVENTORIES
				+"?branchId="+branch.branchId).then(function(response) {
					//console.log(response);
					$scope.inventories = response;
					//console.log("length of result: "+$scope.inventories.length);
					$scope.pagination1 = Pagination.getNew(5);
					$scope.pagination1.numPages = Math
					.ceil($scope.inventories.length
							/ $scope.pagination1.perPage);
				})
	}
	// *****************************************************************
	//
	$scope.editInventory = function(edittedInventory) {
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.EDIT_INVENTORY
				+"?branchId="+edittedInventory.library.branchId
				+"&bookId="+edittedInventory.book.bookId
				+"&noCopy="+edittedInventory.copies).then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/librarian";
				})
				$window.location.href = "#/librarian";
	}
	// *****************************************************************
	//
	$scope.passInventory = function(passInventory) {		
		$scope.passInventory=passInventory;
	}
	// **********************************************************************
	//
	$scope.editLibraryDetails = function(branch) {
		$scope.branch = branch;
	}
	//**********************************************************************
	$scope.editLibrary = function() {
		console.log($scope.branch);
		lmsService.postObj(lmsConstants.LMS_HOST + lmsConstants.EDIT_BRANCH,
				$scope.branch).then(function() {
			//console.log($scope.branch);
					$window.location.href = "#/librarian";
		})
		$window.location.href = "#/librarian";
	}
})