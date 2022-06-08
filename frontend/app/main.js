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