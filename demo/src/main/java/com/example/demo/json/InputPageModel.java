package com.example.demo.json;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/3/29 11:41
 */
@JsonTypeName(value = "ApiThrottleStrategy")
public class InputPageModel extends Page {

//    private String input;
//
//    public String getInput() {
//        return input;
//    }
//
//    public void setInput(String input) {
//        this.input = input;
//    }

        private AccessRate accessRate;

    public static class AccessRate{

        /**  调用频次 */
        private String frequency;

        /**  到期日期 */
        private String maturityDate;

        /**  IP限制 */
        private String restrictIp;

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getMaturityDate() {
            return maturityDate;
        }

        public void setMaturityDate(String maturityDate) {
            this.maturityDate = maturityDate;
        }

        public String getRestrictIp() {
            return restrictIp;
        }

        public void setRestrictIp(String restrictIp) {
            this.restrictIp = restrictIp;
        }
    }

    public AccessRate getAccessRate() {
        return accessRate;
    }

    public void setAccessRate(AccessRate accessRate) {
        this.accessRate = accessRate;
    }
}
