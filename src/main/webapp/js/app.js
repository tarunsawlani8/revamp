
var opticalsApp = angular.module("opticals", ['ui.bootstrap', 'opticals.controllers',
    'opticals.services', 'ngRoute'
]);
opticalsApp.constant("CONSTANTS", {
    getAllStocks: "/stocks/getAllStocks",
    addStocks: "/stocks/addStocks",
    saveUser: "/user/saveUser"
});

opticalsApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    
    when('/loadCreateInvoice', {
       templateUrl: 'createInvoice.html',
       controller: 'InvoiceController'
    }).
    
    when('/viewInvoice', {
       templateUrl: 'viewInvoice.html',
       controller: 'InvoiceController'
    }).
    when('/viewStudents', {
        templateUrl: 'viewInvoice.html',
        controller: 'InvoiceController'
     }).
     
     when('/viewStudents', {
         templateUrl: 'viewInvoice.html',
         controller: 'InvoiceController'
      }).
      
    
    otherwise({
       redirectTo: '/addStudent'
    });
 }]);


