<template>
  <dev>
    <!-- 弹出抽屉 -->
    <dev>
      <dev>
        <!-- 悬浮打开左侧 -->
        <el-button
          icon="el-icon-search"
          circle
          style="position: absolute; left: 0px; top: 20px;"
          @click="leftEjectDrawer.showEjectDrawer = true"
        ></el-button>
        <el-drawer
          title="数据查询"
          :visible.sync="leftEjectDrawer.showEjectDrawer"
          direction="ltr"
          size="80%"
        >
          <!-- 搜索框 -->
          <el-form
            ref="leftSelectform"
            inline="true"
            :model="leftEjectDrawer.leftSearch"
            label-width="100px"
          >
            <el-form-item label="项目名称">
              <el-input
                size="small"
                v-model="leftEjectDrawer.leftSearch.projectName"
                style="width: 100px"
              ></el-input>
            </el-form-item>
            <el-form-item label="项目功能">
              <el-input
                size="small"
                v-model="leftEjectDrawer.leftSearch.projectFunction"
                style="width: 100px"
              ></el-input>
            </el-form-item>
            <el-form-item label="层级">
              <el-input
                size="small"
                v-model="leftEjectDrawer.leftSearch.hierarchy"
                style="width: 100px"
              ></el-input>
            </el-form-item>
            <el-form-item label="id">
              <el-input size="small" v-model="leftEjectDrawer.leftSearch.id" style="width: 100px"></el-input>
            </el-form-item>
            <el-form-item label="名称">
              <el-input size="small" v-model="leftEjectDrawer.leftSearch.name" style="width: 100px"></el-input>
            </el-form-item>
            <el-form-item label="英文名称">
              <el-input
                size="small"
                v-model="leftEjectDrawer.leftSearch.enName"
                style="width: 100px"
              ></el-input>
            </el-form-item>
            <el-form-item label="类型">
              <el-select size="small" v-model="leftEjectDrawer.leftSearch.type" style="width: 100px">
                <el-option label="指标" value="指标"></el-option>
                <el-option label="明细" value="明细"></el-option>
                <el-option label="报表" value="报表"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="描述">
              <el-input
                size="small"
                v-model="leftEjectDrawer.leftSearch.descripe"
                style="width: 100px"
              ></el-input>
            </el-form-item>
            <el-form-item label="sql">
              <el-input size="small" v-model="leftEjectDrawer.leftSearch.sql" style="width: 100px"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="leftSearchSubmit">搜索</el-button>
              <el-button @click="leftSearchClear">清空</el-button>
            </el-form-item>
          </el-form>
          <!-- 表格 -->
          <el-table :data="leftEjectDrawer.leftTableData"  border style="width: 100%" max-height="600px">
            <el-table-column prop="projectName" label="项目名称"/>
            <el-table-column prop="projectFunction" label="项目功能" />
            <el-table-column prop="hierarchy" label="层级" />
            <el-table-column prop="id" label="id" />
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="enName" label="英文名" />
            <el-table-column prop="type" label="类型" />
            
            <el-table-column hidden="descripe" prop="descripe" label="描述">
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>{{ scope.row.descripe }}</p>
                  <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.descripe }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            
            <el-table-column hidden="sql" prop="sql" label="sql">
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>{{ scope.row.sql }}</p>
                  <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.sql }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>

            <el-table-column hidden="remarks" prop="remarks" label="remarks">
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>{{ scope.row.remarks }}</p>
                  <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.remarks }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>

            <el-table-column fixed="right" label="操作" width="100">
              <template slot-scope="scope">
                <el-button @click="clickForParentCache(scope.row)" type="text" size="small">父项目</el-button>
                <el-button @click="clickForViewCache(scope.row)" type="text" size="small">查看</el-button>
                <!-- <el-button type="text" size="small"></el-button> -->
              </template>
            </el-table-column>
          </el-table>
          <!-- 分页 -->
          <div style="text-align: center;">
            <el-pagination
              @size-change="leftEjectDrawerHandleSizeChange"
              @current-change="leftEjectDrawerHandleCurrentChange"
              :current-page="leftEjectDrawer.currentPage"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="leftEjectDrawer.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="leftEjectDrawer.total"
            ></el-pagination>
          </div>
        </el-drawer>
      </dev>

      <dev>
        <el-button
          icon="el-icon-edit"
          circle
          style="position: absolute; left: 50px; top: 20px;"
          @click="topEjectDrawer.topElDrawer = true"
        ></el-button>
        <el-drawer title="剪切板" :visible.sync="topEjectDrawer.topElDrawer" direction="ttb" size="50%">
          <!-- <el-input type="textarea" :rows="10"></el-input> -->
          <textarea style="margin:5px;height:90%;width:98%"></textarea>
        </el-drawer>
      </dev>
    </dev>

    <!-- 布局编辑区 -->
    <dev id="sqlInput">
      <dev id="top">
        <!-- 父组件 -->
        <parentItemBlock :form="cacheParentItem"></parentItemBlock>
      </dev>

      <dev id="editPlace">
        <!-- <font style="font-weight: bold;">编辑与提交</font> -->
        <!-- 编辑与提交 -->
        <subTable ref="subTable" :form="currentItem" :parentForm="cacheParentItem"></subTable>
      </dev>
    </dev>
  </dev>
</template>

<script>
import parentItemBlock from "./block/parentItem";
import subTable from "./block/subTable";
export default {
  name: "sqlInput",
  components: { parentItemBlock, subTable },
  data() {
    return {
      /* 剪切板父信息 */
      cacheParentItem: {
        id: null,
        projectFunction: null,
        projectName: null,
        name: null,
        enName: null,
        type: null,
        dependence: null,
        parentId: null,
        descripe: null,
        sql: null,
        remarks: null,
        hierarchy: null
      },

      /* 提交信息 */
      currentItem: {
        id: null,
        projectFunction: null,
        projectName: null,
        name: null,
        enName: null,
        type: null,
        dependence: null,
        parentId: null,
        descripe: null,
        sql: null,
        remarks: null,
        hierarchy: null,
        upTime: null,
      },

      /* 左侧弹出抽屉控制 */
      leftEjectDrawer: {
        // 显示开关
        showEjectDrawer: false,
        // 查询对象
        leftSearch: {
          id: null,
          name: null,
          projectFunction: null,
          projectName: null,
          enName: null,
          type: null,
          dependence: null,
          parentId: null,
          descripe: null,
          sql: null,
          remarks: null,
          hierarchy: null
        },
        // 当前页
        currentPage: 1,
        pageSize: 10,
        total:null,
        /* 弹出层数据 */
        leftTableData: []
      },
      /* 顶部弹出抽屉控制 */
      topEjectDrawer: {
        topElDrawer: false
      }
    };
  },
  methods: {
    /* 创建提交 由 subTable 回调*/
    saveCurrentItem() {
      this.$axios({
        method: "post",
        url: this.htpUrl+"/saveZSqlTab",
        data: this.currentItem
      })
      .then(response => {
          if (response.data != null && response.data.respCode == 1) {
            this.successInfoTips(response.data.respBody)
          } else {
            this.errorInfoTips(response.data.respMsg)
          }
        })
        .catch(error => {
          console.log(error); //请求失败返回的数据
          this.errorInfoTips(error)
        });
    },
    /* 更新 */
    updateCurrentItem() {
      console.log(this.currentItem)
      this.$axios({
        method: "post",
        url: this.htpUrl+"/updateZSqlTab",
        data: this.currentItem
      })
      .then(response => {
          if (response.data != null && response.data.respCode == 1) {
            this.successInfoTips(response.data.respBody)
          } else {
            this.errorInfoTips(response.data.respMsg)
          }
        })
        .catch(error => {
          console.log(error); //请求失败返回的数据
          this.errorInfoTips(error)
        });
    },
    clickForViewCache(item) {
        this.currentItem = item
        this.$refs.subTable.setEditVal(item.sql);
    },


    /* 弹出层 -- 数据赋值给 父项目 */
    clickForParentCache(item) {
      console.log(item);
      this.cacheParentItem.id = item.id;
      this.cacheParentItem.projectName = item.projectName;
      this.cacheParentItem.name = item.name;
      this.cacheParentItem.enName = item.enName;
      this.cacheParentItem.type = item.type;
      this.cacheParentItem.descripe = item.descripe;
      
      this.currentItem.parentId = item.id;
      this.currentItem.dependence = item.name;
    },
    /* 搜索 */
    leftSearchSubmit() {
      this.$axios({
        method: "post",
        url: this.htpUrl+"/getZSqlTabList/"+this.leftEjectDrawer.currentPage+"/"+this.leftEjectDrawer.pageSize,
        data: this.leftEjectDrawer.leftSearch
      }).then(response => {
          if (response.data != null && response.data.respCode == 1) {
            console.log(response.data.respBody.records);
            this.leftEjectDrawer.leftTableData = response.data.respBody.records;
            this.leftEjectDrawer.total = response.data.respBody.total
            this.leftEjectDrawer.currentPage = response.data.respBody.current
          }
        })
        .catch(error => {
          console.log(error); //请求失败返回的数据
        });
    },
    // 清空查询条件
    leftSearchClear() {
      this.leftEjectDrawer.leftSearch = {
        id: null,
        projectFunction: null,
        projectName: null,
        name: null,
        enName: null,
        type: null,
        dependence: null,
        parentId: null,
        descripe: null,
        sql: null,
        remarks: null,
        hierarchy: null
      };
    },
    // 每页多少条
    leftEjectDrawerHandleSizeChange(val) {
      this.leftEjectDrawer.pageSize = val
      this.leftSearchSubmit()
    },
    // 当前页
    leftEjectDrawerHandleCurrentChange(val) {
      this.leftEjectDrawer.currentPage = val
      this.leftSearchSubmit()
    },

    uuid() {
      var s = [];
      var hexDigits = "0123456789abcdef";
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] = "4";
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
      // s[8] = s[13] = s[18] = s[23] = "-";
      s[8] = s[13] = s[18] = s[23] = "";

      this.uuidA = s.join("");
      console.log(s.join(""), 's.join("")');
      return this.uuidA;
    },
    successInfoTips(info) {
      this.$notify({
                    title: '成功',
                    message: info,
                    duration: 1500,
                    type: 'success'
                  });
    },
    errorInfoTips(info) {
      this.$notify({
                    title: '失败',
                    message: info,
                    duration: 1500,
                    type: 'warn'
                  });
    },
  }
};
</script>

<style>
/* flex */
#sqlInput {
  /* margin-top: 3px; */
  padding: 0%;
  display: -webkit-flex;
  display: flex;
  -webkit-flex-direction: column;
  flex-direction: column;
  width: 100%;
  height: 100%;
  /* background-color: lightgrey; */
}
#top {
  height: 25%;
  /* text-align: center; */
}
#editPlace {
}
</style>