package com.example.demo.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/3/29 11:37
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputPageModel.class, name = "ApiThrottleStrategy")
})
public abstract class Page {

    private String type;
//    private String name;
//    private String uiType;
//    private String label;
//
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUiType() {
//        return uiType;
//    }
//
//    public void setUiType(String uiType) {
//        this.uiType = uiType;
//    }
//
//    public String getLabel() {
//        return label;
//    }
//
//    public void setLabel(String label) {
//        this.label = label;
//    }

        private String docId;

    private String tenantId;


    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }


}
