package com.bulu.fireworm.message.sender.reveal;

import com.bulu.fireworm.entity.UserEntity;

public interface UserMessageSend {

    //发送用户消息
    void sendUserInfo(UserEntity user)throws Exception;
}
