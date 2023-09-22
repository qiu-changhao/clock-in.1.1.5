import {deleteStudent} from "@/api/student"
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
        this.$confirm('此操作将永久删除该资源, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            if (type === 0){
                deleteStudent(ids).then(res => {
                    this.$modal.msgSuccess(res.msg)
                    this.init()
                })
            }else{
                deleteTeacher(ids).then(res => {
                    this.$modal.msgSuccess(res.msg)
                    this.init()
                })
            }

        })
    }
}

