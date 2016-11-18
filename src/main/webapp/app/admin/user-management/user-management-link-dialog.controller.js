(function () {
    'use strict';

    angular
        .module('applicationApp')
        .controller('UserManagementLinkDialogController', UserManagementLinkDialogController);

    UserManagementLinkDialogController.$inject = ['$uibModalInstance', 'entity', 'User', 'Youtube', '$window'];

    function UserManagementLinkDialogController($uibModalInstance, entity, User, Youtube) {
        var vm = this;

        vm.clear = clear;
        vm.save = save;
        vm.student = entity;

        Youtube.query(function (data) {
            vm.links = data;
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function onSaveSuccess(result) {
            $uibModalInstance.close(result);
        }

        function save() {
            vm.student.links = vm.links.filter(function (link) {
                return link.selected;
            }).map(function (link) {
                return {
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
