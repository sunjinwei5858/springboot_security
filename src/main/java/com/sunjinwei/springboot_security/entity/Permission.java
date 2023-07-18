package com.sunjinwei.springboot_security.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>  系统管理-权限权限表  </p>
 */
@Data
@ApiModel(description = "系统管理-权限表 ")
@TableName("t_sys_permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * url
     */
    @ApiModelProperty(value = "url")
    @TableField("url")
    private String url;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
