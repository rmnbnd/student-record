(function () {
    'use strict';

    angular
        .module('applicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('user-management', {
                parent: 'admin',
                url: '/user-management?page&sort',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'Users'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/admin/user-management/user-management.html',
                        controller: 'UserManagementController',
                        controllerAs: 'vm'
                    }
                }, params: {
                    page: {
                        value: '1',
                        squash: true
                    },
                    sort: {
                        value: 'id,asc',
                        squash: true
                    }
                },
                resolve: {
                    pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                        return {
                            page: PaginationUtil.parsePage($stateParams.page),
                            sort: $stateParams.sort,
                            predicate: PaginationUtil.parsePredicate($stateParams.sort),
                            ascending: PaginationUtil.parseAscending($stateParams.sort)
                        };
                    }]
                }
            })
            .state('user-management-detail', {
                parent: 'admin',
                url: '/user/:login',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'Application'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/admin/user-management/user-management-detail.html',
                        controller: 'UserManagementDetailController',
                        controllerAs: 'vm'
                    }
                }
            })
            .state('user-management.new', {
                parent: 'user-management',
                url: '/{login}/new-link',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/admin/user-management/user-management-link-dialog.html',
                        controller: 'UserManagementLinkDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            entity: ['User', function (User) {
                                return User.get({login: $stateParams.login});
                            }]
                        }
                    }).result.then(function () {
                        $state.go('user-management', null, {reload: true});
                    }, function () {
                        $state.go('user-management');
                    });
                }]
            })
            .state('user-management.edit', {
                parent: 'user-management',
                url: '/{login}/edit',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/admin/user-management/user-management-dialog.html',
                        controller: 'UserManagementDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            entity: ['User', function (User) {
                                return User.get({login: $stateParams.login});
                            }]
                        }
                    }).result.then(function () {
                        $state.go('user-management', null, {reload: true});
                    }, function () {
                        $state.go('^');
                    });
                }]
            })
            .state('user-management.delete', {
                parent: 'user-management',
                url: '/{login}/{linkId}/delete',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', '$uibModalStack', function ($stateParams, $state, $uibModal, $uibModalStack) {
                    $uibModal.open({
                        templateUrl: 'app/admin/user-management/user-management-delete-dialog.html',
                        controller: 'UserManagementDeleteController',
                        controllerAs: 'vm',
                        size: 'md',
                        resolve: {
                            entity: ['User', function () {
                                return {
                                    login: $stateParams.login,
                                    linkId: $stateParams.linkId
                                };
                            }]
                        }
                    }).result.then(function () {
                        $uibModalStack.dismissAll();
                        $state.go('user-management', null, {reload: true});
                    }, function () {
                        $state.go('^');
                    });
                }]
            });
    }
})();
