<template>
  <div class="PersonEdit">
    <div class="top-title">个人信息</div>
    <el-divider><i class="el-icon-s-opportunity"></i></el-divider>
    <p class="top-tips">PUBHUB会收集你的姓名，地址，联系方式，邮箱等其他个人信息，以及你的宠物信息，用于我们的服务。所有的个人信息仅供PUBHUB内部使用，承诺绝不对外透露。</p>

    <div class="pe-main">
      <el-form ref="form"
               :model="form"
               label-width="80px">
        <el-form-item label="电子邮箱">
          <el-input v-model="form.email"
                    placeholder="请输入电子邮箱"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.name"
                    placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model='form.gender'>
            <el-option v-for="(item,index) in gender"
                       :key="index"
                       :label="item.label"
                       :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input v-model="form.telephone"
                    placeholder="请输入电话号码"></el-input>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-date-picker v-model="form.birth"
                          format="yyyy-MM-dd"
                          value-format="yyyy-MM-dd"
                          type="date"
                          placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="地址">
          <el-cascader v-model="addressList"
                       size="large"
                       :options="pcaTextArr">
          </el-cascader><el-input style="margin-top:5px;display: inline-block;"
                    v-model="address"
                    placeholder="请输入详细地址"
                    clearable></el-input>
        </el-form-item>
        <!-- <div class="form-tips"> 您的SL个人主页即将完成！我们非常重视你能花时间读完我们下面的条款与条件。继续并提交您的账户信息，表明您同意这些条款。如果您对于这些条款有疑问，请邮件 <strong>info@spareleash.com.cn </strong> 联系我们。
        </div>
        <el-checkbox v-model="checked">我同意此PUBHUB条款与条件</el-checkbox> -->
        <el-form-item>
          <el-button class="submitBtn"
                     @click="userUpdate">保存修改</el-button>
          <el-button class="submitBtn"
                     @click="dialogPasswordForm = true">修改密码</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 修改密码 -->
    <el-dialog @close="handleDialogClose"
               width="40%"
               title="修改密码"
               :visible.sync="dialogPasswordForm">
      <el-form>
        <el-form-item label="旧密码">
          <el-input placeholder="请输入密码"
                    v-model="pwdForm.oldPassword"
                    show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input placeholder="请输入密码"
                    v-model="pwdForm.newPassword"
                    show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogPasswordForm = false">取 消</el-button>
        <el-button type="primary"
                   @click="passUpdate()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { pcaTextArr } from "element-china-area-data";
export default {
  data() {
    return {
      pcaTextArr,
      gender: [
        { label: "未填写", value: "0" },
        { label: "男", value: "1" },
        { label: "女", value: "2" },
      ],
      dialogPasswordForm: false,
      addressList: {},
      address: "",
      form: {},
      pwdForm: {},
    };
  },
  methods: {
    //地址数据格式化
    addressDataBind() {
      let ad = "";
      for (var i = 0; i < this.addressList.length; i++) {
        ad = ad + this.addressList[i] + "-";
      }
      ad = ad + this.address;
      this.form.address = ad;
    },

    handleDialogClose() {
      this.pwdForm = {};
    },

    userData() {
      const self = this;
      const uId = localStorage.getItem("user");
      self.$axios
        .get("/user/info/" + uId)
        .then((res) => {
          this.form = res.data;
          if (res.data.address) {
            let arr = res.data.address.split("-");
            this.address = arr[3];
            arr.pop();
            this.addressList = arr;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //修改密码
    passUpdate() {
      const self = this;
      if (
        this.pwdForm.oldPassword == undefined ||
        this.pwdForm.newPassword == undefined
      ) {
        this.$notify({
          title: "密码不能为空",
          type: "warning",
        });
      } else {
        self.$axios
          .post("/user/pass", {
            uId: localStorage.getItem("user"),
            oldPassword: self.pwdForm.oldPassword,
            uPassword: self.pwdForm.newPassword,
          })
          .then((res) => {
            switch (res.code) {
              case "200":
                this.$notify({
                  title: res.msg,
                  type: "success",
                });
                self.dialogPasswordForm = false;
                localStorage.removeItem("token");
                localStorage.removeItem("user");
                localStorage.removeItem("userName");
                this.$store.dispatch("handelIsLogin", true);
                this.$router.push("/register");
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
      }
    },

    // 用户数据更新
    userUpdate() {
      this.addressDataBind();
      const self = this;
      self.$axios
        .post("/user/update", {
          uId: localStorage.getItem("user"),
          uName: self.form.name,
          uEmail: self.form.email,
          uTelephone: self.form.telephone,
          uBirth: self.form.birth,
          uGender: self.form.gender,
          uAddress: self.form.address,
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
  },
  created() {
    this.userData();
  },
};
</script>

<style scoped>
.PersonEdit {
  width: 1190px;
  margin: 0 auto;
  margin-top: 50px;
}

.top-title {
  font-size: 2.25rem;
  font-weight: bold;
}

.top-tips {
  font-size: 0.75rem;
  margin-left: 10px;
}

.pe-main {
  font-size: 1rem;
  margin-top: 20px;
  font-weight: bold;
}

.form-tips {
  font-size: 0.75rem;
  font-weight: normal;
  margin-left: 10px;
  margin-bottom: 20px;
}

.submitBtn {
  background-color: #124c5f;
  color: #fff;
  margin-right: 10px;
}
</style>