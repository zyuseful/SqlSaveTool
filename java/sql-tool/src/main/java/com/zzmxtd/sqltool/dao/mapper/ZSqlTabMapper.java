package com.zzmxtd.sqltool.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzmxtd.sqltool.dao.beans.ZSqlTab;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author zy
 * @date 2021/01/04
 */
public interface ZSqlTabMapper extends BaseMapper<ZSqlTab> {

    @Select("select * from tab_zsql ${sqlStr}")
    Page<ZSqlTab> queryByParams(Page<ZSqlTab> page, @Param("sqlStr") String sqlStr);

//    @Update("update tab_zsql set sql = E'${zSqlTab}' where id = '123'")
    void saveZSqlTabItem(@Param("zSqlTab")ZSqlTab zSqlTab);

    void updateZSqlTabItem(@Param("zSqlTab")ZSqlTab zSqlTab);
}
