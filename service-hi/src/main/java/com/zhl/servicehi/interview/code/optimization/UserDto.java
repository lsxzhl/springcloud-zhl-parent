package com.zhl.servicehi.interview.code.optimization;

import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/21 16:25
 */
public class UserDto implements Serializable {

    private String userName;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 第四步的聚合优化
     * @return
     */
    public User convertToUser(){
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        User user = (User) userDTOConvert.convert(this);
        return user;
    }

    public UserDto convertFor(User user){
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        return userDTOConvert.doBackward(user);
    }

//    private static class UserDTOConvert implements DTOConvert<UserDto,User>{
//
//        @Override
//        public User convert(UserDto userDto) {
//            User user = new User();
//            BeanUtils.copyProperties(userDto,user);
//            return user;
//        }
//    }

    private static class UserDTOConvert extends Converter<UserDto,User> {

        @Override
        protected User doForward(UserDto userDto) {
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }

        @Override
        protected UserDto doBackward(User user) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            return userDto;
        }
    }
}
