<template>
  <dev id="subTable">
    <dev id="left">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="ID">
          <el-input size="small" v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item label="父id">
          <el-input size="small" v-model="form.parentId"></el-input>
        </el-form-item>
        <el-form-item label="父依赖">
          <el-input size="small" v-model="form.dependence"></el-input>
        </el-form-item>
        <el-form-item label="项目名" prop="projectName">
          <el-input size="small" v-model="form.projectName"></el-input>
        </el-form-item>
        <el-form-item label="项目功能" prop="projectFunction">
          <el-input size="small" v-model="form.projectFunction"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input size="small" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="英文名称">
          <el-input size="small" v-model="form.enName"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select size="small" v-model="form.type">
            <el-option label="指标" value="指标"></el-option>
            <el-option label="明细" value="明细"></el-option>
            <el-option label="报表" value="报表"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="层级">
          <el-input size="small" v-model="form.hierarchy"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input size="small" type="textarea" :rows="3" v-model="form.descripe"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input size="small" type="textarea" :rows="3" v-model="form.remarks"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateOnSubmit('form')">更新</el-button>
          <el-button type="primary" @click="createOnSubmit('form')">创建</el-button>
          <el-button type="primary" @click="frashId('form')">更新ID</el-button>
          <el-button @click="onClear">清空</el-button>
        </el-form-item>
      </el-form>
    </dev>

    <dev id="right">
      <textarea ref="editor" :value="form.sql" :v-bind="editor" placeholder="sql文本区"></textarea>
    </dev>
  </dev>
</template>

<script>
import "codemirror/lib/codemirror.css";
import codemirror from "codemirror";
import "codemirror/theme/ambiance.css";
require("codemirror/mode/sql/sql");

import sqlFormatter from "sql-formatter";

export default {
  name: "subTable",
  // components: { sqlFormatter },
  props: {
    sqlStr: null,
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
    },
    parentForm: {
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
  },
  data() {
    return {
      editor: "",
      options: {
        lineNumbers: true,
        line: true,
        tabSize: 4
      },
      rules: {
        projectName: [
          { required: true, message: "请填写项目名", trigger: "change" }
        ],
        projectFunction: [
          { required: true, message: "请填写项目功能", trigger: "change" }
        ],
        name: [{ required: true, message: "请填写项名称", trigger: "change" }],
        type: [{ required: true, message: "请填写类型", trigger: "change" }]
      }
    };
  },
  methods: {
    initeditor() {
      this.editor = codemirror.fromTextArea(this.$refs.editor, this.options);
    },
    //更新数据
    updateOnSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //将sql值传给父sql字段
          this.$parent.currentItem.sql = this.editor.getValue();
          //调用父方法方式传参
          this.$parent.updateCurrentItem();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //创建数据
    createOnSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //将sql值传给父sql字段
          this.$parent.currentItem.sql = this.editor.getValue();
          //调用父方法方式传参
          this.$parent.saveCurrentItem();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    frashId() {
      this.$parent.currentItem.id = this.uuid();
    },
    //清理数据
    onClear() {
      this.$parent.currentItem = {
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

      let uid = this.uuid();
      this.$parent.currentItem.id = uid;
    },
    setEditVal(val) {
      this.editor.setValue(sqlFormatter.format(val));
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
    }
  },
  mounted() {
    this.initeditor();
  }
};
</script>

<style>
/* #subTable {
  margin: 3px;
} */

/* flex */
#subTable {
  display: -webkit-flex;
  display: flex;
  -webkit-flex-direction: row;
  flex-direction: row;
  width: 100%;
  height: 75%;
  /* background-color: yellow; */
}

#left {
  /* background-color: cornflowerblue; */
  width: 40%;
  height: 100%;
  margin-right: 1%;
  /* border: 1px black solid; */
}
#right {
  /* background-color: cornflowerblue; */
  width: 59%;
  min-height: 500px;
  height: 100%;
  /* border: 1px black solid; */
}

.CodeMirror {
  font-family: monospace;
  height: 600px;
  color: black;
  direction: ltr;
}
</style>