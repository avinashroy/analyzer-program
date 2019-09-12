package com.codegrind.analyzer.Beans;

import com.codegrind.analyzer.model.Alerts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class AlertBean {

    @Bean
    Alerts getAlert() {
        return new Alerts();
    }

}
