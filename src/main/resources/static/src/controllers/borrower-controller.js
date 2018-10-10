lmsApp.controller("borrowerController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {

	$scope.borrower;
	$scope.checkBranch;
	$scope.checkBook;
	$scope.resultMessage;
	$scope.loanBookMessage;
	// **********************************************************************
	if ($location.path() === '/checkout') {
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
	// **********************************************************************
	if ($location.path() === '/checkin') {
		lmsService.getAll(
						lmsConstants.LMS_HOST + lmsConstants.READ_LOANS
								+ "?cardNo=" + $window.localStorage.getItem("sCardNo")).then(
						function(response) {
							console.log(response);
							$scope.loans = response;
							$scope.pagination2 = Pagination.getNew(5);
							$scope.pagination2.numPages = Math.ceil($scope.loans.length
									/ $scope.pagination2.perPage);
						})
	}

	// *****************************************************************
	// Check by borrower id to allow further more operation
	$scope.checkBorrower = function(borrowerCardNo) {

		$http.get(
				lmsConstants.LMS_HOST + "/admin/borrower/readBorrowerByNo"
						+ "?cardNo=" + $scope.borrowerCardNo).then(
				function(response) {
					var result = response;
					console.log(result);
					if (result.data != "") {
						$scope.borrower = result.data;
						$window.localStorage.setItem("sCardNo", result.data.cardNo);
						$window.location = "#/borrower";
					} else
						console.log("lan noluyo yaa");
				})
	}
	// **********************************************************************
	$scope.availableBooks = function(branch) {		
		$scope.checkBranch = branch;
		$window.localStorage.setItem("sBranchId", branch.branchId);
		$http.get(
				lmsConstants.LMS_HOST + lmsConstants.READ_INVENTORY
						+ "?branchId=" + branch.branchId).then(
				function(response) {
					console.log(response);
					$scope.books = response.data;
					$scope.pagination1 = Pagination.getNew(10);
					$scope.pagination1.numPages = Math.ceil($scope.books.length
							/ $scope.pagination1.perPage);
				})
	}
	// **********************************************************************
//	$scope.loanBooks = function() {		
//		$http.get(
//				lmsConstants.LMS_HOST + lmsConstants.READ_LOANS
//						+ "?cardNo=" + $window.localStorage.getItem("sCardNo")).then(
//				function(response) {
//					console.log(response);
//					$scope.loans = response.data;
//					$scope.pagination1 = Pagination.getNew(5);
//					$scope.pagination1.numPages = Math.ceil($scope.loans.length
//							/ $scope.pagination1.perPage);
//				})
//	}
	// **********************************************************************
	$scope.checkOut = function(book) {
		$scope.checkBook = book;
		$window.localStorage.setItem("sBookId", book.bookId);
		$http.get(
				lmsConstants.LMS_HOST + lmsConstants.CHECK_OUT
								+ "?cardNo="
								+ $window.localStorage.getItem("sCardNo")+"&branchId="
								+ $window.localStorage.getItem("sBranchId")+ "&bookId="
								+ $window.localStorage.getItem("sBookId"))
				.then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/borrower";
				})
				$window.location.href = "#/borrower";
	}
	// **********************************************************************
	$scope.checkIn = function(loan) {	
		$http.get(
				lmsConstants.LMS_HOST + lmsConstants.CHECK_IN
								+ "?cardNo="+loan.borrower.cardNo
								+ "&branchId="+loan.library.branchId
								+ "&bookId="+loan.book.bookId)
				.then(function(response) {
					$scope.resultMessage = JSON.parse(response.data).value;
					$window.location.href = "#/checkin";
				})
				$window.location.href = "#/borrower";
	}
	// **********************************************************************
})