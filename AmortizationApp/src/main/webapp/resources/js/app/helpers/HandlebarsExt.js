define(['handlebars',
        'config',
        'app/models/Lookups',
        'standards/helpers/base',
        'standards/helpers/select',
        'hbs!app/templates/shared/select/profiles-widget-style.html',
        'hbs!app/templates/shared/select/secondary-contact-type.html',
        'hbs!app/templates/shared/select/business-identification-type.html',
        'hbs!app/templates/shared/select/client-type-rest.html'],
    function(Handlebars, Config, Lookups, Base, SelectHelper,
             ProfileWidgetStyleSelectTemplate,
             SecondaryContactTypeSelectTemplate,
             BusinessIdentificationTypeSelectTemplate,
             ClientTypeRestSelectTemplate) {
    'use strict';

    Handlebars.registerHelper('ifNotEquals', function(v1, v2, options) {
        if(v1 != v2) {
            return options.fn(this);
        }
        return options.inverse(this);
    });

    Handlebars.registerHelper('ifEquals', function(v1, v2, options) {
        if(v1 == v2) {
            return options.fn(this);
        }
        return options.inverse(this);
    });

    Handlebars.registerHelper('showInEnv', function() {
        var arguments_array = arguments && [].slice.call(arguments);
        if(arguments_array.indexOf(Config.env) >= 0) {
            return _.last(arguments_array).fn(this);
        }
    });

    Handlebars.registerHelper('notShowInEnv', function() {
        var arguments_array = arguments && [].slice.call(arguments);
        if(arguments_array.indexOf(Config.env) < 0) {
            return _.last(arguments_array).fn(this);
        }
    });

    Handlebars.registerPartial('s-profilesWidgetStyleSelect', ProfileWidgetStyleSelectTemplate);
    Handlebars.registerHelper('s-profilesWidgetStyleSelect', function(context, options) {
        if (options['hash']['onboarding']) {
            options['hash']['defaultName'] = 'profiles_widgetStyle';
        } else {
            options['hash']['defaultName'] = 'widgetStyle';
        }
        console.log(Lookups.model.get("widgetStyles"));


        /*
        context['options'] = _.map(Lookups.model.get("widgetStyles"), function(ws) {
            return {
                'value': ws.value,
                'text': ws.displayValue,
                'selected': context[options['hash']['defaultName']] == ws.value
            }
        });
        */
        context['options'] = [
            {
                'value': 'POPUP',
                'text': 'POPUP',
                'selected': context[options['hash']['defaultName']] == "POPUP"
            },
            {
                'value': 'OVERLAY',
                'text': 'OVERLAY',
                'selected': context[options['hash']['defaultName']] == "OVERLAY"
            },
            {
                'value': 'REDIRECT',
                'text': 'REDIRECT',
                'selected': context[options['hash']['defaultName']] == "REDIRECT"
            }
        ];
        return Handlebars.helpers['$']('s-profilesWidgetStyleSelect', context, options);
    });

    Handlebars.registerPartial('s-secondaryContactTypeSelect', SecondaryContactTypeSelectTemplate);
    Handlebars.registerHelper('s-secondaryContactTypeSelect', function(context, options) {
        options['hash']['defaultName'] = 'secondaryContactType';
        context['options'] = [
            {
                'value': 'CUSTOMER_SERVICE',
                'text': 'Customer Service',
                'selected': context[options['hash']['defaultName']] == "CUSTOMER_SERVICE"
            },
            {
                'value': 'TECHNICAL',
                'text': 'Technical',
                'selected': context[options['hash']['defaultName']] == "TECHNICAL"
            },
            {
                'value': 'BUSINESS',
                'text': 'Business',
                'selected': context[options['hash']['defaultName']] == "BUSINESS"
            },
            {
                'value': 'OTHER',
                'text': 'Other',
                'selected': context[options['hash']['defaultName']] == "OTHER"
            }
        ];
        return Handlebars.helpers['$']('s-secondaryContactTypeSelect', context, options);
    });

    Handlebars.registerPartial('s-businessIdentificationTypeSelect', BusinessIdentificationTypeSelectTemplate);
    Handlebars.registerHelper('s-businessIdentificationTypeSelect', function(context, options) {
        options['hash']['defaultName'] = 'businessIdentificationType';
        context['options'] = [
            {
                'value': 'ABN',
                'text': 'ABN',
                'selected': context[options['hash']['defaultName']] == "ABN"
            },
            {
                'value': 'SSN',
                'text': 'SSN',
                'selected': context[options['hash']['defaultName']] == "SSN"
            },
            {
                'value': 'EIN',
                'text': 'EIN',
                'selected': context[options['hash']['defaultName']] == "EIN"
            },
            {
                'value': 'BIN',
                'text': 'BIN',
                'selected': context[options['hash']['defaultName']] == "BIN"
            },
            {
                'value': 'SIN',
                'text': 'SIN',
                'selected': context[options['hash']['defaultName']] == "SIN"
            }
        ];
        return Handlebars.helpers['$']('s-businessIdentificationTypeSelect', context, options);
    });

    Handlebars.registerPartial('s-clientTypeRestSelect', ClientTypeRestSelectTemplate);
    Handlebars.registerHelper('s-clientTypeRestSelect', function(context, options) {
        options['hash']['defaultName'] = 'clientTypeRest';
        context['options'] = [
            {
                'value': 'VISA_MEMBER_PARTNER',
                'text': 'Visa Member Partner',
                'selected': context[options['hash']['defaultName']] == "VISA_MEMBER_PARTNER"
            },
            {
                'value': 'VISA_NON_MEMBER_PARTNER',
                'text': 'Visa Non-Member Partner',
                'selected': context[options['hash']['defaultName']] == "VISA_NON_MEMBER_PARTNER"
            },
            {
                'value': 'DIRECT_MERCHANT',
                'text': 'Direct Merchant',
                'selected': context[options['hash']['defaultName']] == "DIRECT_MERCHANT"
            },
            {
                'value': 'INDIRECT_VISA_MEMBER_MERCHANT',
                'text': 'Indirect Visa Member Merchant',
                'selected': context[options['hash']['defaultName']] == "INDIRECT_VISA_MEMBER_MERCHANT"
            },
            {
                'value': 'INDIRECT_NON_VISA_MEMBER_MERCHANT',
                'text': 'Indirect Non Visa Member Merchant',
                'selected': context[options['hash']['defaultName']] == "INDIRECT_NON_VISA_MEMBER_MERCHANT"
            }
        ];
        return Handlebars.helpers['$']('s-clientTypeRestSelect', context, options);
    });


});