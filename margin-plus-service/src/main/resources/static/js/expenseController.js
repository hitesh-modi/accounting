angular.module('modiTradersApp')
	.controller('ExpenseController', ['$http', '$scope','$window', 'expenseService', 'ngDialog',
        function($http, $scope, $window, expenseService, ngDialog) {
	        var self = this;
            self.expenseTypes;
            self.expense = {};
            self.savedExpenseId = 0;

            expenseService.getExpenseTypes().then(
                    function (successResponse) {
                        console.log('Data received from server', successResponse.data);
                        self.expenseTypes = successResponse.data;
                    },
                    function (errorResponse) {
                        console.log('Error getting expense types from server');
                    }
                );

            self.saveExpense = function () {
                console.log('Creating expense', self.expense)
                expenseService.saveExpense(self.expense).then(
                    function (successResponse) {
                        self.savedExpenseId = successResponse.data;
                        self.expense = {};
                    },
                    function (errorResponse) {
                        self.savedExpenseId = -1;
                        console.error('Error while creating expense');
                    }
                )
            };

	    }
 ]);