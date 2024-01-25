package com.example.puphub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order {
    @TableId(type = IdType.AUTO)
    @JsonProperty(value = "oId")
    private Integer oId;
    @JsonProperty(value = "oUid")
    private String oUid;
    @JsonProperty(value = "oService")
    private String oService;
    @JsonProperty(value = "oDate")
    private String oDate;
    @TableField(value = "o_orderTime")
    @JsonProperty(value = "oOrderTime")
    private String oOrderTime;
    @JsonProperty(value = "oTime")
    private String oTime;
    @JsonProperty(value = "oAge")
    private String oAge;
    @JsonProperty(value = "oSize")
    private String oSize;
    @JsonProperty(value = "oAddress")
    private String oAddress;
    @JsonProperty(value = "oContact")
    private String oContact;
    @JsonProperty(value = "oStatus")
    private String oStatus;
}
