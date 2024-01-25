<template>
  <div class="login-register">
    <div class="contain">
      <div class="big-box"
           :class="{active:isLogin}">
        <div class="big-contain"
             key="bigContainLogin"
             v-if="isLogin">
          <div class="btitle">账户登录</div>
          <div class="bform">
            <input type="email"
                   placeholder="邮箱"
                   v-model="form.uEmail">
            <span class="errTips"
                  v-show="emailError">* 邮箱填写错误 *</span>
            <input type="password"
                   placeholder="密码"
                   v-model="form.uPassword">
            <span class="errTips"
                  v-show="passwordError">* 密码填写错误 *</span>
          </div>
          <button class="bbutton"
                  @click="login">登录</button>
        </div>
        <div class="big-contain"
             key="bigContainRegister"
             v-else>
          <div class="btitle">创建账户</div>
          <div class="bform">
            <input type="text"
                   placeholder="用户名"
                   v-model="form.uName">
            <!-- <span class="errTips"
                  v-show="existed">* 用户名已经存在！ *</span> -->
            <input type="email"
                   placeholder="邮箱"
                   v-model="form.uEmail">
            <span class="errTips"
                  v-show="existed">* 邮箱已被绑定！ *</span>
            <input type="password"
                   placeholder="密码"
                   v-model="form.uPassword">
          </div>
          <button class="bbutton"
                  @click="register">注册</button>
        </div>
      </div>
      <div class="small-box"
           :class="{active:isLogin}">
        <div class="small-contain"
             key="smallContainRegister"
             v-if="isLogin">
          <div class="stitle">你好，朋友!</div>
          <p class="scontent">开始注册，和我们一起旅行</p>
          <button class="sbutton"
                  @click="changeType">注册</button>
        </div>
        <div class="small-contain"
             key="smallContainLogin"
             v-else>
          <div class="stitle">欢迎回来!</div>
          <p class="scontent">与我们保持联系，请登录你的账户</p>
          <button class="sbutton"
                  @click="changeType">登录</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      isLogin: true,
      emailError: false,
      passwordError: false,
      existed: false,
      form: {
        uName: "",
        uEmail: "",
        uPassword: "",
      },
    };
  },
  // 监控data中的数据变化
  watch: {
    //深度监听
    $route: {
      handler: function (newVal) {
        if (newVal) {
          if (this.$route.path == "/login") {
            this.isLogin = true;
          } else if (this.$route.path == "/register") {
            this.isLogin = false;
          }
        }
      },
      deep: true, // 深度监听
      immediate: true, // 立即执行
    },
  },
  methods: {
    changeType() {
      this.isLogin = !this.isLogin;
      this.form.uName = "";
      this.form.uEmail = "";
      this.form.uPassword = "";
    },
    login() {
      const self = this;
      if (self.form.uEmail != "" && self.form.uPassword != "") {
        self
          .$axios({
            method: "post",
            url: "/login",
            data: {
              uEmail: self.form.uEmail,
              uPassword: self.form.uPassword,
            },
          })
          .then((res) => {
            if (res.code === "200") {
              console.log(res);
              localStorage.setItem("token", JSON.stringify(res.token));
              localStorage.setItem("user", JSON.stringify(res.data.id));
              localStorage.setItem("userName", JSON.stringify(res.data.name));
              this.$router.push("/home");
              this.$notify({
                title: res.msg,
                type: "success",
              });
              this.$store.dispatch("handelIsLogin", true);
              this.$store.dispatch("userName", res.data.name);
            } else {
              this.$notify({
                title: res.msg,
                type: "error",
              });
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.$notify({
          title: "填写不能为空！",
          type: "warning",
        });
      }
    },
    register() {
      const self = this;
      if (
        self.form.uName != "" &&
        self.form.uPassword != "" &&
        self.form.uEmail != ""
      ) {
        self.$axios
          .post("/register", {
            uName: self.form.uName,
            uPassword: self.form.uPassword,
            uEmail: self.form.uEmail,
            uClass: 0,
          })
          .then((res) => {
            let uPassword = self.form.uPassword;
            let uEmail = self.form.uEmail;
            switch (res.code) {
              case "200":
                this.$notify({
                  title: res.msg,
                  type: "success",
                });
                self.changeType();
                self.form.uEmail = uEmail;
                self.form.uPassword = uPassword;
                break;
              case "-1":
                this.existed = true;
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
      } else {
        this.$notify({
          title: "填写不能为空！",
          type: "warning",
        });
      }
    },
  },
};
</script>

<style scoped="scoped">
.login-register {
  width: 100vw;
  height: 100vh;
  box-sizing: border-box;
}
.contain {
  width: 60%;
  height: 60%;
  position: relative;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  border-radius: 20px;
  box-shadow: 0 0 3px #f0f0f0, 0 0 6px #f0f0f0;
}
.big-box {
  width: 70%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 30%;
  transform: translateX(0%);
  transition: all 1s;
}
.big-contain {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.btitle {
  font-size: 1.5em;
  font-weight: bold;
  color: #124c5f;
}
.bform {
  width: 100%;
  height: 40%;
  padding: 2em 0;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.bform .errTips {
  display: block;
  width: 50%;
  text-align: left;
  color: red;
  font-size: 0.7em;
  margin-left: 1em;
}
.bform input {
  width: 50%;
  height: 30px;
  border: none;
  outline: none;
  border-radius: 10px;
  padding-left: 2em;
  background-color: #f0f0f0;
}
.bbutton {
  width: 20%;
  height: 40px;
  border-radius: 24px;
  border: none;
  outline: none;
  background-color: #124c5f;
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
}
.small-box {
  width: 30%;
  height: 100%;
  background: linear-gradient(135deg, #124c5f, rgb(56, 183, 145));
  position: absolute;
  top: 0;
  left: 0;
  transform: translateX(0%);
  transition: all 1s;
  border-top-left-radius: inherit;
  border-bottom-left-radius: inherit;
}
.small-contain {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.stitle {
  font-size: 1.5em;
  font-weight: bold;
  color: #fff;
}
.scontent {
  font-size: 0.8em;
  color: #fff;
  text-align: center;
  padding: 2em 4em;
  line-height: 1.7em;
}
.sbutton {
  width: 60%;
  height: 40px;
  border-radius: 24px;
  border: 1px solid #fff;
  outline: none;
  background-color: transparent;
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
}

.big-box.active {
  left: 0;
  transition: all 0.5s;
}
.small-box.active {
  left: 100%;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: inherit;
  border-bottom-right-radius: inherit;
  transform: translateX(-100%);
  transition: all 1s;
}
</style>
