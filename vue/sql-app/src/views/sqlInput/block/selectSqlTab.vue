<template>
    <dev id="selectSqlTab">
        <!-- 搜索 -->
        <dev :model="form"></dev>
        <!-- 展示 + 分页 -->
        <dev>
            <el-table :data="tableData" height="150" border style="width: 100%">
                <el-table-column
                    prop="projectName"
                    label="项目名称"
                />
                <el-table-column
                    prop="id"
                    label="id"
                />
                <el-table-column
                    prop="name"
                    label="名称"
                />
                <el-table-column
                    prop="enName"
                    label="英文名"
                />
                <el-table-column
                    prop="type"
                    label="类型"
                />
                <el-table-column hidden="true"
                    prop="descripe"
                    label="描述"
                />
                <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                    <template slot-scope="scope">
                        <el-button @click="clickForParentCache(scope.row)" type="text" size="small">父项目</el-button>
                        <!-- <el-button type="text" size="small"></el-button> -->
                    </template>
                </el-table-column>
            </el-table>
        </dev>
    </dev>
</template>

<script>
export default {
    props: {
        form: {
                id: null,
                name: null,
                projectFunction: null,
                projectName: null,
                enName: null,
                type: null,
                dependence: null,
                parentId: null,
                descripe: null,
                sql:null,
                remarks: null,
                hierarchy: null
        }
    },
    // name:"selectSqlTab",
    data() {
        return {
            //table 数据
            // tableData:null,
            tableData:[
                {projectName:"1",id:"1",name:"a1",enName:"e1"},
                {projectName:"2",id:"2",name:"a2",enName:"e2"},
            ],
        }
    },
    methods: {
        //将值赋予父组件
        clickForParentCache(item) {
            console.log(item)
            this.$parent.cacheParentItem.projectName = null
            // this.$parent.cacheParentItem.id = item.id
            // this.$parent.currentItem.projectName = item.projectName
            // this.$parent.cacheParentItem.name = item.name
            // this.$parent.cacheParentItem.enName = item.enName
            // this.$parent.cacheParentItem.type = item.type
            // this.$parent.cacheParentItem.descripe = item.descripe
        }
    }
}
</script>

<style>

</style>