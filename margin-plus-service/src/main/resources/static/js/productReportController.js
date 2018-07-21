angular.module('modiTradersApp')
	.controller('ProductReportController', ['$http', '$scope', 'productService', 'ngDialog',
	                                  function($http, $scope, productService, ngDialog) {
	                                	  var self = this;
	                                	  
	                                	  $scope.sort = function(keyName) {
	                                		  console.log('sorting based on ', keyName);
	                                		  $scope.sortKey = keyName;
	                                		  $scope.reverse = !$scope.reverse;
	                                	  };
	                                	  
	                                	  $http.get('/services/getProducts')
	                                	  .then(
                          		  				function(response){
                        		  					self.products = response.data;
                        		  					console.log('data received from server', response.data);
                        		  				}, function(errResponse){
                        		  					console.log('Some error while fetching the data from server');
                        		  				}
                        		  		);

                                          $scope.editProduct = function(productId) {
                                              productService.getProduct(productId).then(
                                                  function(data) {
                                                      console.log('Data got from service',data);
                                                      $scope.product = data;
                                                      ngDialog.open({
                                                          template: 'productForm.html',
                                                          scope: $scope,
                                                          controller: 'ProductController',
                                                          data: {product: $scope.product}
                                                      });
                                                  }
                                              );

                                              console.log('Product from service: ', $scope.product);
                                          };

	                                  }
]);