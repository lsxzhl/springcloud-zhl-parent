package com.zhl.consulserver.deleteServiceShiLi;

import org.springframework.cloud.client.discovery.AbstractDiscoveryLifecycle;

public class ConsulLifecycle extends AbstractDiscoveryLifecycle {
    @Override
    protected int getConfiguredPort() {
        return 0;
    }

    @Override
    protected void setConfiguredPort(int i) {

    }

    @Override
    protected Object getConfiguration() {
        return null;
    }

    @Override
    protected void register() {

    }

    @Override
    protected void deregister() {

    }

    @Override
    protected boolean isEnabled() {
        return false;
    }
}
