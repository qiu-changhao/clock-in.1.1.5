<template>
  <div class="app-container">
    <!-- 表格 -->
    <TablePage :query-params="queryParams" ref="TablePageUser"
               @handleEdit="edit"
               @handleDelete="del"
               @handleAdd="add()"
    ></TablePage>
    <!-- 添加 修改 -->
    <AddEdit ref="AddEditUser" @handleSuccess="init()"></AddEdit>
  </div>
</template>

<script>
import AddEdit from "./components/addEdit"
import methods from "./methods"
import TablePage from "./components/tablePage"
import SearchForm from "./components/searchForm"

export default {
  name: "TeacherIndex",
  components: {SearchForm, TablePage, AddEdit},
  methods: {...methods},
  mounted() {
    this.init();
  },
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      }
    }
  }
}
</script>

<style scoped>

</style>
