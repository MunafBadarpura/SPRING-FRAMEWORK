package com.munaf.A31_TASK_SCHEDULING.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdData implements DataService {
    @Override
    public String getData() {
        return "Prod Data";
    }
}
