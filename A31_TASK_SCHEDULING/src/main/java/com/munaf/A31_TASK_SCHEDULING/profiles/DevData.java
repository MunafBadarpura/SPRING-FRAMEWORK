package com.munaf.A31_TASK_SCHEDULING.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevData implements DataService {

    @Override
    public String getData() {
        return "Dev Data";
    }
}
