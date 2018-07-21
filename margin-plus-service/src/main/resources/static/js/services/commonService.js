/**
 * Created by Hitesh Modi on 18-03-2018.
 */
angular.module('modiTradersApp')
    .service('commonService', ['$http', function($http, $window) {

        this.calculatePercentage = function(total, percent) {
            var returnValue;
            if (!isNaN(parseFloat(total)) && !isNaN(parseFloat(percent))) {
                returnValue = parseFloat(total)
                    * parseFloat(percent)
                    / 100;
            } else {
                returnValue = "";
            }
            return returnValue;
        };

        this.multiply = function(operand1, operand2) {
            var returnValue;
            if (!isNaN(parseFloat(operand1)) && !isNaN(parseFloat(operand1))) {
                returnValue = parseFloat(operand1)
                    * parseFloat(operand2);
            } else {
                returnValue = "";
            }
            return returnValue;
        };

        this.add = function(operand1, operand2) {
            var returnValue;
            if (!isNaN(parseFloat(operand1)) && !isNaN(parseFloat(operand2))) {
                returnValue = parseFloat(operand1)
                    + parseFloat(operand2);
            } else {
                returnValue = "";
            }
            return returnValue;
        };

        this.substract = function(operand1, operand2) {
            var returnValue;
            if (!isNaN(parseFloat(operand1)) && !isNaN(parseFloat(operand2))) {
                returnValue = parseFloat(operand1)
                    - parseFloat(operand2);
            } else {
                returnValue = "";
            }
            return returnValue;
        };

        this.isNullOrUndefined = function(object) {
            if (object === undefined) {
                return true;
            }
            if (object === null) {
                return true;
            }
            return false;
        };

    }]);