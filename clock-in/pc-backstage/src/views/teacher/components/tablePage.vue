<template>
  <div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
        >删除
        </el-button>
      </el-col>
    </el-row>

    <el-table ref="table" v-loading="loading" :data="teacherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center"/>
      <el-table-column label="序号" width="55">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="账号" prop="username"/>
      <el-table-column label="真实姓名" prop="realName"/>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleEdit(scope.row.id)">编辑</el-button>
          <el-button type="text" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="list"
    />
  </div>
</template>

<script>
import {getTeacherPage} from "@/api/teacher";

export default {
  name: "TablePage",
  props: {
    queryParams: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      // 非多个禁用
      multiple: true,
      // 选中数组
      ids: [],
      // 老师列表
      teacherList: [],
      loading: true,
      total: 0
    }
  },
  methods: {
    init(data) {
      this.queryParams = data
      this.list()
    },
    list() {
      this.loading = true
      getTeacherPage(this.queryParams).then(res => {
        this.teacherList = res.rows
        this.total = res.total
        this.loading = false
      })
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.multiple = !selection.length
    },
    handleAdd() {
      this.$emit('handleAdd')
    },
    handleEdit(id) {
      this.$emit('handleEdit', id)
    },
    handleDelete(id) {
      let ids = id instanceof Object ? this.ids : id
      this.$emit('handleDelete', ids)
    }
  }
}
</script>

<style scoped>

</style>
