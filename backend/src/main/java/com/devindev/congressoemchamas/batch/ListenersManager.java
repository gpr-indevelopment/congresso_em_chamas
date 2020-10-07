package com.devindev.congressoemchamas.batch;

import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ListenersManager {

    @Bean(name = "currentLegislaturePromotionListener")
    public ExecutionContextPromotionListener currentLegislaturePromotionListener() {
        ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
        listener.setKeys(new String[]{"currentLegislature"});
        return listener;
    }
}
