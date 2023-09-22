<template>
  <el-dialog :title="title" :visible.sync="open" width="400px" append-to-body>
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="账号">
        <el-input v-model="form.username"/>
      </el-form-item>
      <el-form-item label="真实姓名">
        <el-input v-model="form.realName"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancelForm">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {addTeacher, editTeacher, getTeacherById} from "@/api/teacher";

export default {
  name: "AddEdit",
  data() {
    return {
      title: '',
      open: false,
      form: {
        id: undefined,
        username: undefined,
        realName: undefined,
        password: undefined
      },
      type: undefined
    }
  },
  methods: {
    init(id) {
      this.type = Number.parseInt(this.$store.getters.type)
      this.resetForm()
      if (id) {
        this.title = '修改'

        getTeacherById(id).then(res => {
          this.form = res.data
          this.form.password = undefined
        })

      }
      this.open = true
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {

            editTeacher(this.form).then(res => {
              this.$modal.msgSuccess(res.msg)
              this.cancelForm()
              this.$emit('handleSuccess')
            })

          }
        }
      })
    },
    cancelForm() {
      this.resetForm()
      this.open = false
    },
    resetForm() {
      this.form = {
        id: undefined,
        username: undefined,
        realName: undefined,
        password: undefined
      }
    }
  }
}
</script>

<style scoped>

</style>
