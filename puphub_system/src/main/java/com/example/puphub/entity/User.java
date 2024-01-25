package com.example.puphub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    @JsonProperty(value = "uId")
    private Integer uId;
    @JsonProperty(value = "uName")
    private String uName;
    @JsonProperty(value = "uPassword")
    private String uPassword;
    @TableField(exist = false)
    @JsonProperty(value = "oldPassword")
    private String oldPassword;
    @JsonProperty(value = "uEmail")
    private String uEmail;
    @JsonProperty(value = "uTelephone")
    private String uTelephone;
    @JsonProperty(value = "uBirth")
    private String uBirth;
    @JsonProperty(value = "uGender")
    private String uGender;
//    @TableField(exist = false)
//    @JsonProperty(value = "uClass")
//    private String uClass;
    @JsonProperty(value = "uAddress")
    private String uAddress;
    @TableField(value = "u_userTime")
    @JsonProperty(value = "uUserTime")
    private String uUserTime;
//    @JsonProperty(value = "uAvatar")
//    private String uAvatar;
}
