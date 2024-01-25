<template>
  <div>
    <div style="margin-bottom: 20px;">
      <el-row style="text-align: right; margin-right: 100px;">
        <el-button type="primary"
                   icon="el-icon-refresh-left"
                   v-show="resetTableBtn"
                   @click="resetTable()">重置</el-button>
        <el-button type="primary"
                   icon="el-icon-search"
                   @click="openDialog('search')">搜索</el-button>
        <el-button type="primary"
                   @click="openDialog('add')">新增<i class="el-icon-edit el-icon--right"></i></el-button>
        <el-popover placement="top"
                    width="160"
                    v-model="DeleteVisible"
                    style="margin:0 10px">
          <p>确定删除所选数据吗？</p>
          <div style="text-align: right; margin: 0">
            <el-button size="mini"
                       type="text"
                       @click="DeleteVisible = false">取消</el-button>
            <el-button type="primary"
                       size="mini"
                       @click="usersDelete();DeleteVisible = false">确定</el-button>
          </div>
          <el-button slot="reference"
                     type="danger">批量删除<i class="el-icon-delete el-icon--right"></i></el-button>
        </el-popover>
        <el-upload class="upload-demo"
                   style="display:inline-block;margin-right:10px"
                   :action="this.$axios.defaults.baseURL + '/user/import'"
                   :show-file-list="false"
                   accept="xlsx"
                   :on-success="uploadSuccess">
          <el-button type="primary">导入<i class="el-icon-document-add el-icon--right"></i></el-button>
        </el-upload>
        <el-button type="primary"
                   @click="exportUserData()">导出<i class="el-icon-document el-icon--right"></i></el-button>
      </el-row>
    </div>
    <el-table height="70vh"
              :data="tableData"
              border
              stripe
              @selection-change="handleSelectionChange"
              v-loading="loading">
      <el-table-column type="selection"
                       width="55">
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left"
                   inline
                   class="demo-table-expand">
            <el-form-item label="ID">
              <span>{{ props.row.uId }}</span>
            </el-form-item>
            <el-form-item label="用户名">
              <span>{{ props.row.uName }}</span>
            </el-form-item>
            <el-form-item label="性别">
              <span>{{ formatterGender(props.row) }}</span>
            </el-form-item>
            <el-form-item label="手机号">
              <span>{{ props.row.uTelephone }}</span>
            </el-form-item>
            <el-form-item label="邮箱">
              <span>{{ props.row.uEmail }}</span>
            </el-form-item>
            <el-form-item label="出生日期">
              <span>{{ props.row.uBirth }}</span>
            </el-form-item>
            <el-form-item label="密码">
              <span>{{ props.row.uPassword }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="uId"
                       label="ID"
                       width="80">
      </el-table-column>
      <!-- <el-table-column prop="uAvatar"
                       label="头像">
        <el-avatar src="/img/avatar.png"></el-avatar>
      </el-table-column> -->
      <el-table-column prop="uName"
                       label="用户名">
      </el-table-column>
      <el-table-column prop="uGender"
                       label="性别"
                       :formatter="formatterGender"
                       width="80">
      </el-table-column>
      <el-table-column prop="uTelephone"
                       label="手机号">
      </el-table-column>
      <el-table-column prop="uEmail"
                       label="邮箱">
      </el-table-column>
      <el-table-column prop="uBirth"
                       label="出生日期">
      </el-table-column>
      <el-table-column prop="uPassword"
                       label="密码">
      </el-table-column>
      <el-table-column prop="uAddress"
                       label="地址">
        <template slot-scope="props">
          <el-tooltip placement="left"
                      effect="light">
            <div slot="content">{{ (props.row.uAddress) ? props.row.uAddress : "未填写"}}</div>
            <el-button>查看</el-button>
          </el-tooltip>
        </template>
      </el-table-column>

      <el-table-column :width="180"
                       fixed="right"
                       label="操作">
        <template slot-scope="scope">
          <el-button type="warning"
                     style="margin-right: 15px;"
                     @click="openDialog('edit',scope.row)">编辑</el-button>
          <el-popover placement="left"
                      width="160"
                      trigger="click"
                      :ref="`popover-${scope.$index}`">
            <p>确定删除吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini"
                         type="text"
                         @click="scope._self.$refs[`popover-${scope.$index}`].doClose()">取消</el-button>
              <el-button type="primary"
                         size="mini"
                         @click="userDelete(scope.row.uId);scope._self.$refs[`popover-${scope.$index}`].doClose()">确定</el-button>
            </div>
            <el-button slot="reference"
                       type="danger">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0;">
      <el-pagination background
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :page-sizes="[5, 10, 15, 20]"
                     :page-size="100"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </div>

    <!-- 这里是弹出表单模态框组件-->
    <el-dialog :title="user.formDialog.formTitle"
               :visible.sync="user.formDialog.dialogFormVisible"
               :width="(user.formDialog.formWidth==null) ? '30%' : user.formDialog.formWidth"
               :fullscreen="(user.formDialog.fullScreen==null) ? 'false' : user.formDialog.fullScreen"
               top="5vh"
               @close="handleDialogClose">
      <div class="el-dialog-div">
        <el-form :inline="true"
                 :model="form"
                 class="demo-form-inline"
                 label-position='left'
                 label-width='80px'>
          <!--查询字段初始化-->
          <el-form-item v-show="item.show"
                        v-for="(item,index) in user.formDialog.formItem"
                        :key="index"
                        :label="item.label">
            <!--文本字段-->
            <el-input v-show="item.show"
                      v-if="item.type=='text'"
                      v-model='form[item.name]'
                      :placeholder="item.placeholder"
                      :readonly="item.readonly"
                      clearable></el-input>
            <!--多行文本字段-->
            <el-input v-show="item.show"
                      v-if="item.type=='textarea'"
                      :type="item.type"
                      :placeholder="item.placeholder"
                      v-model='form[item.name]'
                      :readonly="item.readonly"
                      clearable></el-input>
            <!--下拉选择框-->
            <el-select v-show="item.show"
                       v-if="item.type=='option'"
                       v-model='form[item.name]'
                       :readonly="item.readonly">
              <el-option v-for="(item,index) in item.options"
                         :key="index"
                         :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
            <!--日期-->
            <el-date-picker v-show="item.show"
                            v-if="item.type=='date'"
                            v-model="form[item.name]"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                            type="date"
                            :readonly="item.readonly"
                            placeholder="选择日期"></el-date-picker>
            <!--日期段选择框-->
            <el-date-picker v-show="item.show"
                            v-if="item.type=='daterange'"
                            v-model="form[item.name]"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                            type="daterange"
                            :readonly="item.readonly"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期">
            </el-date-picker>
            <div v-show="item.show"
                 v-if="item.type=='address'">
              <el-cascader v-model="selectedOptions"
                           size="large"
                           :options="pcaTextArr">
              </el-cascader><el-input v-model="address"
                        placeholder="请输入详细地址"
                        clearable></el-input>
            </div>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="user.formDialog.dialogFormVisible=false;">取 消</el-button>
        <el-button type="primary"
                   @click="handleFormBtn();user.formDialog.dialogFormVisible=false;">{{ user.formDialog.formBtnValue }}</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { pcaTextArr } from "element-china-area-data";

import md from "@/assets/js/ManagerData.js";
export default {
  name: "UserView",
  data() {
    return {
      ...md.data(),
      DeleteVisible: false, //批量删除Popover显示
      address: "",
      pcaTextArr,
      selectedOptions: [],
    };
  },
  methods: {
    //表单数据重置
    resetTable() {
      this.pageNum = 1;
      this.form = {};
      this.userData("reset");
      this.resetTableBtn = false;
    },
    //表单选中触发事件
    handleSelectionChange(val) {
      const list = [];
      val.forEach((item) => {
        list.push(item.uId);
      });
      this.multipleSelection = list;
    },
    ...md.methods,
    //分页按钮
    handleSizeChange(val) {
      this.pageSize = val;
      this.userData();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.userData();
    },
    //表单数据格式化(性别)
    formatterGender(row) {
      switch (row.uGender) {
        case "1":
          return "男";
        case "2":
          return "女";
        default:
          return "未填写";
      }
    },
    //地址数据格式化
    addressDataBind() {
      let ad = "";
      for (var i = 0; i < this.selectedOptions.length; i++) {
        ad = ad + this.selectedOptions[i] + "-";
      }
      ad = ad + this.address;
      this.form.uAddress = ad;
    },
    //动态Dialog关闭回调
    handleDialogClose() {
      this.form = {};
      this.selectedOptions = [];
      this.address = "";
    },
    //动态显示Dialog
    openDialog(btnCode, scope) {
      this.user.formDialog.dialogFormVisible = true;
      if (btnCode == "search") {
        this.form = {};
        this.user.formDialog.formTitle = "用户搜索";
        this.user.formDialog.formBtnValue = "搜索";
        this.handleFormBtn = function () {
          this.userData("search");
          this.resetTableBtn = true;
        };
      } else if (btnCode == "add") {
        this.form = {};
        this.user.formDialog.formTitle = "用户新增";
        this.user.formDialog.formBtnValue = "新增";
        this.handleFormBtn = function () {
          this.userAdd();
          this.form = {};
        };
      } else if (btnCode == "edit") {
        this.form = scope;
        if (scope.uAddress) {
          let arr = scope.uAddress.split("-");
          this.address = arr[3];
          arr.pop();
          this.selectedOptions = arr;
        }
        this.user.formDialog.formTitle = "用户编辑";
        this.user.formDialog.formBtnValue = "修改";
        this.handleFormBtn = function () {
          this.userUpdate();
          this.form = {};
        };
      }
    },
    // 用户数据查询
    userData(code) {
      this.addressDataBind();
      const self = this;
      if (code == "reset") {
        self.$axios
          .get("/user/info", {
            params: {
              pageNum: self.pageNum,
              pageSize: self.pageSize,
            },
          })
          .then((res) => {
            self.tableData = res.records;
            self.total = res.total;
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (code == "search") {
        self.$axios
          .get("/user/info", {
            params: {
              pageNum: 1,
              pageSize: self.pageSize,
              uId: self.form.uId,
              uName: self.form.uName,
              uPassword: self.form.uPassword,
              uEmail: self.form.uEmail,
              uTelephone: self.form.uTelephone,
              uBirth: self.form.uBirth,
              uGender: self.form.uGender,
              uAddress: self.form.uAddress,
            },
          })
          .then((res) => {
            this.$notify({
              title: "搜索到" + res.total + "条数据",
              type: "success",
            });
            self.tableData = res.records;
            self.total = res.total;
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        self.$axios
          .get("/user/info", {
            params: {
              pageNum: self.pageNum,
              pageSize: self.pageSize,
              uId: self.form.uId,
              uName: self.form.uName,
              uPassword: self.form.uPassword,
              uEmail: self.form.uEmail,
              uTelephone: self.form.uTelephone,
              uBirth: self.form.uBirth,
              uGender: self.form.uGender,
              uAddress: self.form.uAddress,
            },
          })
          .then((res) => {
            self.tableData = res.records;
            self.total = res.total;
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },

    // 用户数据新增
    userAdd() {
      this.addressDataBind();
      const self = this;
      self.$axios
        .post("/user/add", {
          uId: self.form.uId,
          uName: self.form.uName,
          uPassword: self.form.uPassword,
          uEmail: self.form.uEmail,
          uTelephone: self.form.uTelephone,
          uBirth: self.form.uBirth,
          uGender: self.form.uGender,
          uAddress: self.form.uAddress,
          uClass: 0,
        })
        .then((res) => {
          this.userData("reset");
          switch (res.code) {
            case "200":
              this.$notify({
                title: res.msg,
                type: "success",
              });
              break;
            case "-1":
              this.$notify({
                title: res.msg,
                type: "error",
              });
              break;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 用户数据更新
    userUpdate() {
      this.addressDataBind();
      const self = this;
      self.$axios
        .post("/user/update", {
          uId: self.form.uId,
          uName: self.form.uName,
          uPassword: self.form.uPassword,
          uEmail: self.form.uEmail,
          uTelephone: self.form.uTelephone,
          uBirth: self.form.uBirth,
          uGender: self.form.uGender,
          uAddress: self.form.uAddress,
          uClass: 0,
        })
        .then((res) => {
          this.userData("reset");
          switch (res.code) {
            case "200":
              this.$notify({
                title: res.msg,
                type: "success",
              });
              break;
            case "-1":
              this.$notify({
                title: res.msg,
                type: "error",
              });
              break;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //用户数据删除
    userDelete(id) {
      const self = this;
      self.$axios
        .get("/user/delete/" + id)
        .then((res) => {
          this.userData("reset");
          switch (res.code) {
            case "200":
              this.$notify({
                title: res.msg,
                type: "success",
              });
              break;
            case "-1":
              this.$notify({
                title: res.msg,
                type: "error",
              });
              break;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 用户数据批量删除
    usersDelete() {
      const self = this;
      self.$axios
        .post("/user/delete", this.multipleSelection)
        .then((res) => {
          this.userData("reset");
          switch (res.code) {
            case "200":
              this.$notify({
                title: res.msg,
                type: "success",
              });
              break;
            case "-1":
              this.$notify({
                title: res.msg,
                type: "error",
              });
              break;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 导入用户数据
    uploadSuccess(response) {
      this.$notify({
        title: "成功导入 " + response.data + " 条数据",
        type: "success",
      });
      this.userData("reset");
    },

    // 导出用户数据
    exportUserData() {
      const self = this;
      window.open(self.$axios.defaults.baseURL + "/user/export");
    },
  },
  created() {
    this.userData(); //用户数据加载
    this.loading = false;
  },
};
</script>

<style>
/* 表格展开栏样式 */
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand {
  padding-left: 30px;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>