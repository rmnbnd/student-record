(function () {
    'use strict';

    angular
        .module('applicationApp')
        .controller('SettingsController', SettingsController);

    SettingsController.$inject = ['Principal', 'User'];

    function SettingsController(Principal, User) {
        var vm = this;

        Principal.identity().then(function (account) {
            User.get({login: account.login}, function (result) {
                vm.student = result;
            });
        });
    }
})();
