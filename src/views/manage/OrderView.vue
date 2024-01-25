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
                       @click="ordersDelete();DeleteVisible = false">确定</el-button>
          </div>
          <el-button slot="reference"
                     type="danger">批量删除<i class="el-icon-delete el-icon--right"></i></el-button>
        </el-popover>
        <el-upload class="upload-demo"
                   style="display:inline-block;margin-right:10px"
                   :action="this.$axios.defaults.baseURL + '/order/import'"
                   :show-file-list="false"
                   accept="xlsx"
                   :on-success="uploadSuccess">
          <el-button type="primary">导入<i class="el-icon-document-add el-icon--right"></i></el-button>
        </el-upload>
        <el-button type="primary"
                   @click="exportOrderData()">导出<i class="el-icon-document el-icon--right"></i></el-button>
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
            <el-form-item label="订单编号">
              <span>{{ props.row.oId }}</span>
            </el-form-item>
            <el-form-item label="用户ID">
              <span>{{ props.row.oUid }}</span>
            </el-form-item>
            <el-form-item label="服务名称">
              <span>{{ formatterService(props.row) }}</span>
            </el-form-item>
            <el-form-item label="服务日期">
              <span>{{ props.row.oDate }}</span>
            </el-form-item>
            <el-form-item label="时间段">
              <span>{{ formatterTime(props.row) }}</span>
            </el-form-item>
            <el-form-item label="下单时间">
              <span>{{ props.row.oOrderTime }}</span>
            </el-form-item>
            <el-form-item label="狗狗年龄">
              <span>{{ formatterAge(props.row) }}</span>
            </el-form-item>
            <el-form-item label="狗狗大小">
              <span>{{ formatterSize(props.row) }}</span>
            </el-form-item>
            <el-form-item label="地址">
              <span>{{ props.row.oAddress }}</span>
            </el-form-item>
            <el-form-item label="联系方式">
              <span>{{ props.row.oContact }}</span>
            </el-form-item>
            <el-form-item label="订单状态">
              <el-tag :type="formatStatusTag(props.row.oStatus)">{{ formatterStatus(props.row) }}</el-tag>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="oId"
                       label="订单编号"
                       width="80">
      </el-table-column>
      <el-table-column prop="oUid"
                       label="用户ID"
                       width="80">
      </el-table-column>
      <!-- <el-table-column prop="uAvatar"
                       label="头像">
        <el-avatar src="/img/avatar.png"></el-avatar>
      </el-table-column> -->
      <el-table-column prop="oService"
                       label="服务名称"
                       width="100"
                       :formatter="formatterService">
      </el-table-column>
      <el-table-column prop="oDate"
                       label="服务日期"
                       width="120"
                       sortable>
      </el-table-column>
      <el-table-column prop="oTime"
                       label="时间段"
                       width="80"
                       :formatter="formatterTime">
      </el-table-column>
      <el-table-column prop="oOrderTime"
                       label="下单时间"
                       width="180"
                       sortable>
      </el-table-column>
      <el-table-column prop="oAge"
                       label="狗狗年龄"
                       width="90"
                       :formatter="formatterAge">
      </el-table-column>
      <el-table-column prop="oSize"
                       label="狗狗大小"
                       width="90"
                       :formatter="formatterSize">
      </el-table-column>
      <el-table-column prop="oAddress"
                       width="100"
                       label="地址">
        <template slot-scope="props">
          <el-tooltip placement="left"
                      effect="light">
            <div slot="content">{{ (props.row.oAddress) ? props.row.oAddress : "未填写"}}</div>
            <el-button>查看</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="oContact"
                       width="180"
                       label="联系方式">
      </el-table-column>
      <el-table-column prop="oStatus"
                       label="订单状态"
                       width="120"
                       sortable>
        <template slot-scope="props">
          <el-tag :type="formatStatusTag(props.row.oStatus)">{{ formatterStatus(props.row) }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column :width="300"
                       fixed="right"
                       label="操作">
        <template slot-scope="scope">
          <el-popover placement="left"
                      width="160"
                      trigger="click"
                      style="margin-right: 15px;"
                      :ref="`popover-${scope.$index}`">
            <p>确定更新吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini"
                         type="text"
                         @click="scope._self.$refs[`popover-${scope.$index}`].doClose()">取消</el-button>
              <el-button type="primary"
                         size="mini"
                         @click="orderStatusUpdate(scope.row);scope._self.$refs[`popover-${scope.$index}`].doClose()">确定</el-button>
            </div>
            <el-button :disabled="scope.row.oStatus == '2'"
                       slot="reference"
                       type="success">状态更新</el-button>
          </el-popover>
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
                         @click="orderDelete(scope.row.oId);scope._self.$refs[`popover-${scope.$index}`].doClose()">确定</el-button>
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
    <el-dialog :title="order.formDialog.formTitle"
               :visible.sync="order.formDialog.dialogFormVisible"
               :width="(order.formDialog.formWidth==null) ? '30%' : order.formDialog.formWidth"
               :fullscreen="(order.formDialog.fullScreen==null) ? 'false' : order.formDialog.fullScreen"
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
                        v-for="(item,index) in order.formDialog.formItem"
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
              <el-option v-for="(item,index) in (item.options.constructor === String) ? service : item.options"
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
        <el-button @click="order.formDialog.dialogFormVisible=false;">取 消</el-button>
        <el-button type="primary"
                   @click="handleFormBtn();order.formDialog.dialogFormVisible=false;">{{ order.formDialog.formBtnValue }}</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { pcaTextArr } from "element-china-area-data";

import md from "@/assets/js/ManagerData.js";
export default {
  name: "OrderView",
  data() {
    return {
      ...md.data(),
      DeleteVisible: false, //批量删除Popover显示
      address: "",
      service: [],
      pcaTextArr,
      selectedOptions: [],
    };
  },
  methods: {
    //表单数据重置
    resetTable() {
      this.pageNum = 1;
      this.form = {};
      this.orderData("reset");
      this.resetTableBtn = false;
    },
    //表单选中触发事件
    handleSelectionChange(val) {
      const list = [];
      val.forEach((item) => {
        list.push(item.oId);
      });
      this.multipleSelection = list;
    },
    ...md.methods,
    //分页按钮
    handleSizeChange(val) {
      this.pageSize = val;
      this.orderData();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.orderData();
    },
    //表单数据格式化(服务)
    formatterService(row) {
      var a = "";
      this.service.forEach((element) => {
        if (row.oService == element.value) a = element.label;
      });
      return a;
    },

    //表单数据格式化(时间)
    formatterTime(row) {
      switch (row.oTime) {
        case "1":
          return "早上";
        case "2":
          return "下午";
        case "3":
          return "晚上";
        default:
          return "未填写";
      }
    },

    //表单数据格式化(狗狗年龄)
    formatterAge(row) {
      switch (row.oAge) {
        case "1":
          return "幼犬";
        case "2":
          return "成年犬";
        case "3":
          return "老年犬";
        default:
          return "未填写";
      }
    },

    //表单数据格式化(狗狗大小)
    formatterSize(row) {
      switch (row.oSize) {
        case "1":
          return "小型犬";
        case "2":
          return "中型犬";
        case "3":
          return "大型犬";
        default:
          return "未填写";
      }
    },

    //表单数据格式化(订单状态)
    formatterStatus(row) {
      switch (row.oStatus) {
        case "0":
          return "服务待开始";
        case "1":
          return "服务进行中";
        case "2":
          return "服务已完成";
        default:
          return "未填写";
      }
    },

    formatStatusTag(row) {
      switch (row) {
        case "0":
          return "";
        case "1":
          return "warning";
        case "2":
          return "success";
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
      this.form.oAddress = ad;
    },
    //动态Dialog关闭回调
    handleDialogClose() {
      this.form = {};
      this.selectedOptions = [];
      this.address = "";
    },
    //动态显示Dialog
    openDialog(btnCode, scope) {
      this.order.formDialog.dialogFormVisible = true;
      if (btnCode == "search") {
        this.form = {};
        this.order.formDialog.formTitle = "订单搜索";
        this.order.formDialog.formBtnValue = "搜索";
        this.handleFormBtn = function () {
          this.orderData("search");
          this.resetTableBtn = true;
        };
      } else if (btnCode == "add") {
        this.form = {};
        this.order.formDialog.formTitle = "订单新增";
        this.order.formDialog.formBtnValue = "新增";
        this.handleFormBtn = function () {
          this.orderAdd();
          this.form = {};
        };
      } else if (btnCode == "edit") {
        this.form = scope;
        if (scope.oAddress) {
          let arr = scope.oAddress.split("-");
          this.address = arr[3];
          arr.pop();
          this.selectedOptions = arr;
        }
        this.order.formDialog.formTitle = "订单编辑";
        this.order.formDialog.formBtnValue = "修改";
        this.handleFormBtn = function () {
          this.orderUpdate();
          this.form = {};
        };
      }
    },
    //服务数据查询
    serviceData() {
      const self = this;
      self.$axios
        .get("/service/info/all")
        .then((res) => {
          res.data.forEach((element) => {
            let arr = {};
            arr.label = element.sName;
            arr.value = element.sId;
            self.service.push(arr);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 订单数据查询
    orderData(code) {
      this.addressDataBind();
      const self = this;
      if (code == "reset") {
        self.$axios
          .get("/order/info", {
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
          .get("/order/info", {
            params: {
              pageNum: 1,
              pageSize: self.pageSize,
              oId: self.form.oId,
              oUid: self.form.oUid,
              oService: self.form.oService,
              oDate: self.form.oDate,
              oTime: self.form.oTime,
              oAge: self.form.oAge,
              oSize: self.form.oSize,
              oAddress: self.form.oAddress,
              oContact: self.form.oContact,
              oStatus: self.form.oStatus,
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
          .get("/order/info", {
            params: {
              pageNum: self.pageNum,
              pageSize: self.pageSize,
              oId: self.form.oId,
              oUid: self.form.oUid,
              oService: self.form.oService,
              oDate: self.form.oDate,
              oTime: self.form.oTime,
              oAge: self.form.oAge,
              oSize: self.form.oSize,
              oAddress: self.form.oAddress,
              oContact: self.form.oContact,
              oStatus: self.form.oStatus,
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

    // 订单数据更新
    orderUpdate() {
      this.addressDataBind();
      const self = this;
      self.$axios
        .post("/order/update", {
          oId: self.form.oId,
          oUid: self.form.oUid,
          oService: self.form.oService,
          oDate: self.form.oDate,
          oTime: self.form.oTime,
          oAge: self.form.oAge,
          oSize: self.form.oSize,
          oAddress: self.form.oAddress,
          oContact: self.form.oContact,
          oStatus: self.form.oStatus,
        })
        .then((res) => {
          this.orderData("reset");
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

    // 订单状态更新
    orderStatusUpdate(row) {
      const self = this;
      self.$axios
        .post("/order/update", {
          oId: row.oId,
          oStatus: parseInt(row.oStatus) + 1,
        })
        .then((res) => {
          this.orderData("reset");
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

    //订单数据删除
    orderDelete(id) {
      const self = this;
      self.$axios
        .get("/order/delete/" + id)
        .then((res) => {
          this.orderData("reset");
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

    // 订单数据批量删除
    ordersDelete() {
      const self = this;
      self.$axios
        .post("/order/delete", this.multipleSelection)
        .then((res) => {
          this.orderData("reset");
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

    // 导入订单数据
    uploadSuccess(response) {
      this.$notify({
        title: "成功导入 " + response.data + " 条数据",
        type: "success",
      });
      this.orderData("reset");
    },

    // 导出订单数据
    exportOrderData() {
      const self = this;
      window.open(self.$axios.defaults.baseURL + "/order/export");
    },
  },
  created() {
    this.serviceData(); //服务数据加载
    this.orderData(); //订单数据加载
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