lmsApp.factory("bookDetails", function() {
	
	var publisher = {};
	var genres={};
	
	function setPublisher(pub) {
		publisher = pub;
	}
	
	function getPublisher() {
		return publisher;
	}
	
	function setGenres(gen) {
		genres=gen;
	}
	
	function getGenres() {
		return genres;
	}

	return {
		setPublisher : setPublisher,
		getPublisher : getPublisher,
		setGenres : setGenres,
		getGenres : getGenres
	}

});
//lmsApp.factory("bookDeatils", function() {
//	var publisher = {};
//	var genres={};
//	return{
//		setPublisher: function(pub){
//			publisher=pub;
//		},
//		getPublisher: function(){
//			return publisher;
//		},
//		setGenres: function(gen){
//			genres=gen;
//		},
//		getGenres: function(){
//			return genres;
//		}
//	}
//})