(function () {
    'use strict';

    angular
        .module('applicationApp')
        .controller('UserManagementLinkDialogController', UserManagementLinkDialogController);

    UserManagementLinkDialogController.$inject = ['$uibModalInstance', 'entity', 'User'];

    function UserManagementLinkDialogController($uibModalInstance, entity, User) {
        var vm = this;

        vm.clear = clear;
        vm.save = save;
        vm.student = entity;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function onSaveSuccess(result) {
            $uibModalInstance.close(result);
        }

        function save() {
            if (vm.student.id !== null) {
                User.update(vm.student, onSaveSuccess);
            } else {
                User.save(vm.student, onSaveSuccess);
            }
        }
    }
})();
