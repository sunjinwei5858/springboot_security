package com.sunjinwei.springboot_security.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p> 创建日期 </p>
 *
 * @description:
 */
@Getter
@Setter
public abstract class BaseAddEntity<T extends Model> extends Model<T> {
    /**
     * 创建日期 - 现在时表示主动创建
     */
    @ApiModelProperty(value = "创建日期")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 创建人
     */
//    @TableField(value = "creator_id", fill = FieldFill.INSERT)
//    private Long creatorId;
    /**
     * 是否可用
     */
//    @TableField(fill = FieldFill.INSERT)
//    private Boolean availableFlag;
}
