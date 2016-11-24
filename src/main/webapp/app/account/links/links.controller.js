(function () {
    'use strict';

    angular
        .module('app')
        .controller('LinksController', LinksController);

    LinksController.$inject = ['Principal', 'User'];

    function LinksController(Principal, User) {
        var vm = this;

        Principal.identity().then(function (account) {
            User.get({login: account.login}, function (result) {
                vm.student = result;
            });
        });
    }
})();
