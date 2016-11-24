(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserManagementDeleteController', UserManagementDeleteController);

    UserManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'Link'];

    function UserManagementDeleteController($uibModalInstance, entity, Link) {
        var vm = this;

        vm.student = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete(entity) {
            Link.delete(entity,
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
