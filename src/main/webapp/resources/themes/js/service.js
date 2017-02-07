/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){
        var baseUrl = "http://localhost:8080/artifact/";
        return{
            login:function(){
                return $http.get(baseUrl)
                        .then(
                            function(response){
                                return response.data;
                            },
                            function(errResponse){
                                console.error('Error while fetching users');
                                return $q.reject(errResponse);
                            }
                        );
            }
        };
}]);

