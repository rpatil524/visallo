define([
    'flight/lib/component',
    'util/withDataRequest',
    'hbs!./configureTpl'
], function(
    defineComponent,
    withDataRequest,
    template) {
    'use strict';

    return defineComponent(HtmlDashboardItemConfiguration, withDataRequest);

    function HtmlDashboardItemConfiguration() {
        this.defaultAttrs({
            htmlSelector: 'textarea.html'
        });

        this.after('initialize', function() {
            this.on('change', {
                htmlSelector: this.onHtmlChange
            });

            this.$node.html(template({ html: this.attr.item.configuration.html }));
        });

        this.onHtmlChange = function(event) {
            var html = $(event.target).val();
            this.attr.item.configuration.html = html;
            this.triggerChange();
        };

        this.triggerChange = function() {
            this.trigger('configurationChanged', {
                extension: this.attr.extension,
                item: this.attr.item
            });
        };
    }
});
