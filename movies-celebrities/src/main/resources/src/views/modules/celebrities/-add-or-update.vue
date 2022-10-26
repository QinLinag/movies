<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="英文名字" prop="englishName">
      <el-input v-model="dataForm.englishName" placeholder="英文名字"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="gender">
      <el-input v-model="dataForm.gender" placeholder="性别"></el-input>
    </el-form-item>
    <el-form-item label="星座" prop="sign">
      <el-input v-model="dataForm.sign" placeholder="星座"></el-input>
    </el-form-item>
    <el-form-item label="出生日期" prop="birth">
      <el-input v-model="dataForm.birth" placeholder="出生日期"></el-input>
    </el-form-item>
    <el-form-item label="家乡" prop="hometown">
      <el-input v-model="dataForm.hometown" placeholder="家乡"></el-input>
    </el-form-item>
    <el-form-item label="工作" prop="job">
      <el-input v-model="dataForm.job" placeholder="工作"></el-input>
    </el-form-item>
    <el-form-item label="" prop="imdb">
      <el-input v-model="dataForm.imdb" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="个人经历" prop="brief">
      <el-input v-model="dataForm.brief" placeholder="个人经历"></el-input>
    </el-form-item>
    <el-form-item label="" prop="avatar">
      <el-input v-model="dataForm.avatar" placeholder=""></el-input>
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
          name: '',
          englishName: '',
          gender: '',
          sign: '',
          birth: '',
          hometown: '',
          job: '',
          imdb: '',
          brief: '',
          avatar: ''
        },
        dataRule: {
          name: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
          ],
          englishName: [
            { required: true, message: '英文名字不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '性别不能为空', trigger: 'blur' }
          ],
          sign: [
            { required: true, message: '星座不能为空', trigger: 'blur' }
          ],
          birth: [
            { required: true, message: '出生日期不能为空', trigger: 'blur' }
          ],
          hometown: [
            { required: true, message: '家乡不能为空', trigger: 'blur' }
          ],
          job: [
            { required: true, message: '工作不能为空', trigger: 'blur' }
          ],
          imdb: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          brief: [
            { required: true, message: '个人经历不能为空', trigger: 'blur' }
          ],
          avatar: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/celebrities//info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data..name
                this.dataForm.englishName = data..englishName
                this.dataForm.gender = data..gender
                this.dataForm.sign = data..sign
                this.dataForm.birth = data..birth
                this.dataForm.hometown = data..hometown
                this.dataForm.job = data..job
                this.dataForm.imdb = data..imdb
                this.dataForm.brief = data..brief
                this.dataForm.avatar = data..avatar
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
              url: this.$http.adornUrl(`/celebrities//${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'englishName': this.dataForm.englishName,
                'gender': this.dataForm.gender,
                'sign': this.dataForm.sign,
                'birth': this.dataForm.birth,
                'hometown': this.dataForm.hometown,
                'job': this.dataForm.job,
                'imdb': this.dataForm.imdb,
                'brief': this.dataForm.brief,
                'avatar': this.dataForm.avatar
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
