<template>
  <div class="in-coder-panel">
    <textarea ref="editor" :value="sqlStr" :v-bind="editor" @change="change"></textarea>
  </div>
</template>

<script>
import 'codemirror/lib/codemirror.css';
import codemirror from "codemirror";

import "codemirror/theme/ambiance.css";
require("codemirror/mode/sql/sql");

export default {
    props: {
        sqlStr:null,
    },
    data() {
        return {
            editor:"",
            options: {
                lineNumbers:true,
                line:true,
                tabSize:4
            }
        }
    },
    methods: {
        initeditor() {
            this.editor = codemirror.fromTextArea(this.$refs.editor,this.options);
        },
        change() {
            alert(this.editor)
        }
    },
    mounted() {
        this.initeditor()
    }
}
</script>

<style>
.CodeMirror {
    font-family: monospace;
    height: 700px;
    color: black;
    direction: ltr;
}
</style>