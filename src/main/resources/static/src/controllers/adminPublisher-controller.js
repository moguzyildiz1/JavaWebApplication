lmsApp.controller("adminPublisherController", function($scope, $http, lmsService,
		lmsConstants, $window, $location, Pagination) {
	
	$scope.publisher;
	$scope.addedPublisherName;
	$scope.addedPublisherAddress;
	$scope.addedPublisherPhone;
	$scope.passPublisher;
	$scope.resultMessage;
	// **********************************************************************
	if ($location.path() === '/publisherpage') {
		lmsService.getAll(
				lmsConstants.LMS_HOST + lmsConstants.READ_PUBLISHERS+"?name")
				.then(
						function(response) {
							//console.log(response);
							$scope.publishers = response;
							$scope.pagination = Pagination.getNew(20);
							$scope.pagination.numPages = Math
									.ceil($scope.publishers.length
											/ $scope.pagination.perPage);
						})
	}
	// **********************************************************************
	//
	$scope.editPublisher = function() {	
		
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.EDIT_PUBLISHER
				+"?name="+$scope.passPublisher.pubName
				+"&id="+$scope.passPublisher.publisherId)
				.then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/publisherpage";
				})
				$window.location.href = "#/publisherpage";
	}
	// *****************************************************************
	//
	$scope.savePublisher = function() {
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.SAVE_PUBLISHER
				+"?name="+$scope.addedPublisherName
				+"&address="+$scope.addedPublisherAddress
				+"&phone="+$scope.addedPublisherPhone).then(function(response) {
					$scope.resultMessage = response.data;
					$window.location.href = "#/publisherpage";
				})
				$window.location.href = "#/publisherpage";
	}
	// *****************************************************************
	//
	$scope.passToPublisher = function(pPublisher) {
		console.log("pPublisher: "+pPublisher.pubName);
		$scope.passPublisher=pPublisher;
		console.log("passPublisher: "+$scope.passPublisher.pubName);
	}
	// *****************************************************************
	//
	$scope.deletePublisher = function(publisher) {
		lmsService.getAll(lmsConstants.LMS_HOST + lmsConstants.DELETE_PUBLISHER
				+"?id="+publisher.publisherId).then(function(response) {
					$scope.resultMessage=response.data;		
					$window.location.href = "#/publisherpage";
				})
				$window.location.href = "#/publisherpage";
	}
})