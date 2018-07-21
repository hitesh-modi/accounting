angular.module('modiTradersApp')
	.controller('InvoiceReportController', ['$http', '$scope', 'ngDialog','invoiceService', '$window', 
	function($http, $scope, ngDialog,invoiceService, $window) {
		var self = this;
		self.invoices;

		$scope.getInvoiceData = function(defaultReport, fromDate, toDate) {
			if(defaultReport == true) {
				var toDate = new Date();
				var dateOneMonthBack = toDate.getMonth()-1;
				var fromDate = new Date();
				fromDate.setMonth(dateOneMonthBack);
			}
			var receivePaymentDialog = {};
            var creditNoteDialog = {};
			console.log('From Date: ', fromDate);
			console.log('To Date: ', toDate);
			console.log('Getting invoice data');
			$http.post('/services/getInvoiceReport', {fromDate: fromDate, toDate: toDate})
	   	  	 .then(
		  				function(response){
	 					self.invoices = response.data;
	 					console.log('data received from server', response.data);
	 				}, function(errResponse){
	 					console.log('Some error while fetching the data from server');
	 				}
	 		);
		};
		
		$scope.getInvoice = function(invoiceId) {
			invoiceService.getInvoice(invoiceId).then(
					function(data) {
						console.log('Data got from service',data);
						$scope.invoice = data;
						ngDialog.open({
							template: 'invoice.html',
							scope: $scope,
							controller: 'InvoiceReportController',
							data: {invoice: $scope.invoice}
						});
					}
			);
				
			console.log('invoice from service: ', $scope.invoice);
		};

        $scope.sort = function(keyName) {
            $scope.sortKey = keyName;
            $scope.reverse = !$scope.reverse;
        };

        $scope.checkBalanceAmount = function(amount) {
            var amount = parseFloat(amount);
            if(amount == 0)
                return true;
        };
		
		$scope.printInvoice = function(invoiceId) {
			invoiceService.printInvoice(invoiceId);
		};
		
		$scope.receivePaymentDialog = function(invoice) {
			receivePaymentDialog = ngDialog.open({
				template: 'receivePaymentForm.html',
				scope: $scope,
				controller: 'InvoiceReportController',
				data: {invoice: invoice}
			});
		};
		
		$scope.receivePayment = function(invoiceId, amountReceived) {
			console.log('Receiving payment for ', invoiceId, amountReceived);
			$http.post('/services/receivePayment', {invoiceId: invoiceId, amountReceived: amountReceived})
	   	  	 .then(
		  				function(response){
	 					console.log('Amount Received Successfully');
	 					$window.location.reload();	 					
	 				}, function(errResponse){
	 					console.log('Some error while fetching the data from server');
	 				}
	 		);
		};

		$scope.openCreditNote = function(invoiceId) {

            invoiceService.getInvoice(invoiceId).then(
                function(data) {
                    console.log('Data got from service',data);
                    $scope.invoice = data;
                    $scope.invoice.invoiceDate = new Date(data.invoiceDate);
                    $scope.invoice.invoiceDueDate = new Date(data.invoiceDueDate);
                    ngDialog.open({
                        template: 'creditNoteForm.html',
                        scope: $scope,
                        controller: 'CreditNoteController',
                        data: {invoice: $scope.invoice}
                    });
                }
            );

        };
		
	}
]);