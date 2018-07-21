/**
 * 
 */
angular.module('loginApp', ['ngRoute', 'ngDialog'])
.config(
			['$routeProvider',
			function($routeProvider) {
				$routeProvider.when('/dashboard', {
					templateUrl: 'home.html'
				})
				.otherwise({redirectTo: '/home'});
				
			}
		]
	)
	.controller('UserController', function($http, $window, $scope, ngDialog) {
		
		var self = this;
		self.credentials;
		self.stateList;
		self.showRegistrationResponse;
		self.registrationResponse;
		$scope.arePasswordsSame = true;
		
		var registrationDialog;
		
		$scope.openRegistrationForm = function() {
			console.log('Opening registration form.');
			registrationDialog = ngDialog.
									openConfirm({ template: 'registrationModal.html', scope: $scope})
									.then(
											function(confirm) {
												console.log('Register Called', self.user);
												
												var password = self.user.password;
												var encodedPassword = "Basic "
													+ btoa(self.user.password)
												self.user.password = encodedPassword;
												
												$http.post('/registerUser', self.user).
												then(
														function(successResponse) {
															console.log('Success', successResponse.data);
															if(successResponse.data.status == 'user_email_exists') {
																console.log('User with same email id exists.');
																self.registrationSuccess = false;
																self.showRegistrationResponse = true;
																self.registrationResponse = 'User Already Exists with the Email Id';
																ngDialog.open({ template : 'registrationMessage.html', 
																				scope: $scope,
																				controller: 'UserController'});
																}
																
															if(successResponse.data.status == 'user_mobile_exists') {
																console.log('User with same mobile number exists.');
																self.registrationSuccess = false;
																self.showRegistrationResponse = true;
																self.registrationResponse = 'User Already Exists with the Contact Number';
																ngDialog.open({ template : 'registrationMessage.html', 
																	scope: $scope,
																	controller: 'UserController'});
															}
															if(successResponse.data.status == 'success') {
																console.log('User registered successfully');
																self.registrationSuccess = true;
																self.showRegistrationResponse = true;
																self.registrationResponse = 'User registered successfully, email notification sent.';
																self.user = {};
																ngDialog.open({ template : 'registrationMessage.html', 
																	scope: $scope,
																	controller: 'UserController'});
															}
														},
														function(failureResponse) {
															console.log('Error', failureResponse)
														}
												);
												
											
											},
											function(reject) {
												
											}
									);
		};
		
		$scope.openLoginDialog = function() {
			ngDialog.open({
				template: 'loginModal.html',
				scope: $scope,
				controller: 'UserController'
		
			});
		};
		
		
		console.log('Getting state list');
		$http.get('/getStates')
			 .then(
					function(successResponse){
						console.log('Got states from server', successResponse);
						self.stateList = successResponse.data;
					},
					function(failureResponse){
						console.log('Request for fetching states failed', successResponse);
					}
			);
		
		$scope.checkPasswords = function(obj1, obj2) {
			
			console.log("Compare",obj1, obj2);
			if(obj1 == obj2)
				$scope.arePasswordsSame = true;
			else 
				$scope.arePasswordsSame = false;
		}
		
		self.login = function() {
			console.log('User Login');
			var headers = self.credentials ? {
				authorization : "Basic "
						+ btoa(self.credentials.username + ":"
								+ self.credentials.password)
			} : {};
			console.log('Header', headers);
			$http.post('/login', headers).
			then(
					function(successResponse) {
						console.log('Login Success Response', successResponse);
						if(successResponse.data.status == 'success') {
							$window.location.href = '/greeting';
						}
						if(successResponse.data.status == 'failure') {
							self.loginFailedMessages='Login failed. Please check email id or password.';
							ngDialog.open({
								template: 'loginFailure.html',
								controller: 'UserController',
								scope: $scope
							});
						}
					},
					function(failedResponse) {
						console.log('Login Failure Response', failedResponse);
					}
			);
			self.credentials={};
		};
		
		getStates = function() {
			console.log('Getting state list');
			$http.get(
					'/getStates'
			)
			.then(
					function(successResponse){
						console.log('Got states from server', successResponse);
					},
					function(failureResponse){
						console.log('Request for fetching states failed', successResponse);
					}
			);
		};
		
	});
