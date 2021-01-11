<template>
  <!-- Two-way Data-Binding -->
  <codemirror v-model="sqlStr" :options="cmOptions" />
</template>

<script>
import codemirror from "vue-codemirror";
import "codemirror/mode/sql/sql.js";
import "codemirror/theme/ambiance.css";

export default {
  components: { codemirror },
  props: {
    sqlStr: null
  },
  data () {
    return {
      code: 'const a = 10',
      cmOptions: {
        tabSize: 4,
        mode: 'text/javascript',
        theme: 'base16-dark',
        lineNumbers: true,
        line: true,
        // more CodeMirror options...
      }
    }
  },
  methods: {
    onCmReady(cm) {
      console.log('the editor is readied!', cm)
    },
    onCmFocus(cm) {
      console.log('the editor is focused!', cm)
    },
    onCmCodeChange(newCode) {
      console.log('this is new code', newCode)
      this.code = newCode
    }
  },
  computed: {
    codemirror() {
      return this.$refs.cmEditor.codemirror
    }
  },
  mounted() {
    console.log('the current CodeMirror instance object:', this.codemirror)
    // you can use this.codemirror to do something...
  }
}
</script>