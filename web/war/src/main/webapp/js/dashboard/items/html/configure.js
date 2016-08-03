define([
    'flight/lib/component',
    'util/withDataRequest',
    'hbs!./configureTpl',
    'quill'
], function(defineComponent,
            withDataRequest,
            template,
            Quill) {
    'use strict';

    return defineComponent(HtmlDashboardItemConfiguration, withDataRequest);

    function HtmlDashboardItemConfiguration() {
        this.after('initialize', function() {
            this.$node.html(template({html: this.attr.item.configuration.sanitizedHtml}));

            var self = this;
            var $editor = this.$node.find('.editor');
            var $toolbar = this.$node.find('.toolbar');

            this.quill = new Quill($editor.get(0), {
                theme: 'snow'
            });
            this.quill.addModule('toolbar', {container: $toolbar.get(0)});
            this.quill.on('text-change', function (delta, source) {
                var html = self.quill.getHTML();
                if ($(html).text() === '') {
                    html = '';
                }
                self.attr.item.configuration.sanitizedHtml = html;
                self.triggerChange();
            });
        });

        this.triggerChange = function() {
            this.trigger('configurationChanged', {
                extension: this.attr.extension,
                item: this.attr.item
            });
        };
    }
});
