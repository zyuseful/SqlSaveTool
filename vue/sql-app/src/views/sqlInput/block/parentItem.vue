<template>
  <dev id="sqlParentItem">
    <h4 style="text-align:center;">父信息</h4>
    <el-form ref="form" inline="true" :model="form" label-width="100px">
      <el-form-item label="项目名称">
        <el-input size="small" v-model="form.projectName" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="项目功能">
        <el-input size="small" v-model="form.projectFunction" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="层级">
        <el-input size="small" v-model="form.hierarchy" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="id">
        <el-input size="small" v-model="form.id" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="名称">
        <el-input size="small" v-model="form.name" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="英文名称">
        <el-input size="small" v-model="form.enName" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-input size="small" v-model="form.type" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" size="small" v-model="form.descripe" style="width: 800px"></el-input>
      </el-form-item>
    </el-form>
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
      sql: null,
      remarks: null,
      hierarchy: null
    }
  }
};
</script>

<style>
#sqlParentItem {
  /* margin: 3px; */
}
</style>