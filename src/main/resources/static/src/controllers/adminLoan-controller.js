lmsApp.controller("adminLoanController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {

	$scope.loan
	$scope.resultMessage;
	// **********************************************************************
	if ($location.path() === '/loanpage') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.READ_ALL_LOANS)
				.then(
						function(result) {
							$scope.loans = result;
							$scope.pagination = Pagination.getNew(30);
							$scope.pagination.numPages = Math
							.ceil($scope.loans.length
									/ $scope.pagination.perPage);
						})
	}
	// **********************************************************************
	$scope.editLoan = function(loan) {
		$scope.loan=loan;
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.EDIT_LOAN
				+"?cardNo=" +$scope.loan.borrower.cardNo
				+"&branchId=" +$scope.loan.library.branchId
				+"&bookId=" +$scope.loan.book.bookId
				+"&dueDate=" +$scope.loan.dueDate).then(
						function(response) {
							$scope.resultMessage = response.data;
							$window.location.href = "#/loanpage";
						})
						$window.location.href ="#/authorpage";
	}
})
