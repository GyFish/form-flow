package com.gyfish.formflow.flow.filter;

import com.gyfish.formflow.flow.event.FlowEvent;

public interface FlowFilter {

    boolean support();

    void doFilter(FlowEvent e, FlowFilterChain chain);

}
