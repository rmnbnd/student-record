(function () {
    'use strict';

    angular
        .module('app')
        .factory('Link', Link);

    Link.$inject = ['$resource'];

    function Link($resource) {
        var service = $resource('api/links/:login/:linkId', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': {method: 'POST'},
            'update': {method: 'PUT'},
            'delete': {method: 'DELETE'}
        });

        return service;
    }
})();
