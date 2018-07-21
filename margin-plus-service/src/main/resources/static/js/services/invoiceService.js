angular.module('modiTradersApp')
.service('invoiceService', ['$http', '$window', function($http, $window) {

	this.invoice = {};
	
	this.getInvoice = function(invoiceId) {
		var promise = $http
		.get('/services/getInvoice?invoiceId='+invoiceId);
		
		promise = promise.then(
				function(response) {
					this.invoice = response.data;
					console.log('Invoice received from server',this.invoice);
					return this.invoice;
				},
				function(errResponse) {
					console.log('Some error while fetching the invoice from server');
				});
		return promise;
	};
	
	this.printInvoice = function(invoiceId) {
		console.log('Printing invoice with id: ', invoiceId);
		$http
				.post('/services/printInvoice',
						{invoiceId: invoiceId},
						{responseType: 'arraybuffer'})
				.then(
						function(response) {
							
							 var a = document.createElement("a");
					         document.body.appendChild(a);
					         a.style = "display: none";
					         a.target = "_blank";
							
							 var file = new Blob([response.data], {type: 'application/pdf'});
						     var fileURL = $window.URL.createObjectURL(file);
						     a.href = fileURL;
						    // a.download = "invoice.pdf";
						     a.click();
						     $window.location.reload();
						},
						function(errResponse) {
							console
									.log('Some error while creating invoice');
						});
	};
	
}]);