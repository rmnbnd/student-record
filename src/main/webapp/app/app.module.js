(function() {
    'use strict';

    angular
        .module('app', [
            'ngStorage',
            'ngResource',
            'ui.bootstrap',
            'ui.router'
        ])
        .run(run);

    run.$inject = ['stateHandler'];

    function run(stateHandler) {
        stateHandler.initialize();
    }
})();
