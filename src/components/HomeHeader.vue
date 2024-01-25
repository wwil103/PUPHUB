<template>
  <div class="topbar">
    <el-row>
      <el-col :span="3"><router-link to="/home">
          <div class="logo">
            <img src="/img/logo.png"
                 alt="PETHUB">
          </div>
        </router-link>
      </el-col>
      <el-col :span="2">&nbsp;
      </el-col>
      <el-col :span="19">
        <div class="topbar-nav">
          <el-row :gutter="20">
            <el-col :span="3">
              <div class="grid-content">
                <router-link to="/home"><a href="#">HOME</a></router-link>
                <transition name="fade"
                            mode="out-in">
                  <div v-show="$route.path == '/home'"
                       class="sc-tabbar">
                    <div class="sc-spot"></div>
                    <div class="sc-line"></div>
                  </div>
                </transition>
              </div>
            </el-col>
            <el-col :span="3">
              <div class="grid-content">
                <router-link to="/services"><a href="#">SERVICES</a></router-link>
                <transition name="fade"
                            mode="out-in">
                  <div v-show="$route.path == '/services'"
                       class="sc-tabbar">
                    <div class="sc-spot"></div>
                    <div class="sc-line"></div>
                  </div>
                </transition>
              </div>
            </el-col>
            <el-col :span="3">
              <div class="grid-content">
                <router-link to="/help"><a href="#">HELP</a></router-link>
                <transition name="fade"
                            mode="out-in">
                  <div v-show="$route.path == '/help'"
                       class="sc-tabbar">
                    <div class="sc-spot"></div>
                    <div class="sc-line"></div>
                  </div>
                </transition>
              </div>
            </el-col>
            <el-col :span="3">
              <div class="grid-content">
                <router-link to="/about"><a href="#">ABOUT US</a></router-link>
                <transition name="fade"
                            mode="out-in">
                  <div v-show="$route.path == '/about'"
                       class="sc-tabbar">
                    <div class="sc-spot"></div>
                    <div class="sc-line"></div>
                  </div>
                </transition>
              </div>
            </el-col>
            <el-col :span="3">
              <div class="grid-content">
                <router-link to="/contact"><a href="#">CONTACT</a></router-link>
                <transition name="fade"
                            mode="out-in">
                  <div v-show="$route.path == '/contact'"
                       class="sc-tabbar">
                    <div class="sc-spot"></div>
                    <div class="sc-line"></div>
                  </div>
                </transition>
              </div>
            </el-col>
            <el-col :span="9"
                    class="last-group">
              <div class="loginBtnGroup">
                <div v-show="!personShow"><router-link to="/login?is=true"><button class="btn-si">SIGN IN</button></router-link>
                  <router-link to="/register?is=false"><button class="btn-re">REGISITER</button></router-link>
                </div>
                <div v-show="personShow"
                     class="personBtnGroup">
                  <div class="avatar"><el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></div>
                  <div class="btn-person">
                    <el-dropdown trigger="click">
                      <button class="btn-re">{{ userName }}</button>
                      <el-dropdown-menu slot="dropdown">
                        <router-link to="/person"><el-dropdown-item icon="el-icon-user">个人中心</el-dropdown-item></router-link>
                        <router-link to="/help"><el-dropdown-item icon="el-icon-unlock">平台帮助</el-dropdown-item></router-link>
                        <router-link to="/contact"><el-dropdown-item icon="el-icon-service">联系我们</el-dropdown-item></router-link>
                        <span @click="closeUser()"><el-dropdown-item icon="el-icon-switch-button">退出账户</el-dropdown-item></span>
                      </el-dropdown-menu>
                    </el-dropdown>
                  </div>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "HomeHeader",
  data() {
    return {
      personShow: false,
      userName: "",
    };
  },
  methods: {
    closeUser() {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      localStorage.removeItem("userName");
      this.$store.dispatch("handelIsLogin", true);
      if (this.$route.path !== "/home") {
        this.$router.push("/home");
      }
    },
  },
  watch: {
    //深度监听
    $route: {
      handler: function (newVal) {
        if (newVal) {
          if (this.$store.state.userName)
            this.userName = this.$store.state.userName;
          if (this.$store.state.isLogin) {
            this.personShow = this.$store.state.isLogin;
          } else {
            this.personShow = false;
          }
        }
      },
      deep: true, // 深度监听
      immediate: true, // 立即执行
    },
  },
  created() {
    if (localStorage.getItem("userName")) {
      let arr = localStorage.getItem("userName").split('"');
      this.userName = arr[1];
    }
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.sc-tabbar {
  width: 100%;
  line-height: 0;
  margin-top: -20px;
}

.sc-line {
  display: inline-block;
  background-color: #124c5f;
  width: 30px;
  height: 10px;
  margin-left: 5px;
  border-radius: 10px;
}

.sc-spot {
  display: inline-block;
  background-color: #124c5f;
  width: 10px;
  height: 10px;
  border-radius: 50px;
}

.topbar {
  width: 1190px;
  margin: 20px auto;
  margin-bottom: 0;
  height: 80px;
  background: white;
}

.logo {
  margin: 0 20px;
}

.logo img {
  width: auto;
  height: 80px;
  cursor: pointer;
}

.topbar-nav {
  width: 100%;
}

.grid-content a {
  color: #000000;
  font-family: "Glades Bold", "微软雅黑";
}

.grid-content a:hover {
  color: #124c5f;
}

.btn-si {
  width: 135px;
  height: 40px;
  font-size: 1rem;
  border: 1px solid #124c5f;
  border-radius: 5px;
  color: white;
  font-family: "Glades Bold", "微软雅黑";
  background-color: #124c5f;
  cursor: pointer;
}

.btn-re {
  width: 135px;
  height: 40px;
  border: 1px solid #c7e7e1;
  font-size: 1rem;
  border-radius: 5px;
  color: #124c5f;
  font-family: "Glades Bold", "微软雅黑";
  background-color: #c7e7e1;
  margin-left: 20px;
  cursor: pointer;
}

.btn-person {
  display: inline-block;
}

li.el-dropdown-menu__item {
  width: 100px;
  text-align: center;
}

.btn-person > div > div {
  width: 175px;
  height: 50px;
  border: 1px solid #c7e7e1;
  border-radius: 5px;
  font-family: "Glades Bold", "微软雅黑";
  background-color: #124c5f;
  margin-left: 10px;
  cursor: pointer;
}

.btn-person button {
  color: #fff;
  background-color: #124c5f;
}

.btn-person div > div > * {
  display: inline-block;
}

.el-row {
  line-height: 80px;
}

.grid-content {
  text-align: center;
}

.loginBtnGroup {
  display: inline-flex;
  vertical-align: middle;
}

.last-group {
  text-align: center;
}

.personBtnGroup {
  cursor: pointer;
}

.avatar {
  display: inline-block;
}

.avatar span {
  display: block;
  position: relative;
  top: 13px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
