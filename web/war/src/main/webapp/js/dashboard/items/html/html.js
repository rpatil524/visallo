define([
    'flight/lib/component'
], function(
    defineComponent
) {
    'use strict';

    return defineComponent(HtmlDashboardItem);

    function HtmlDashboardItem() {
        this.after('initialize', function() {
            var html = this.attr.item.configuration.sanitizedHtml;
            this.$node.html(html);
        });
    }
});
