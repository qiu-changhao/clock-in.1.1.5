<template>
  <el-form ref="form" :model="user" label-width="80px">
    <el-form-item label="用户名称">
      <el-input v-model="user.realName" maxlength="30"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
    </el-form-item>
  </el-form>
</template>

<script>

const {updateUser} = require("@/api/admin");
export default {
  props: {
    user: {
      type: Object
    }
  },
  methods: {
    submit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateUser(this.user).then(() => {
            this.$modal.msgSuccess("修改成功");
          });
        }
      });
    }
  }
};
</script>
