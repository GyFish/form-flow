package com.gyfish.formflow.flow;

import com.gyfish.formflow.domain.AppUser;
import com.gyfish.formflow.domain.flow.FlowNode;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定义模块应该提供的功能
 *
 * @author geyu
 */
@Component
public interface Engine {

    List<Process> todo(AppUser user);

    List<Process> done(AppUser user);

    FlowNode run(FlowNode node);

}
