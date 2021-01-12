package com.zzmxtd.sqltool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmxtd.sqltool.dao.beans.ZSqlTab;
import com.zzmxtd.sqltool.dao.mapper.ZSqlTabMapper;
import com.zzmxtd.sqltool.service.IZSqlTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author zy
 * @date 2021/01/04
 */
@Service
public class ZSqlTabService extends ServiceImpl<ZSqlTabMapper, ZSqlTab> implements IZSqlTabService {
    @Autowired
    ZSqlTabMapper zSqlTabMapper;

    @Override
    public Page getZSqlTabList(ZSqlTab zSqlTab, Long currentPage, Long pageSize) throws Exception {
        Page page = new Page(currentPage,pageSize);

        Page result = null;
        if (null == zSqlTab) {
            result = zSqlTabMapper.selectPage(page, null);
        } else {
            StringBuffer sb = new StringBuffer();
            //项目名称
            String project_name = zSqlTab.getProjectName();
            columnAppendToStr(sb,null,"project_name"," like '%",project_name,"%' and ");
            //项目功能
            String project_function = zSqlTab.getProjectFunction();
            columnAppendToStr(sb,null,"project_function"," like '%",project_function,"%' and ");
            //层级
            String hierarchy = zSqlTab.getHierarchy();
            columnAppendToStr(sb,null,"hierarchy"," like '%",hierarchy,"%' and ");
            //id
            String id = zSqlTab.getId();
            columnAppendToStr(sb,null,"id"," like '%",id,"%' and ");
            //parentId
            String parentId = zSqlTab.getParentId();
            columnAppendToStr(sb,null,"parent_id"," like '%",parentId,"%' and ");
            //name
            String name = zSqlTab.getName();
            columnAppendToStr(sb,null,"name"," like '%",name,"%' and ");
            //enName
            String enName = zSqlTab.getEnName();
            columnAppendToStr(sb,null,"en_name"," like '%",enName,"%' and ");
            String type = zSqlTab.getType();
            columnAppendToStr(sb,null,"type"," like '%",type,"%' and ");
            String sql = zSqlTab.getSql();
            columnAppendToStr(sb,null,"sql"," like '%",sql,"%' and ");

            String sqlStr = sb.toString();
            sb.setLength(0);

            if (StringUtils.isEmpty(sqlStr)) {
                result = zSqlTabMapper.selectPage(page, null);
            } else {
                int lastEnd = sqlStr.lastIndexOf("and ");
                if (lastEnd+4 == sqlStr.length()) {
                    sqlStr = sqlStr.substring(0,lastEnd);
                }
                sb.append(" where ");
                sb.append(sqlStr);
                sqlStr = sb.toString();
                result = zSqlTabMapper.queryByParams(page,sqlStr);
            }
        }
        return result;
    }

    @Override
    public void saveZSqlTabItem(ZSqlTab zSqlTab) throws Exception {
        if (null == zSqlTab) {
            throw new RuntimeException("zSqlTab 对象为null");
        }
        if (StringUtils.isEmpty(zSqlTab.getId())) {
            throw new RuntimeException("zSqlTab.id 不能为null 或 空字符");
        }
        zSqlTab.setSql(changeStrForSave(zSqlTab.getSql()));
        zSqlTab.setRemarks(changeStrForSave(zSqlTab.getRemarks()));
        zSqlTab.setDescripe(changeStrForSave(zSqlTab.getDescripe()));
        zSqlTabMapper.saveZSqlTabItem(zSqlTab);
    }

    @Override
    public ZSqlTab updateZSqlTab(ZSqlTab zSqlTab) throws Exception {
        if (null == zSqlTab) {
            throw new RuntimeException("zSqlTab 对象为null");
        }
        if (StringUtils.isEmpty(zSqlTab.getId())) {
            throw new RuntimeException("zSqlTab.id 不能为null 或 空字符");
        }
        zSqlTab.setSql(changeStrForSave(zSqlTab.getSql()));
        zSqlTab.setRemarks(changeStrForSave(zSqlTab.getRemarks()));
        zSqlTab.setDescripe(changeStrForSave(zSqlTab.getDescripe()));
        zSqlTabMapper.updateZSqlTabItem(zSqlTab);
        return zSqlTab;
    }

    /**
     *
     * @param sb
     * @param before
     * @param dbColumn
     * @param way
     * @param columnField
     * @param end
     */
    public void columnAppendToStr(StringBuffer sb,String before,String dbColumn,String way,String columnField,String end) {
        if (!StringUtils.isEmpty(columnField) || null == sb) {
            if (!StringUtils.isEmpty(before)) {
                sb.append(before);
            }

            if (!StringUtils.isEmpty(end)) {
                sb.append(dbColumn+way+columnField+end);
            } else {
                sb.append(dbColumn+way+columnField);
            }
        }
    }

    /**
     *
     * @param str
     * @return
     */
    public String changeStrForSave(String str) {
        if (null == str) {
            return null;
        }
        String newStr = str.trim();
        if (newStr != "''") {
            StringBuilder sb = new StringBuilder();
//            sb.append("'");
            newStr = newStr.replaceAll("'","\'");
            sb.append(newStr);
//            sb.append("'");
            newStr = sb.toString();
            return newStr;
        } else {
            return null;
        }
    }
}
