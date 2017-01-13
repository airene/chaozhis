package com.chaozhis.dto;

import java.sql.Timestamp;

public class UserDTO {
    private Integer id;

    public void setId(Integer id) {
    this.id = id;
    }
    public Integer getId() {
    return id;
    }

    private String phone;

    public void setPhone(String phone) {
    this.phone = phone;
    }
    public String getPhone() {
    return phone;
    }

    private String nickname;

    public void setNickname(String nickname) {
    this.nickname = nickname;
    }
    public String getNickname() {
    return nickname;
    }

    private String skills;

    public void setSkills(String skills) {
    this.skills = skills;
    }
    public String getSkills() {
    return skills;
    }

    private Integer gongxianzhi;

    public void setGongxianzhi(Integer gongxianzhi) {
    this.gongxianzhi = gongxianzhi;
    }
    public Integer getGongxianzhi() {
    return gongxianzhi;
    }

    private String alipay;

    public void setAlipay(String alipay) {
    this.alipay = alipay;
    }
    public String getAlipay() {
    return alipay;
    }

    private Integer is_post;

    public void setIs_post(Integer is_post) {
    this.is_post = is_post;
    }
    public Integer getIs_post() {
    return is_post;
    }

    private Integer status;

    public void setStatus(Integer status) {
    this.status = status;
    }
    public Integer getStatus() {
    return status;
    }

    private Timestamp reg_time;

    public void setReg_time(Timestamp reg_time) {
    this.reg_time = reg_time;
    }
    public Timestamp getReg_time() {
    return reg_time;
    }

    private Integer referee_id;

    public void setReferee_id(Integer referee_id) {
    this.referee_id = referee_id;
    }
    public Integer getReferee_id() {
    return referee_id;
    }

    private String source;

    public void setSource(String source) {
    this.source = source;
    }
    public String getSource() {
    return source;
    }

}