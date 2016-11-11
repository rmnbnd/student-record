(function () {
    'use strict';

    angular
        .module('applicationApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope', '$state', '$timeout', 'Auth', '$uibModalInstance'];

    function LoginController($rootScope, $state, $timeout, Auth, $uibModalInstance) {
        var vm = this;

        vm.authenticationError = false;
        vm.cancel = cancel;
        vm.credentials = {};
        vm.login = login;
        vm.password = null;
        vm.username = null;

        $timeout(function () {
            angular.element('#username').focus();
        });

        function cancel() {
            vm.credentials = {
                username: null,
                password: null
            };
            vm.authenticationError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function login(event) {
            event.preventDefault();
            Auth.login({
                username: vm.username,
                password: vm.password
            }).then(function () {
                vm.authenticationError = false;
                $uibModalInstance.close();

                $rootScope.$broadcast('authenticationSuccess');

                if (Auth.getPreviousState()) {
                    var previousState = Auth.getPreviousState();
                    Auth.resetPreviousState();
                    $state.go(previousState.name, previousState.params);
                }
            }).catch(function () {
                vm.authenticationError = true;
            });
        }

    }
})();
