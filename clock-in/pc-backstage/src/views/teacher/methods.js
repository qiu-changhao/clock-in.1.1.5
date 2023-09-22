import {deleteTeacher} from "@/api/teacher";

export default {
    init() {
        this.$refs.TablePageUser.init(this.queryParams)
    },
    add() {
        this.$refs.AddEditUser.init()
    },
    edit(id) {
        this.$refs.AddEditUser.init(id)
    },
    del(ids) {
        let type = Number.parseInt(this.$store.getters.type)
        this.$confirm('此操作将永久删除该老师, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            deleteTeacher(ids).then(res => {
                this.$modal.msgSuccess(res.msg)
                this.init()
            })
        })
    }
}

