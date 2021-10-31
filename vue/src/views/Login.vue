<template>
  <div style="width: 100%; height: 100vh; background-color: white; overflow: hidden"> <!--  :style="bg" 加背景图片-->
    <div style="width: 400px; margin: 100px auto">
      <div style="font-size: 30px; text-align: center; padding: 30px 0">欢迎登录</div>
      <el-form ref="form" :model="form" size="normal">
        <el-form-item>
          <el-input prefix-icon="el-icon-user-solid" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/util/request";

export default {
  name: "Login",
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
    }
  },
  methods: {
    login() {
      request.post("http://localhost:9090/user/login" , this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "登录成功",
          })
          this.$router.push("/")
        } else {
          this.$message({
            type: "error",
            message: "登录失败："+res.msg,
          })
        }
      })
    },
  }
}
</script>

<style scoped>

</style>