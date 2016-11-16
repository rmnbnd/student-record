(function () {
    'use strict';

    angular
        .module('applicationApp')
        .factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = ['$http', '$localStorage', '$window', 'YoutubeAuthorize'];

    function AuthServerProvider($http, $localStorage, $window, YoutubeAuthorize) {
        var service = {
            getToken: getToken,
            hasValidToken: hasValidToken,
            login: login,
            logout: logout
        };

        return service;

        function getToken() {
            var token = $localStorage.authenticationToken;
            return token;
        }

        function hasValidToken() {
            var token = this.getToken();
            return !!token;
        }

        function login(credentials) {
            var data = 'j_username=' + encodeURIComponent(credentials.username) +
                '&j_password=' + encodeURIComponent(credentials.password) +
                '&remember-me=' + credentials.rememberMe + '&submit=Login';

            return $http.post('api/authentication', data, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).success(function (response) {
                YoutubeAuthorize.get(function (data) {
                    $window.open(data.url);
                });
                return response;
            });
        }

        function logout() {


            // logout from the server
            $http.post('api/logout').success(function (response) {
                delete $localStorage.authenticationToken;
                // to get a new csrf token call the api
                $http.get('api/account');
                return response;
            });

        }
    }
})();
