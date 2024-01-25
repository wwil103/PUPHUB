<template>
  <div id="MainView">
    <el-backtop :bottom="100"
                :right="60"
                :visibility-height="800">
      <div style="{
        text-align: center;
        line-height: 40px;
        color: #fff;
      }">
        UP
      </div>
    </el-backtop>
    <HomeHeader />
    <transition name="fade"
                mode="out-in"><router-view />
    </transition>
    <HomeFooter />
  </div>
</template>

<script>
import HomeHeader from "@/components/HomeHeader";
import HomeFooter from "@/components/HomeFooter";
export default {
  name: "MainView",
  components: {
    HomeHeader,
    HomeFooter,
  },
  created() {
    this.$store.dispatch("handelIsLogin", true);
    this.checkUser();
  },
  methods: {
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
};
</script>

<style scoped>
.el-backtop {
  width: 60px;
  height: 60px;
  background-color: #124c5f;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
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