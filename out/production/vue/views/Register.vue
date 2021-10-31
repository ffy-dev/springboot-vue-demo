<template>
  <div style="width: 100%; height: 100vh; background-color: white; overflow: hidden"> <!--  :style="bg" 加背景图片-->
    <div style="width: 400px; margin: 100px auto">
      <div style="font-size: 30px; text-align: center; padding: 30px 0">欢迎注册</div>
      <el-form :rules="rules" ref="form" :model="form" size="normal">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user-solid" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-button style="width: 100%" type="primary" @click="Register">注册</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/util/request";

export default {
  name: "Register",
  data() {
    return {
      form: {
        username: '',
        password: '',
        confirm: '',
      },
      rules: {
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },
        ],
        confirm: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    Register() {

      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.confirm === this.form.password) {
            request.post("http://localhost:9090/user/register", this.form).then(res => {
              if (res.code === '0') {
                this.$message({
                  type: "success",
                  message: "注册成功",
                })
                this.$router.push("/login")
              } else {
                this.$message({
                  type: "error",
                  message: "注册失败：" + res.msg,
                })
              }
            })
          } else {
            this.$messageBox({
              type: "error",
              message: "两次输入密码不一致",
            })
          }
        }
      })

    },

  }
}
</script>

<style scoped>

</style>