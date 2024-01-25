<template>
  <div class="ServiceSubscribe">
    <span class="service-title-bg">Pup<br>Service Booking</span>
    <div class="service-bg">
    </div>
    <div class="ss-content">
      <el-card class="ss-box-card box-card">
        <div class="service-tag">
          <span class="service-title">服务选择</span>
          <div v-for="(item,index) in this.services"
               :key="item.id">
            <a class="service-tag-a">
              <div class="s-a-div"
                   :class="index == currentIndex ? 'div-focus' : ''"
                   @click="clickQuery(item.id)">
                <img class="s-a-img"
                     :src="index == currentIndex ? item.icon.focus : item.icon.blur"
                     alt="Dog white">
                <div class="s-a-name"
                     style="text-shadow: none">{{item.name}}</div>
                <div v-show="index == currentIndex"
                     class="s-a-icon"><i class="el-icon-s-data"></i></div>
              </div>
            </a>
          </div>
          <span class="service-title">服务时间</span>
          <div class="service-date">
            <el-date-picker v-model="form.oDate"
                            type="date"
                            placeholder="选择日期"
                            format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd">
            </el-date-picker>
          </div>
          <div class="service-time">
            <el-radio v-model="form.oTime"
                      label="1"
                      border>早上</el-radio>
            <el-radio v-model="form.oTime"
                      label="2"
                      border>下午</el-radio>
            <el-radio v-model="form.oTime"
                      label="3"
                      border>晚上</el-radio>
          </div>
          <span class="service-title">狗狗年龄</span>
          <div class="service-time">
            <el-radio v-model="form.oAge"
                      label="1"
                      border>幼犬</el-radio>
            <el-radio v-model="form.oAge"
                      label="2"
                      border>成年犬</el-radio>
            <el-radio v-model="form.oAge"
                      label="3"
                      border>老年犬</el-radio>
          </div>
          <span class="service-title">狗狗大小</span>
          <div class="service-time">
            <el-radio v-model="form.oSize"
                      label="1"
                      border>小型犬</el-radio>
            <el-radio v-model="form.oSize"
                      label="2"
                      border>中型犬</el-radio>
            <el-radio v-model="form.oSize"
                      label="3"
                      border>大型犬</el-radio>
          </div>
          <span class="service-title">地址</span>
          <el-select v-model="form.oAddress"
                     placeholder="请选择">
            <el-option v-for="item in oAddress"
                       :key="item.index"
                       :label="item"
                       :value="item">
            </el-option>
          </el-select>
          <span v-show="isAddress"
                class="formTips">*这还没有保存你的地址呢，前往<a href="/person/edit"> 个人中心 </a>保存</span>
          <span class="service-title">联系方式</span>
          <el-select v-model="form.oContact"
                     placeholder="请选择">
            <el-option v-for="item in oContact"
                       :key="item.index"
                       :label="item"
                       :value="item">
            </el-option>
          </el-select>
          <span v-show="isContact"
                class="formTips">*还可以添加你的电话号码作为联系方式，前往<a href="/person/edit"> 个人中心 </a>添加</span>
        </div>
        <a @click="orderAdd()"
           class="service-btn"> 立即预约 </a>
      </el-card>
    </div>
  </div>
</template>

<script>
import sd from "@/assets/js/ServicesData.js";
export default {
  name: "ServiceSubscribe",
  data() {
    return {
      ...sd.data(),
      currentIndex: 0,
      oAddress: [],
      isAddress: false,
      isContact: false,
      oContact: [],
      form: {},
    };
  },
  methods: {
    userData() {
      const self = this;
      const uId = localStorage.getItem("user");
      self.$axios
        .get("/user/info/" + uId)
        .then((res) => {
          if (res.data.address == "") {
            this.isAddress = true;
          } else {
            this.isAddress = false;
            self.oAddress.push(res.data.address);
          }
          self.oContact.push(res.data.email);
          if (res.data.telephone == null) {
            this.isContact = true;
          } else {
            this.isContact = false;
            self.oContact.push(res.data.telephone);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 订单数据新增
    orderAdd() {
      const self = this;
      self.$axios
        .post("/order/add", {
          oId: self.form.oId,
          oUid: localStorage.getItem("user"),
          oService: self.form.oService,
          oDate: self.form.oDate,
          oTime: self.form.oTime,
          oAge: self.form.oAge,
          oSize: self.form.oSize,
          oAddress: self.form.oAddress,
          oContact: self.form.oContact,
          oStatus: 0,
        })
        .then((res) => {
          switch (res.code) {
            case "200":
              this.$notify({
                title: res.msg,
                type: "success",
              });
              this.$router.push("/person");
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
    clickQuery(num) {
      this.form.oService = num;
      num = num - 1;
      this.currentIndex = num;
      if (!(this.$route.query.tag === num)) {
        this.$router.push({
          name: "ServiceSubscribe",
          query: {
            tag: num,
          },
        });
      }
    },
    load() {
      this.time = "";
      this.date = "";
      this.size = "";
      const num = this.$route.query.tag;
      this.currentIndex = num;
      this.form.oService = parseInt(num) + 1;
      this.userData();
    },
  },
  beforeRouteEnter(to, from, next) {
    const uId = localStorage.getItem("user");
    const userName = localStorage.getItem("userName");
    if (uId === null || userName === null) {
      next((vm) => {
        // 通过 `vm` 访问组件实例
        vm.$notify({
          title: "请先登录账户！",
          type: "warning",
        });
        next("/login");
      });
    } else {
      next((vm) => {
        vm.load();
      });
    }
  },
};
</script>

<style scoped>
.ServiceSubscribe {
  width: 100%;
  position: relative;
  margin-bottom: 2rem;
  margin-top: 60px;
}

.ss-content {
  position: relative;
  width: 690px;
  margin: 0 10%;
}

.ss-box-card {
  background: #333333;
  border-radius: 20px;
  color: #fff;
}

.service-title-bg {
  font-size: 6rem;
  top: 5%;
  right: 2%;
  position: absolute;
  /* font-family: "Glades Bold", "微软雅黑"; */
  font-family: "Pangolin", "微软雅黑";
}

.service-bg {
  height: 32rem;
  width: 100%;
  bottom: -5%;
  z-index: 0;
  position: absolute;
  background-size: cover !important;
  background-image: linear-gradient(
      to left,
      rgba(5, 34, 122, 0.3),
      rgba(18, 76, 95, 0.9)
    ),
    url("https://spare-assets.oss-cn-shanghai.aliyuncs.com/DayCare.jpg");
}

.service-tag {
  width: 790px;
  margin: 0 auto;
}

.service-tag > div {
  display: inline-block;
}

.service-tag-a {
  display: inline-block;
  text-decoration: none;
  background-color: transparent;
  cursor: pointer;
}

.s-a-div {
  width: 150px;
  text-align: center;
  position: relative;
  padding: 1rem 0;
  color: #fff;
  font-weight: bold;
  background: #333333;
  display: inline-block;
  border: 1px double #c7e7e1;
  border-radius: 10px;
  margin-right: 10px;
  margin-bottom: 10px;
}

.s-a-div > * {
  display: inline-block;
}

.div-focus {
  background: #124c5f !important;
}

.s-a-img {
  max-width: 100%;
  border-style: none;
  width: 20px;
  aspect-ratio: auto 35 / 35;
  height: 20px;
  position: relative;
  bottom: -3px;
}

.s-a-name {
  font-size: 15px;
  margin-left: 10px;
}

.s-a-icon {
  position: absolute;
  bottom: -4px;
  left: 30%;
  right: 30%;
}

.service-time > label {
  border-radius: 10px;
  color: #fff;
  margin-right: 0px;
}

.is-checked {
  border-color: #fff !important;
  background: #124c5f !important;
}

.service-date {
  margin-right: 1rem;
}

.service-title {
  display: block;
  font-size: 20px;
  margin: 1rem 0;
  color: #c7e7e1;
}

.service-btn {
  display: block;
  font-size: 1.25rem;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  background-color: #c7e7e1;
  color: #124c5f;
  border: 1px solid #000;
  border-radius: 5px;
  padding: 1rem 2rem;
  font-weight: bold;
  margin: 50px 80px 10px;
}

.formTips {
  color: #fff;
  font-size: 0.8rem;
  margin-left: 10px;
}
.formTips a {
  text-decoration: underline;
  color: #c7e7e1;
}
</style>