(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserManagementDetailController', UserManagementDetailController);

    UserManagementDetailController.$inject = ['$stateParams', 'User'];

    function UserManagementDetailController($stateParams, User) {
        var vm = this;

        vm.load = load;
        vm.student = {};

        vm.load($stateParams.login);

        function load(login) {
            User.get({login: login}, function (result) {
                vm.student = result;
            });
        }
    }
})();
