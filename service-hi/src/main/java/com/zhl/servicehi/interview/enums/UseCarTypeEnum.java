package com.zhl.servicehi.interview.enums;


public enum UseCarTypeEnum {
    TAXI("1", "出租车"),
    SPECIAL_CAR("2", "专车"),
    EXPRESS_BUS("3","快车"),
    SUBSTITUTE_DRIVING("4","代驾"),
    LUXURY_CAR("5","豪华车"),
    ;
    private String code;
    private String title;

    public String geCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    UseCarTypeEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public static UseCarTypeEnum getUseCarType(String code){
        if (null == code) {
            return null;
        }
        for(UseCarTypeEnum useCarType: UseCarTypeEnum.values()){
            if(useCarType.code.equals(code)){
                return useCarType;
            }
        }
        return null;
    }
}
