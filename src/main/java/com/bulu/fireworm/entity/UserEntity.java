package com.bulu.fireworm.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserEntity implements Serializable {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String nickname;

    @Getter
    @Setter
    private String loginpwd;

    @Getter
    @Setter
    private String icon;

    @Getter
    @Setter
    private String telnumber;

    @Getter
    @Setter
    private String mail;

    @Getter
    @Setter
    private String wechat;

    @Getter
    @Setter
    private int loginstate;

    @Getter
    @Setter
    private int state;
}
