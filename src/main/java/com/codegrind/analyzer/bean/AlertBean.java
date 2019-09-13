package com.codegrind.analyzer.bean;

import com.codegrind.analyzer.model.Alerts;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AlertBean {

    @Bean
    Alerts getAlert() {
        return new Alerts();
    }

}
