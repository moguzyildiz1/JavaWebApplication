lmsApp.factory("lmsService", function($http){
	return{
		getAll: function(url){
			var listObjs = {};
			return $http.get(url).success(function(data){
				listObjs = data;
			}).then(function(){
				return listObjs;
			})
		},
		postObj: function(url, obj){
			var returnObj = {};
			return $http.post(url, obj).success(function(data){
				returnObj = data;
			}).then(function(){
				return returnObj;
			})
		},
		initAuthor: function(url){
			var author = {};
			return $http.get(url).success(function(data){
				author = data;
			}).then(function(){
				return author;
			})
		}
	}
})