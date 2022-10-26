<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="电影id" prop="mid">
      <el-input v-model="dataForm.mid" placeholder="电影id"></el-input>
    </el-form-item>
    <el-form-item label="点赞数" prop="thumbUp">
      <el-input v-model="dataForm.thumbUp" placeholder="点赞数"></el-input>
    </el-form-item>
    <el-form-item label="收藏数" prop="collect">
      <el-input v-model="dataForm.collect" placeholder="收藏数"></el-input>
    </el-form-item>
    <el-form-item label="看过数" prop="watched">
      <el-input v-model="dataForm.watched" placeholder="看过数"></el-input>
    </el-form-item>
    <el-form-item label="想看数量" prop="keen">
      <el-input v-model="dataForm.keen" placeholder="想看数量"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          mid: '',
          thumbUp: '',
          collect: '',
          watched: '',
          keen: ''
        },
        dataRule: {
          mid: [
            { required: true, message: '电影id不能为空', trigger: 'blur' }
          ],
          thumbUp: [
            { required: true, message: '点赞数不能为空', trigger: 'blur' }
          ],
          collect: [
            { required: true, message: '收藏数不能为空', trigger: 'blur' }
          ],
          watched: [
            { required: true, message: '看过数不能为空', trigger: 'blur' }
          ],
          keen: [
            { required: true, message: '想看数量不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/movies/infomovie/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.mid = data.infoMovie.mid
                this.dataForm.thumbUp = data.infoMovie.thumbUp
                this.dataForm.collect = data.infoMovie.collect
                this.dataForm.watched = data.infoMovie.watched
                this.dataForm.keen = data.infoMovie.keen
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/movies/infomovie/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'mid': this.dataForm.mid,
                'thumbUp': this.dataForm.thumbUp,
                'collect': this.dataForm.collect,
                'watched': this.dataForm.watched,
                'keen': this.dataForm.keen
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
