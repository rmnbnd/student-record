(function() {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('links', {
            parent: 'account',
            url: '/links',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Links'
            },
            views: {
                'content@': {
                    templateUrl: 'app/account/links/links.html',
                    controller: 'LinksController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
