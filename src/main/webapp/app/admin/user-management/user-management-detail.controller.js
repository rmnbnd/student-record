(function () {
    'use strict';

    angular
        .module('applicationApp')
        .controller('UserManagementDetailController', UserManagementDetailController);

    UserManagementDetailController.$inject = ['$stateParams', '$sce', 'User'];

    function UserManagementDetailController($stateParams, $sce, User) {
        var vm = this;

        vm.load = load;
        vm.trustSrc = trustSrc;
        vm.student = {};

        vm.load($stateParams.login);

        function load(login) {
            User.get({login: login}, function (result) {
                vm.student = result;
            });
        }

        function trustSrc(src) {
            return $sce.trustAsResourceUrl(src);
        }
    }
})();
