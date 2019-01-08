package com.zhl.eurekaserver.config;

import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.BackupRegistry;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;



public class StaticBackupServiceRegistry implements BackupRegistry {

    private Applications localRegionApps = new Applications();

    public StaticBackupServiceRegistry() {
        Application orgApplication = new Application("org");
        InstanceInfo orgInstance1 = InstanceInfo.Builder.newBuilder()
                .setAppName("org-service")
                .setVIPAddress("org-service")
                .setSecureVIPAddress("org-service")
                .setInstanceId("org-servce-1")
                .setHostName("192.168.1.2")
                .setIPAddr("192.168.1.2")
                .setPort(8081)
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setStatus(InstanceInfo.InstanceStatus.UP)
                .build();
        InstanceInfo orgInstance2 = InstanceInfo.Builder.newBuilder()
                .setAppName("org-service")
                .setVIPAddress("org-service")
                .setSecureVIPAddress("org-service")
                .setInstanceId("org-instance-1")
                .setHostName("192.168.1.2")
                .setIPAddr("192.168.1.2")
                .setPort(8081)
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setStatus(InstanceInfo.InstanceStatus.UP)
                .build();
        orgApplication.addInstance(orgInstance1);
        orgApplication.addInstance(orgInstance2);
        localRegionApps.addApplication(orgApplication);
    }

    @Override
    public Applications fetchRegistry() {
        return localRegionApps;
    }

    @Override
    public Applications fetchRegistry(String[] includeRemoteRegions) {
        return localRegionApps;
    }



}
