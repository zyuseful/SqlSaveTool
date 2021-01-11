package com.zzmxtd.sqltool.dao.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

/**
 * sql记录表
 * insert into tab_zsql values ('1','test','pro_fun','导出','export','1','宽表','01','select * from sss','hh','0-0')
 * @author zy
 * @date 2021/01/04
 */
@Data
@Getter
@ToString
@TableName("tab_zsql")
public class ZSqlTab {
    @TableId("id")
    private String id;
    @TableField("project_function")
    private String projectFunction;
    @TableField("project_name")
    private String projectName;
    @TableField("name")
    private String name;
    @TableField("en_name")
    private String enName;
    @TableField("type")
    private String type;
    @TableField("dependence")
    private String dependence;
    @TableField("parent_id")
    private String parentId;
    @TableField("descripe")
    private String descripe;
    @TableField("sql")
    private String sql;
    @TableField("remarks")
    private String remarks;
    @TableField("hierarchy")
    private String hierarchy;
    @TableField("uptime")
    private Timestamp upTime;
}
