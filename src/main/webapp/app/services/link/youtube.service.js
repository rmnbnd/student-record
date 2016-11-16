(function () {
    'use strict';

    angular
        .module('applicationApp')
        .factory('YoutubeAuthorize', YoutubeAuthorize)
        .factory('Youtube', Youtube);

    Youtube.$inject = ['$resource'];
    YoutubeAuthorize.$inject = ['$resource'];

    function YoutubeAuthorize($resource) {
        var service = $resource('api/youtube/google-authorize', {}, {
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

    function Youtube($resource) {
        return $resource('api/youtube/uploads', {}, {
            'query': {
                method: 'GET',
                isArray: true
            }
        });
    }
})();
