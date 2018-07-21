angular.module('modiTradersApp',['ngRoute', 'angularUtils.directives.dirPagination', 'ngDialog'])
	.config(
			['$routeProvider',
			function($routeProvider) {
				$routeProvider.when('/home', {
					templateUrl: 'home.html'
				})
				.when('/createProduct', {
					templateUrl: 'productForm.html' 
				})
				.when('/createExpense', {
					templateUrl: 'expenseForm.html'
				})
				.when('/serviceRecord', {
					templateUrl: 'serviceRecord.html'
				})
				.when('/inventoryRefill', {
					templateUrl: 'inventoryRefill.html'
				})
				.when('/contactUs', {
					templateUrl: 'contactUs.html'
				})
				.when('/createInvoice', {
					templateUrl: 'invoiceForm.html'
				})
				.when('/productReport', {
					templateUrl: 'productReport.html'
				})
				.when('/salesReport', {
					templateUrl: 'salesReport.html'
				})
				.when('/serviceReport', {
					templateUrl: 'serviceReport.html'
				})
				.otherwise({redirectTo: '/home'});
				
			}
		]
	)
	.controller(
			'UserController',
			[	'$http', '$rootScope',
				function($http, $rootScope) {
					var self = this;
					
					$http.get('/getUserName').then(
							function(successResponse){
								console.log('Success Response for get user name', successResponse);
								$rootScope.userDetails = successResponse.data;
							},
							function(failedResponse){
								console.log('Failure Response for get user name', failedResponse);
							}
					);
				}
			]
	);
	