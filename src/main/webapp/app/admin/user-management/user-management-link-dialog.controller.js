(function () {
    'use strict';

    angular
        .module('applicationApp')
        .controller('UserManagementLinkDialogController', UserManagementLinkDialogController);

    UserManagementLinkDialogController.$inject = ['$stateParams', '$state', 'User', 'Youtube'];

    function UserManagementLinkDialogController($stateParams, $state, User, Youtube) {
        var vm = this;

        vm.clear = clear;
        vm.save = save;

        Youtube.query(function (data) {
            vm.links = data;
        });

        User.get({
            login: $stateParams.login
        }, function (user) {
            vm.student = user;
        });

        function clear() {
            $state.go('user-management');
        }

        function onSaveSuccess() {
            $state.go('user-management');
        }

        function save() {
            vm.student.links = vm.links.filter(function (link) {
                return link.selected;
            }).map(function (link) {
                return {
                    title: link.title,
                    url: 'https://www.youtube.com/watch?v=' + link.videoId
                }
            });
            if (vm.student.id !== null) {
                User.update(vm.student, onSaveSuccess);
            } else {
                User.save(vm.student, onSaveSuccess);
            }
        }
    }
})();
