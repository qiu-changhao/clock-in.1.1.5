<template>
  <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px" @submit.native.prevent>
    <el-form-item label="账号" prop="username">
      <el-input
          v-model="queryParams.username"
          placeholder="请输入账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "SearchForm",
  props: {
    queryParams: {
      type: Object,
      required: true
    }
  },
  methods: {
    handleQuery() {
      this.$emit('handleQuery')
    },
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery();
    },
  }
}
</script>

<style scoped>

</style>
