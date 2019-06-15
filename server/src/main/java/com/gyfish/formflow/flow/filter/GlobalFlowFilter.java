package com.gyfish.formflow.flow.filter;

import com.gyfish.formflow.flow.event.FlowEvent;

public class GlobalFlowFilter implements FlowFilter {


    @Override
    public void doFilter(FlowEvent e, FlowFilterChain chain) {

        System.out.println("=== do sth global ===");
        chain.doFilter(e);
    }
}
