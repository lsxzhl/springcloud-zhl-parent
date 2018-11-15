package com.zhl.servicefeign.client.fallback;

import com.zhl.servicefeign.client.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHysric implements SchedualServiceHi {
    @Override
    public String getName(String name) {
        return "Soory you cann't stay here!" + name;
    }
}
