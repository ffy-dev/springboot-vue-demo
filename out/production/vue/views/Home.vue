<template>
  <div style="padding:10px">
    <!--    功能区域-->
    <div>
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <div>
      <el-input v-model="search" @keyup.enter="load" placeholder="请输入关键字" style="width: 20%"/>
      <el-button type="primary" style="margin: 10px" @click="load">搜索</el-button>
    </div>
    <!--    表格-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="id" sortable label="ID" width="180"/>
      <el-table-column prop="username" label="用户名" width="180"/>
      <el-table-column prop="nickname" label="昵称" width="180"/>
      <el-table-column prop="age" label="年龄" width="70"/>
      <el-table-column prop="sex" label="性别" width="70"/>
      <el-table-column prop="address" label="地址"/>
      <el-table-column label="Operations" width="120">
        <template #default="scope">
          <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row)">
            <template #reference>
              <el-button type="text">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          :current-page="pageNum"
          :page-sizes="pageSizes"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    <div>
      <el-dialog v-model="dialogFormVisible" title="用户信息">
        <el-form :model="form" label-position="right" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model.number="form.age" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男"/>
            <el-radio v-model="form.sex" label="女"/>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" type="textarea"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="saveOrUpdate"
        >确认</el-button
        >
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import request from "@/util/request";

export default {
  name: 'Home',
  components: {},
  data() {
    return {
      //表单
      dialogFormVisible: false,//弹出的表单对话框是否打开
      form: {},//表单数据
      search: '',//搜索框的值
      tableData: [],
      formLabelWidth: '',
      //分页栏属性
      pageNum: 1,//当前页码
      pageSize: 5,//当前是几条一页
      total: 0,//查出了几条数据
      pageSizes: [5, 10, 15, 20],
    }
  },
  created() {
    this.load();
  },
  methods: {
    /**
     * 删除用户
     */
    handleDelete: function (row) {
      request.delete("http://localhost:9090/user/" + row.id).then(res => {
        if (res.code === '0') {
          this.$messageBox({
            type: "success",
            message: "删除成功",
          })
        } else {
          this.$messageBox({
            type: "error",
            message: res.msg,
          })
        }
      })
      this.load();
    },
    /**
     * 增
     */
    add: function () {
      this.dialogFormVisible = true;
      this.form = {};
    },
    saveOrUpdate: function () {
      if (this.form.id) {
        request.put("http://localhost:9090/user", this.form).then(res => {
          if (res.code === '0') {
            this.$messageBox({
              type: "success",
              message: "更新成功",
            })
          } else {
            this.$messageBox({
              type: "error",
              message: res.msg,
            })
          }
          // console.log(res);
        })
      } else {
        request.post("http://localhost:9090/user", this.form).then(res => {
          if (res.code === '0') {
            this.$messageBox({
              type: "success",
              message: "新增成功",
            })
          } else {
            this.$messageBox({
              type: "error",
              message: res.msg,
            })
          }
          // console.log(res);
        })
      }
      this.load();
      this.dialogFormVisible = false
    },
    /**
     * 查询
     */
    load: function () {
      request.get("http://localhost:9090/user",
          {
            params: {
              pageNum: this.pageNum,
              pageSize: this.pageSize,
              search: this.search,
            }
          }).then(res => {
        this.tableData = res.data.records;
        this.total = res.data.total;
        this.search = '';
        console.log(res)
      })
    },
    handleEdit: function (row) {
      this.dialogFormVisible = true;
      this.form = JSON.parse(JSON.stringify(row));
    },
    handleSizeChange: function (pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange: function (currentPage) {
      this.pageNum = currentPage;
      this.load()
    },
  }

}
</script>
