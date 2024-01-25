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
@TableName("service")
public class Services {
    @TableId(type = IdType.AUTO)
    @JsonProperty(value = "sId")
    private Integer sId;
    @JsonProperty(value = "sName")
    private String sName;
    @JsonProperty(value = "sIntro")
    private String sIntro;
    @JsonProperty(value = "sPrice")
    private String sPrice;
    @JsonProperty(value = "sPoster")
    private String sPoster;
    @TableField(value = "s_serviceTime")
    @JsonProperty(value = "sServiceTime")
    private String sServiceTime;
}
