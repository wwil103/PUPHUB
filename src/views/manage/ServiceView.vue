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
                       @click="servicesDelete();DeleteVisible = false">确定</el-button>
          </div>
          <el-button slot="reference"
                     type="danger">批量删除<i class="el-icon-delete el-icon--right"></i></el-button>
        </el-popover>
        <el-upload class="upload-demo"
                   style="display:inline-block;margin-right:10px"
                   :action="$axios.defaults.baseURL + '/service/import'"
                   :show-file-list="false"
                   accept="xlsx"
                   :on-success="uploadSuccess">
          <el-button type="primary">导入<i class="el-icon-document-add el-icon--right"></i></el-button>
        </el-upload>
        <el-button type="primary"
                   @click="exportServiceData()">导出<i class="el-icon-document el-icon--right"></i></el-button>
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
          <el-descriptions class="margin-top"
                           :column="3"
                           :size="size"
                           border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-collection-tag"></i>
                ID
              </template>
              {{ props.row.sId }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-service "></i>
                服务名称
              </template>
              {{ props.row.sName }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-coin"></i>
                服务价格
              </template>
              <el-tag size="small">{{ formatterPrice(props.row) }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                服务简介
              </template>
              {{ props.row.sIntro }}
            </el-descriptions-item>

          </el-descriptions>
        </template>
      </el-table-column>
      <el-table-column prop="sId"
                       label="ID"
                       width="80">
      </el-table-column>
      <el-table-column prop="sName"
                       label="服务名称">
      </el-table-column>
      <el-table-column prop="sIntro"
                       label="服务简介">
      </el-table-column>
      <el-table-column prop="sPrice"
                       :formatter="formatterPrice"
                       label="服务价格">
      </el-table-column>
      <el-table-column prop="sPoster"
                       label="服务海报">
        <template slot-scope="props">
          <div class="demo-image__preview">
            <el-image style="width: 100px; height: 50px"
                      :src="props.row.sPoster"
                      :preview-src-list="[props.row.sPoster]">
            </el-image>
          </div>
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
                         @click="serviceDelete(scope.row.sId);scope._self.$refs[`popover-${scope.$index}`].doClose()">确定</el-button>
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
    <el-dialog :title="service.formDialog.formTitle"
               :visible.sync="service.formDialog.dialogFormVisible"
               :width="(service.formDialog.formWidth==null) ? '30%' : service.formDialog.formWidth"
               :fullscreen="(service.formDialog.fullScreen==null) ? 'false' : service.formDialog.fullScreen"
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
                        v-for="(item,index) in service.formDialog.formItem"
                        :key="index"
                        :label="item.label">
            <!--文本字段-->
            <el-input v-show="item.show"
                      v-if="item.type=='text'"
                      v-model='form[item.name]'
                      :placeholder="item.placeholder"
                      :readonly="item.readonly"
                      clearable></el-input>
            <!--数字字段-->
            <el-input-number v-show="item.show"
                             v-if="item.type=='number'"
                             v-model="form[item.name]"
                             :min="item.min"
                             :max="item.max"
                             :placeholder="item.placeholder"
                             controls-position="right"
                             :disabled="item.disabled"></el-input-number>
            <!--多行文本字段-->
            <el-input v-show="item.show"
                      v-if="item.type=='textarea'"
                      :type="item.type"
                      :placeholder="item.placeholder"
                      :rows="item.row"
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
            <!--海报上传-->
            <el-upload v-show="item.show"
                       v-if="item.type=='avatar'"
                       :action="$axios.defaults.baseURL + '/service/upload'"
                       list-type="picture-card"
                       :on-remove="handlePictureCardRemove"
                       :on-success="handlePictureCardSuccess"
                       ref="avatarUpload">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="service.formDialog.dialogFormVisible=false;">取 消</el-button>
        <el-button type="primary"
                   @click="handleFormBtn();service.formDialog.dialogFormVisible=false;">{{ service.formDialog.formBtnValue }}</el-button>
      </span>
    </el-dialog>
  </div>
</template>
  
  <script>
import md from "@/assets/js/ManagerData.js";
export default {
  name: "ServiceView",
  data() {
    return {
      ...md.data(),
      DeleteVisible: false, //批量删除Popover显示
    };
  },
  methods: {
    //表单数据重置
    resetTable() {
      this.form = {};
      this.serviceData();
      this.resetTableBtn = false;
    },
    //表单选中触发事件
    handleSelectionChange(val) {
      const list = [];
      val.forEach((item) => {
        list.push(item.sId);
      });
      this.multipleSelection = list;
    },
    ...md.methods,
    //分页按钮
    handleSizeChange(val) {
      this.pageSize = val;
      this.serviceData();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.serviceData();
    },

    //表单数据格式化(价格)
    formatterPrice(row) {
      return row.sPrice + "￥ 起";
    },

    //海报上传移除
    handlePictureCardRemove() {
      document
        .getElementsByClassName("el-upload--picture-card")[0]
        .style.setProperty("display", "inline-block"); //显示上传窗口
    },
    handlePictureCardSuccess(response) {
      document
        .getElementsByClassName("el-upload--picture-card")[0]
        .style.setProperty("display", "none"); //隐藏上传窗口
      if (response.code === "200") {
        this.form.sPoster = response.data;
      }
    },

    //动态显示Dialog
    openDialog(btnCode, scope) {
      const self = this;
      this.service.formDialog.formItem.forEach(function (item, index) {
        if (item.type === "avatar") {
          self.service.formDialog.formItem[index].show = true;
        }
      });
      this.service.formDialog.dialogFormVisible = true;

      if (btnCode == "search") {
        this.service.formDialog.formItem.forEach(function (item, index) {
          if (item.type === "avatar") {
            self.service.formDialog.formItem[index].show = false;
          }
        });
        this.form = {};
        this.service.formDialog.formTitle = "服务搜索";
        this.service.formDialog.formBtnValue = "搜索";
        this.handleFormBtn = function () {
          this.serviceData("search");
          this.resetTableBtn = true;
        };
      } else if (btnCode == "add") {
        this.form = {};
        this.service.formDialog.formTitle = "服务新增";
        this.service.formDialog.formBtnValue = "新增";
        this.handleFormBtn = function () {
          this.serviceAdd();
          this.form = {};
        };
      } else if (btnCode == "edit") {
        this.form = scope;
        this.service.formDialog.formTitle = "服务编辑";
        this.service.formDialog.formBtnValue = "修改";
        this.handleFormBtn = function () {
          this.serviceUpdate();
          this.form = {};
        };
      }
    },
    //动态Dialog关闭回调
    handleDialogClose() {
      this.$nextTick(() => {
        this.$refs.avatarUpload[0].clearFiles(); //清除upload文件
        this.handlePictureCardRemove(); //恢复upload组件
      });
      this.form = {};
    },

    // 服务数据查询
    serviceData(code) {
      const self = this;
      if (code == "reset") {
        self.$axios
          .get("/service/info", {
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
          .get("/service/info", {
            params: {
              pageNum: 1,
              pageSize: self.pageSize,
              sId: self.form.sId,
              sName: self.form.sName,
              sIntro: self.form.sIntro,
              sPrice: self.form.sPrice,
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
          .get("/service/info", {
            params: {
              pageNum: self.pageNum,
              pageSize: self.pageSize,
              sId: self.form.sId,
              sName: self.form.sName,
              sIntro: self.form.sIntro,
              sPrice: self.form.sPrice,
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

    // 服务数据新增
    serviceAdd() {
      const self = this;
      self.$axios
        .post("/service/add", {
          sId: self.form.sId,
          sName: self.form.sName,
          sIntro: self.form.sIntro,
          sPrice: self.form.sPrice,
          sPoster: self.form.sPoster,
        })
        .then((res) => {
          this.serviceData("reset");
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

    // 服务数据更新
    serviceUpdate() {
      const self = this;
      self.$axios
        .post("/service/update", {
          sId: self.form.sId,
          sName: self.form.sName,
          sIntro: self.form.sIntro,
          sPrice: self.form.sPrice,
          sPoster: self.form.sPoster,
        })
        .then((res) => {
          this.serviceData("reset");
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
    //服务数据删除
    serviceDelete(id) {
      const self = this;
      self.$axios
        .get("/service/delete/" + id)
        .then((res) => {
          this.serviceData("reset");
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

    // 服务数据批量删除
    servicesDelete() {
      const self = this;
      self.$axios
        .post("/service/delete", this.multipleSelection)
        .then((res) => {
          this.serviceData("reset");
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

    // 导入服务数据
    uploadSuccess(response) {
      this.$notify({
        title: "成功导入 " + response.data + " 条数据",
        type: "success",
      });
      this.serviceData("reset");
    },

    // 导出服务数据
    exportServiceData() {
      const self = this;
      window.open(self.$axios.defaults.baseURL + "/service/export");
    },
  },
  created() {
    this.serviceData(); //服务数据加载
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