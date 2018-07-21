angular.module('modiTradersApp')
.service('productService', ['$http', '$window', function($http, $window) {

    this.getProduct = function(productId) {
        var promise = $http
            .get('/services/getProduct?productId='+productId);

        promise = promise.then(
            function(response) {
                this.product = response.data;
                console.log('Product received from server',this.product);
                return this.product;
            },
            function(errResponse) {
                console.log('Some error while fetching the product from server');
            });
        return promise;
    };
	
}]);