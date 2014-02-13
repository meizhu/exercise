requirejs.config({
    'baseUrl': 'resources/js',
    'deps': ['jquery', 'underscore', 'backbone'],
    'paths': {
        'jquery': 'lib/jquery',
        'underscore': 'lib/underscore',
        'backbone': 'lib/backbone',
        'backbone.marionette': 'lib/backbone.marionette',
        'handlebars': 'lib/handlebars',
        'text': 'lib/text'
    },
    'shim': {
        'underscore': {
            'exports': '_'
        },
        'backbone': {
             'deps': ['jquery', 'underscore'],
            'exports': 'Backbone'
        },
        'backbone.marionette': {
            'deps': ['backbone'],
            'exports': 'Marionette'
        },
        'handlebars': {
            'exports': 'Handlebars'
        }
    }
});

require(['app/App'], function(app) {
    app.start();
});
