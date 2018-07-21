angular
		.module('modiTradersApp')
		.controller(
				'CreditNoteController',
				[
						'$http',
						'$scope',
						'$rootScope',
						'$window',
						'ngDialog',
						'invoiceService',
						'commonService',
						function($http, $scope, $rootScope, $window, ngDialog, invoiceService, commonService) {
							var self = this;
							self.invoice = {};
							self.invoice.amountReceived = 0.0;
							self.invoice.invoiceItemDetails = [];
							self.invoice.grandTotal = 0.0;
							self.invoice.totalTax = 0.0;
							self.invoiceItemLock = false;

							self.invoiceItemUnderEdit;

							$scope.editMode = false;

							$scope.createdInvoiceId = "";

							$scope.tempInvoiceDetails = [ {
								serialNumber : 1
							} ];

							$scope.invoiceItemNumbers = 0;

							$scope.invoiceItemDetails = [];

							self.stateList;
							$scope.addtionalTaxes = [];
							self.additionalTaxType = [ 'Service Tax', 'VAT',
									'Excise Duty' ];

							self.tempAdditionalTax = [ {
								type : {
									taxType : 'Service Tax'
								}
							} ];

                            if(typeof $scope.ngDialogData != "undefined") {
                                self.invoice = $scope.ngDialogData.invoice;
                                $scope.invoiceItemDetails = self.invoice.invoiceItemDetails;
                            }

                            $scope.setEdit = function(invoiceItem) {

                                if(self.invoiceItemLock == false) {
                                    console.log('invoice under edit is null or undefined');
                                    self.invoiceItemUnderEdit = invoiceItem;
                                    invoiceItem.editMode = true;
                                    self.invoiceItemLock = true;
                                } else {
                                    console.log('invoice under edit is null or undefined');
                                    ngDialog.openConfirm({
                                        template: '<p>Please save existing invoice item which is open for editing</p>',
                                        plain: true
                                    });
                                }

                            };

                            $scope.saveEdit = function () {
                                self.invoiceItemUnderEdit.editMode = false;
                                //self.calculateTotal(self.invoiceItemUnderEdit);
                                self.invoiceItemLock = false;
                            };

                            self.calculateTotal = function (invoiceItem) {
                                var totalTaxableValue = 0;
                                var totalTaxes = 0;
                                for (var i = 0; i < self.invoice.invoiceItemDetails.length; i++) {
                                    tempInvoiceItem = self.invoice.invoiceItemDetails[i];
                                    totalTaxableValue = commonService.add(tempInvoiceItem.taxableValue, totalTaxableValue);
                                    totalTaxes = commonService.add(tempInvoiceItem.cgstAmount, totalTaxes);
                                    totalTaxes = commonService.add(tempInvoiceItem.sgstAmount, totalTaxes);
                                    totalTaxes = commonService.add(tempInvoiceItem.igstAmount, totalTaxes);
                                }

                                $scope.calculateTotal(invoiceItem);
                                $scope.calculateTaxableValue(invoiceItem);

                                self.invoice.grandTotal = totalTaxableValue;
                                self.invoice.totalTax = totalTaxes;
                                self.invoice.netTotal = commonService.add(totalTaxes, totalTaxableValue);
                            };

							self.submit = function() {
								console.log('Data posted ', self.invoice);
							};
							
							self.showSuccessMessage = function() {

							};

                            $scope.calculateTotal = function (invoiceItem) {
                                invoiceItem.total = commonService.multiply(invoiceItem.quantity, invoiceItem.rate);
                            };

                            $scope.calculateTaxableValue = function(invoiceItem) {
                                if(isNaN(parseFloat(invoiceItem.discount))) {
                                    invoiceItem.discount = 0;
                                }
                                invoiceItem.taxableValue = commonService.substract(invoiceItem.total, invoiceItem.discount);
                            };


                            /**
                             * This listener is for quantity, whenever quantity changes calculate the amount.
                             */
                            $scope.$watch('controller.invoiceItemUnderEdit.quantity', function (newValue, oldValue, scope) {

                                console.log('Quantity values changed', newValue, oldValue);

                                if(newValue == oldValue) {
                                    return;
                                }

                               self.invoiceItemUnderEdit.total = commonService.multiply(newValue, self.invoiceItemUnderEdit.rate);

                            }, true);


                            /**
                             * This listener is for Total (quantity x rate), whenever quantity changes calculate the amount.
                             */
                            $scope.$watch('controller.invoiceItemUnderEdit.total', function (newValue, oldValue, scope) {

                                console.log('Total values changed', newValue, oldValue);

                                if(newValue == oldValue) {
                                    return;
                                }

                                self.invoiceItemUnderEdit.taxableValue = commonService.substract(newValue, self.invoiceItemUnderEdit.discount);

                            }, true);

                            /**
                             * This listener is for Discount, whenever quantity changes calculate the amount.
                             */
                            $scope.$watch('controller.invoiceItemUnderEdit.discount', function (newValue, oldValue, scope) {

                                console.log('Discount values changed', newValue, oldValue);

                                if(newValue == oldValue) {
                                    return;
                                }

                                self.invoiceItemUnderEdit.taxableValue = commonService.substract(self.invoiceItemUnderEdit.total, newValue);

                            }, true);

                            /**
                             * This is listener for taxable value, whenever that changes it has to recalculate the taxes.
                             */
                            $scope.$watch('controller.invoiceItemUnderEdit.taxableValue', function (newValue, oldValue, scope) {

                                console.log('Taxable values changed', newValue, oldValue);

                                if(newValue == oldValue) {
                                    return;
                                }

                                self.invoiceItemUnderEdit.cgstAmount = commonService.calculatePercentage(newValue, self.invoiceItemUnderEdit.cgstRate);
                                self.invoiceItemUnderEdit.igstAmount = commonService.calculatePercentage(newValue, self.invoiceItemUnderEdit.igstRate);
                                self.invoiceItemUnderEdit.sgstAmount = commonService.calculatePercentage(newValue, self.invoiceItemUnderEdit.sgstRate);

                                self.calculateTotal(self.invoiceItemUnderEdit);

                            }, true);


						} ]);