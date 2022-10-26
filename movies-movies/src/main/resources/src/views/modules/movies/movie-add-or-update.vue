<template>
  <el-dialog
    :title="!dataForm.mid ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类型" prop="tags">
      <el-input v-model="dataForm.tags" placeholder="类型"></el-input>
    </el-form-item>
    <el-form-item label="发布日期" prop="date">
      <el-input v-model="dataForm.date" placeholder="发布日期"></el-input>
    </el-form-item>
    <el-form-item label="出演明星" prop="stars">
      <el-input v-model="dataForm.stars" placeholder="出演明星"></el-input>
    </el-form-item>
    <el-form-item label="细节信息" prop="detail">
      <el-input v-model="dataForm.detail" placeholder="细节信息"></el-input>
    </el-form-item>
    <el-form-item label="电影名" prop="name">
      <el-input v-model="dataForm.name" placeholder="电影名"></el-input>
    </el-form-item>
    <el-form-item label="" prop="score">
      <el-input v-model="dataForm.score" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="情节" prop="plot">
      <el-input v-model="dataForm.plot" placeholder="情节"></el-input>
    </el-form-item>
    <el-form-item label="" prop="avatar">
      <el-input v-model="dataForm.avatar" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="出演明星" prop="celebrities">
      <el-input v-model="dataForm.celebrities" placeholder="出演明星"></el-input>
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
          mid: 0,
          tags: '',
          date: '',
          stars: '',
          detail: '',
          name: '',
          score: '',
          plot: '',
          avatar: '',
          celebrities: ''
        },
        dataRule: {
          tags: [
            { required: true, message: '类型不能为空', trigger: 'blur' }
          ],
          date: [
            { required: true, message: '发布日期不能为空', trigger: 'blur' }
          ],
          stars: [
            { required: true, message: '出演明星不能为空', trigger: 'blur' }
          ],
          detail: [
            { required: true, message: '细节信息不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '电影名不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          plot: [
            { required: true, message: '情节不能为空', trigger: 'blur' }
          ],
          avatar: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          celebrities: [
            { required: true, message: '出演明星不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.mid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.mid) {
            this.$http({
              url: this.$http.adornUrl(`/movies/movie/info/${this.dataForm.mid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.tags = data.movie.tags
                this.dataForm.date = data.movie.date
                this.dataForm.stars = data.movie.stars
                this.dataForm.detail = data.movie.detail
                this.dataForm.name = data.movie.name
                this.dataForm.score = data.movie.score
                this.dataForm.plot = data.movie.plot
                this.dataForm.avatar = data.movie.avatar
                this.dataForm.celebrities = data.movie.celebrities
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
              url: this.$http.adornUrl(`/movies/movie/${!this.dataForm.mid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'mid': this.dataForm.mid || undefined,
                'tags': this.dataForm.tags,
                'date': this.dataForm.date,
                'stars': this.dataForm.stars,
                'detail': this.dataForm.detail,
                'name': this.dataForm.name,
                'score': this.dataForm.score,
                'plot': this.dataForm.plot,
                'avatar': this.dataForm.avatar,
                'celebrities': this.dataForm.celebrities
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
