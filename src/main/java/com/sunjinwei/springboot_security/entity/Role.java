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
 * <p>  系统管理-角色表  </p>
 */
@Data
@ApiModel(description = "系统管理-角色表 ")
@TableName("t_sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    @TableField("code")
    private String code;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @TableField("name")
    private String name;
    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    @TableField("remarks")
    private String remarks;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
