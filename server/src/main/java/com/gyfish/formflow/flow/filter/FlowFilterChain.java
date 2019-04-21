package com.gyfish.formflow.flow.filter;

import com.gyfish.formflow.domain.flow.definition.FlowAction;
import com.gyfish.formflow.flow.event.FlowEvent;

public class FlowFilterChain {

    private int n = 0;

    private FlowAction flowAction;

    private FlowFilter[] filters = new FlowFilter[0];

    public FlowFilterChain(FlowAction action) {
        this.flowAction = action;
    }

    public FlowFilterChain addFilter(FlowFilter flowFilter) {

        for (FlowFilter filter : filters)
            if (filter == flowFilter) return this;

        // 扩容
        if (n == filters.length) {
            FlowFilter[] newFilters = new FlowFilter[n + 10];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }

        filters[n++] = flowFilter;
        return this;
    }

    public void doFilter(FlowEvent e) {

        int pos = 0;
        if (pos >= n)
            flowAction.start(e);

        FlowFilter filter = filters[n++];
        filter.doFilter(e, this);
    }
}
