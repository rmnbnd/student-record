(function () {
    'use strict';

    angular
        .module('applicationApp')
        .factory('Youtube', Youtube);

    Youtube.$inject = ['$resource'];

    function Youtube($resource) {
        var service = $resource('api/youtube/google-authorize', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    return {
                        url: data
                    };
                }
            }
        });

        return service;
    }
})();
