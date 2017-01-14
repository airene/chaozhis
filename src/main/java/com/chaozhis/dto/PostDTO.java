package com.chaozhis.dto;

import java.sql.Timestamp;

public class PostDTO {
    private Long id;

    public void setId(Long id) {
    this.id = id;
    }
    public Long getId() {
    return id;
    }
    private String main_pic_url;

    public String getMain_pic_url() {
        return main_pic_url;
    }

    public void setMain_pic_url(String main_pic_url) {
        this.main_pic_url = main_pic_url;
    }

    private Integer user_id;

    public void setUser_id(Integer user_id) {
    this.user_id = user_id;
    }
    public Integer getUser_id() {
    return user_id;
    }

    private Integer perfix_id;

    public void setPerfix_id(Integer perfix_id) {
    this.perfix_id = perfix_id;
    }
    public Integer getPerfix_id() {
    return perfix_id;
    }

    private Integer class_id;

    public void setClass_id(Integer class_id) {
    this.class_id = class_id;
    }
    public Integer getClass_id() {
    return class_id;
    }

    private Timestamp invalid_time;

    public void setInvalid_time(Timestamp invalid_time) {
    this.invalid_time = invalid_time;
    }
    public Timestamp getInvalid_time() {
    return invalid_time;
    }

    private String title;

    public void setTitle(String title) {
    this.title = title;
    }
    public String getTitle() {
    return title;
    }

    private String tags;

    public void setTags(String tags) {
    this.tags = tags;
    }
    public String getTags() {
    return tags;
    }

    private String post_content;

    public void setPost_content(String post_content) {
    this.post_content = post_content;
    }
    public String getPost_content() {
    return post_content;
    }

    private String buy_link;

    public void setBuy_link(String buy_link) {
    this.buy_link = buy_link;
    }
    public String getBuy_link() {
    return buy_link;
    }

    private String discount_method;

    public void setDiscount_method(String discount_method) {
    this.discount_method = discount_method;
    }
    public String getDiscount_method() {
    return discount_method;
    }

    private Timestamp add_time;

    public void setAdd_time(Timestamp add_time) {
    this.add_time = add_time;
    }
    public Timestamp getAdd_time() {
    return add_time;
    }

    private Integer recommend_count;

    public void setRecommend_count(Integer recommend_count) {
    this.recommend_count = recommend_count;
    }
    public Integer getRecommend_count() {
    return recommend_count;
    }

    private Integer pass_by;

    public void setPass_by(Integer pass_by) {
    this.pass_by = pass_by;
    }
    public Integer getPass_by() {
    return pass_by;
    }

    private Integer unpass_by;

    public void setUnpass_by(Integer unpass_by) {
    this.unpass_by = unpass_by;
    }
    public Integer getUnpass_by() {
    return unpass_by;
    }

    private Integer support_count;

    public void setSupport_count(Integer support_count) {
    this.support_count = support_count;
    }
    public Integer getSupport_count() {
    return support_count;
    }

    private Integer status;

    public void setStatus(Integer status) {
    this.status = status;
    }
    public Integer getStatus() {
    return status;
    }

}