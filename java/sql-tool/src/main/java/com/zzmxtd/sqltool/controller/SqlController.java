package com.zzmxtd.sqltool.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzmxtd.sqltool.common.Res;
import com.zzmxtd.sqltool.dao.beans.ZSqlTab;
import com.zzmxtd.sqltool.dao.mapper.ZSqlTabMapper;
import com.zzmxtd.sqltool.service.impl.ZSqlTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * TODO this is description
 *
 * @author zy
 * @date 2021/01/04
 */
@RestController
public class SqlController {
    @Autowired
    ZSqlTabMapper zSqlTabMapper;

    @Autowired
    ZSqlTabService zSqlTabService;

    @RequestMapping("/test1")
    public Object test1() {
        List<ZSqlTab> zSqlTabs = zSqlTabMapper.selectList(null);
        return zSqlTabs;
    }
    @RequestMapping("/test2")
    public Object test2(String cp,String se) {
        /*
        Object result = null;
        long currentPage = Long.parseLong(cp);
        long pageSize = Long.parseLong(se);
        Page page = new Page(currentPage,pageSize);
        result = zSqlTabMapper.selectPage(page,null);
        return result;
        */
        Object result = null;
        try {
            ZSqlTab z = new ZSqlTab();
            result = zSqlTabService.getZSqlTabList(z,1L,1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存数据
     * @param zSqlTab
     * @return
     */
    @PostMapping("/saveZSqlTab")
    public Res saveZSqlTab(@RequestBody ZSqlTab zSqlTab) {
        try {
            zSqlTab.setId(zSqlTab.getId().trim());
            zSqlTab.setUpTime(new Timestamp(System.currentTimeMillis()));

            zSqlTab.setSql(zSqlTabService.changeStrForSave(zSqlTab.getSql()));
            zSqlTab.setRemarks(zSqlTabService.changeStrForSave(zSqlTab.getRemarks()));
            zSqlTab.setDescripe(zSqlTabService.changeStrForSave(zSqlTab.getDescripe()));
            zSqlTabService.save(zSqlTab);

//            zSqlTabService.saveZSqlTabItem(zSqlTab);
            return Res.success("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Res.failure(e.getMessage());
        }
    }

    /**
     * 更新数据
     * @param zSqlTab
     * @return
     */
    @PostMapping("/updateZSqlTab")
    public Res updateZSqlTab(@RequestBody ZSqlTab zSqlTab) {
        try {
            zSqlTab.setId(zSqlTab.getId().trim());
            zSqlTab.setUpTime(new Timestamp(System.currentTimeMillis()));


            zSqlTab.setSql(zSqlTabService.changeStrForSave(zSqlTab.getSql()));
            zSqlTab.setRemarks(zSqlTabService.changeStrForSave(zSqlTab.getRemarks()));
            zSqlTab.setDescripe(zSqlTabService.changeStrForSave(zSqlTab.getDescripe()));
            zSqlTabMapper.updateById(zSqlTab);
//            zSqlTabService.updateZSqlTab(zSqlTab);

            return Res.success("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Res.failure(e.getMessage());
        }
    }

    /**
     * 数据列表
     * @param zSqlTab
     * @param currentPage
     * @param pageSize
     * @return
     */
    @PostMapping("/getZSqlTabList/{currentPage}/{pageSize}")
    public Res getZSqlTabList(@RequestBody ZSqlTab zSqlTab, @PathVariable("currentPage")Long currentPage,@PathVariable("pageSize") Long pageSize) {

        if (null == pageSize) {
            pageSize = 10L;
        }
        if (null == currentPage) {
            currentPage = 1L;
        }

        try {
            Page zSqlTabList = zSqlTabService.getZSqlTabList(zSqlTab, currentPage, pageSize);
            return Res.success(zSqlTabList);
        } catch (Exception e) {
            e.printStackTrace();
            return Res.failure(e.getMessage());
        }
    }
}
