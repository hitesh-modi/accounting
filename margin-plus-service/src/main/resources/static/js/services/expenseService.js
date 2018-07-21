angular.module('modiTradersApp')
.service('expenseService', ['$http', function($http, $window) {

    this.getExpenseTypes = function() {
        var promise = $http
            .get('/services/getExpenseTypes');
        return promise;
    };

    this.saveExpense = function(expense) {
        var promise = $http
            .post('/services/saveExpense', expense);
        return promise;
    };
	
}]);