var app = angular.module('dstools', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider.when('/home', {
        templateUrl: 'pages/Home/home.html'
    }).when('/produtos', {
        templateUrl: 'pages/Produtos/produtos.html'
    })
    .when('/sobre', {
        templateUrl: 'pages/Sobre/sobre.html'
    })
    .when('/admin', {
        templateUrl: 'pages/Admin/admin.html'
    })
    .otherwise({redirectTo: '/home'})
})

app.controller('ProductCtrl', function($scope, $http){
    $http.get("http://localhost:8080/products")
    .then(function(response) {
        $scope.products = response.data;
        $scope.statusCode = response.statusCode;
        console.log(products, statusCode);
    });
});