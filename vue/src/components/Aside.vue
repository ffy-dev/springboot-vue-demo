<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        default-active="user"
        :default-openeds="openeds"
        class="el-menu-vertical-demo"
    >
      <!--              @open="handleOpen"-->
      <!--              @close="handleClose"-->

      <el-sub-menu index="1">
        <template >
<!--        <template #title>-->
<!--          <i class="el-icon-location"></i>-->
          <span>系统管理</span>
        </template>
        <el-menu-item index="user" :route="{path: '/'}">用户管理</el-menu-item>
      </el-sub-menu>

      <el-menu-item index="data">
        <i class="el-icon-menu"></i>
        <span>数据管理</span>
      </el-menu-item>
<!--      <el-menu-item index="4">-->
<!--        <i class="el-icon-setting"></i>-->
<!--        <span>Navigator Four</span>-->
<!--      </el-menu-item>-->
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return{
      openeds: ['1'],
    }
  }
}
</script>

<style scoped>

</style>