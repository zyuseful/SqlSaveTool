package com.zzmxtd.sqltool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmxtd.sqltool.dao.beans.ZSqlTab;

/**
 *
 * @author zy
 * @date 2021/01/04
 */
public interface IZSqlTabService extends IService<ZSqlTab> {
    public Object getZSqlTabList(ZSqlTab zSqlTab, Long pageSize, Long currentPage) throws Exception;

    public void saveZSqlTabItem(ZSqlTab zSqlTab) throws Exception;

    public ZSqlTab updateZSqlTab(ZSqlTab zSqlTab) throws Exception;
}
