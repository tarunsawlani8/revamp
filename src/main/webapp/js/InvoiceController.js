'use strict'
var module = angular.module('demo.controllers', []);
module.controller("InvoiceController", ["$scope", "InvoiceService",
    function($scope, InvoiceService) {
        $scope.userDto = {
            userId: null,
            userName: null,
            skillDtos: []
        };
        $scope.skills = [];
        InvoiceService.getUserById(1).then(function(value) {
            console.log(value.data);
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });
        $scope.saveUser = function() {
            $scope.userDto.skillDtos = $scope.skills.map(skill => {
                return {
                    skillId: null,
                    skillName: skill
                };
            });
            InvoiceService.saveUser($scope.userDto).then(function() {
                console.log("works");
                UserService.getAllUsers().then(function(value) {
                    $scope.allUsers = value.data;
                }, function(reason) {
                    console.log("error occured");
                }, function(value) {
                    console.log("no callback");
                });
                $scope.skills = [];
                $scope.userDto = {
                    userId: null,
                    userName: null,
                    skillDtos: []
                };
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
    }
]);