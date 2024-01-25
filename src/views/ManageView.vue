<template>
  <el-container style="height: 100vh;">

    <el-backtop :bottom="100"
                :visibility-height="-50">
      <div style="{
        height: 100%;
        width: 100%;
        background-color: #f2f5f6;
        box-shadow: 0 0 6px rgba(0,0,0, .12);
        text-align: center;
        line-height: 40px;
        color: #1989fa;
      }">
        UP
      </div>
    </el-backtop>

    <el-aside :width="sideWidth +'px'"
              style="background-color: rgb(238, 241, 246);height: 100%;box-shadow: 2px 0 6px #eee;">
      <el-menu :default-active="activeIndex"
               style="height:100%;overflow-x: hidden;"
               background-color="#fff"
               text-color="#000"
               active-text-color="#F1C31A"
               :collapse="isCollapse">
        <div style="height:60px;line-height: 60px;text-align: center;">
          <img src="/img/logo.png"
               alt="PETHUB">
        </div>
        <router-link to="/main"><el-menu-item index="1">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
          </el-menu-item></router-link>
        <router-link to="/order"><el-menu-item index="2">
            <i class="el-icon-s-order"></i>
            <span slot="title">订单管理</span>
          </el-menu-item></router-link>
        <router-link to="/user"><el-menu-item index="3">
            <i class="el-icon-s-custom"></i>
            <span slot="title">用户管理</span>
          </el-menu-item></router-link>
        <router-link to="/service"><el-menu-item index="4">
            <i class="el-icon-s-opportunity"></i>
            <span slot="title">服务管理</span>
          </el-menu-item></router-link>
        <!-- <el-menu-item index="4">
          <i class="el-icon-s-goods"></i>
          <span slot="title">商品管理</span>
        </el-menu-item> -->
        <!-- <el-menu-item index="4">
          <i class="el-icon-s-help"></i>
          <span slot="title">工单管理</span>
        </el-menu-item>
        <el-menu-item index="5">
          <i class="el-icon-s-tools"></i>
          <span slot="title">系统管理</span>
        </el-menu-item> -->
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px;display: flex;box-shadow: 2px 0 6px 0 #eee;">
        <div style="flex: 1; font-size: 18px;">
          <span :class="collapseBtnClass"
                style="cursor: pointer;"
                @click="collapse">
          </span>
        </div>
        <el-dropdown style="margin-right: 50px; cursor: pointer;">
          <span class="el-dropdown-link"
                style="color: #000;font-family:Glades Bold,微软雅黑; ">
            A D M I N<i class="el-icon-arrow-down el-icon--right"
               style="color: #000;font-size:1rem;font-weight: bold;"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <span @click="dialogPasswordForm = true"><el-dropdown-item>修改密码</el-dropdown-item></span>
            <router-link to="/home"
                         replace><el-dropdown-item>退出后台</el-dropdown-item></router-link>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>

    <!-- 修改密码 -->
    <el-dialog @close="handleDialogClose"
               width="40%"
               title="修改密码"
               :visible.sync="dialogPasswordForm">
      <el-form>
        <el-form-item label="旧密码">
          <el-input placeholder="请输入密码"
                    v-model="form.oldPassword"
                    show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input placeholder="请输入密码"
                    v-model="form.newPassword"
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

  </el-container>
</template>

<script>
import md from "@/assets/js/ManagerData.js";
export default {
  data() {
    return {
      activeIndex: "",
      ...md.data(),
      form: {},
      dialogPasswordForm: false,
    };
  },
  methods: {
    ...md.methods,
    //菜单栏收起展开
    collapse() {
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        this.sideWidth = 64;
        this.collapseBtnClass = "el-icon-s-unfold";
      } else {
        this.sideWidth = 200;
      }
    },

    handleDialogClose() {
      this.form = {};
    },

    // 用户密码修改
    passUpdate() {
      const self = this;
      if (
        this.form.oldPassword == undefined ||
        this.form.newPassword == undefined
      ) {
        this.$notify({
          title: "密码不能为空",
          type: "warning",
        });
      } else {
        self.$axios
          .post("/user/pass", {
            uId: localStorage.getItem("user"),
            oldPassword: self.form.oldPassword,
            uPassword: self.form.newPassword,
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

    checkUser() {
      const self = this;
      let uId = localStorage.getItem("user");
      if (uId != null) {
        self.$axios
          .get("/user/info/" + uId)
          .then((res) => {
            if (res.code == "401") {
              this.closeUser();
              this.$notify.warning("登录信息失效，请重新登录");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    closeUser() {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      localStorage.removeItem("userName");
      this.$store.dispatch("handelIsLogin", true);
      if (this.$route.path !== "/login") {
        this.$router.replace("/login");
      }
    },
  },
  mounted() {
    this.checkUser();
    //MENU索引
    if (this.$route.path == "/main") {
      this.activeIndex = "1";
    } else if (this.$route.path == "/order") {
      this.activeIndex = "2";
    } else if (this.$route.path == "/user") {
      this.activeIndex = "3";
    } else if (this.$route.path == "/service") {
      this.activeIndex = "4";
    }
  },
  beforeRouteEnter(to, from, next) {
    const uId = localStorage.getItem("user");
    let userName = localStorage.getItem("userName");
    if (userName !== null) {
      let arr = localStorage.getItem("userName").split('"')[1];
      userName = arr;
      if (uId === null) {
        next("/home");
      } else if (uId == "1" && userName == "admin") {
        next();
      }
    } else {
      next("/home");
    }
  },
};
</script>

<style>
.el-header {
  background-color: #fff;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>