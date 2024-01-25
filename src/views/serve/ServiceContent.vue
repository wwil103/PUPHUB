<template>
  <div class="ServiceContent">
    <div class="service-top"
         :style="'background-image: linear-gradient(to right, rgba(18, 76, 95, 0.9), rgba(255,255,255,0.1)), url(' + this.services[this.$route.query.active].img + '); background-position-y: -100px;'">
      <div class="service-top-content">
        <span class="s-content-title">{{ this.services[this.$route.query.active].name }}</span>
        <div class="s-content-intro"
             v-html="this.services[this.$route.query.active].intro"></div>
        <a @click="SubScribe($route.query.active)"
           class="s-content-btn"> 立即预订 </a>
      </div>
    </div>
    <div class="service-middle">
      <el-row :gutter="20">
        <el-col :span="4"
                v-for="(item,index) in this.services"
                :key="item.id">
          <a class="service-middle-a">
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
          </a></el-col></el-row>
    </div>
    <div class="service-bottom">
      <el-row :gutter="20">
        <el-col :span="8"
                v-for="item in this.services[this.$route.query.active].detail"
                :key="item.index">
          <div class="service-bottom-div">
            <header class="bottom-div-title">{{ item.title }}</header>
            <div class="bottom-div-lists"
                 v-for="list in item.content"
                 :key="list.index"> {{ list }} </div>
          </div>
        </el-col></el-row>
    </div>
  </div>
</template>

<script>
import sd from "@/assets/js/ServicesData.js";
export default {
  name: "ServiceContent",
  data() {
    return { ...sd.data(), currentIndex: 0 };
  },
  methods: {
    clickQuery(num) {
      num = num - 1;
      this.currentIndex = num;
      if (!(this.$route.query.active === num)) {
        this.$router.push({
          name: "ServiceContent",
          query: {
            active: num,
          },
        });
      }
    },

    SubScribe(num) {
      this.$router.push({
        name: "ServiceSubscribe",
        query: {
          tag: num,
        },
      });
    },
    load() {
      const num = this.$route.query.active;
      this.currentIndex = num;
    },
  },
  created() {
    this.load();
  },
};
</script>

<style scoped>
.ServiceContent {
  margin-top: 35px;
}

.service-top {
  height: 20rem;
  padding: 2rem 0;
}

.service-top-content {
  width: 1190px;
  margin: 0 auto;
}

.s-content-title {
  display: block;
  font-size: 3rem;
  font-weight: bold;
  color: #fff;
  margin-bottom: 1rem;
}

.s-content-intro {
  font-size: 1.5rem;
  color: #fff;
  margin-bottom: 3.5rem;
  line-height: 1.5;
}

.s-content-btn {
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
}

.service-middle {
  width: 1090px;
  margin: 0 auto;
  padding: 2rem 0;
}

.service-middle-a {
  display: inline-block;
  text-decoration: none;
  background-color: transparent;
  cursor: pointer;
}

.s-a-div {
  width: 180px;
  text-align: center;
  position: relative;
  padding: 1rem 0;
  color: #fff;
  font-weight: bold;
  background: #333333;
  display: inline-block;
  border-radius: 5px;
}

.div-focus {
  background: #124c5f !important;
}

.s-a-img {
  font-size: 3rem;
  max-width: 100%;
  border-style: none;
  width: 35px;
  aspect-ratio: auto 35 / 35;
  height: 35px;
}

.s-a-icon {
  position: absolute;
  bottom: -4px;
  left: 30%;
  right: 30%;
}

.service-bottom {
  width: 990px;
  margin: 0 auto;
  padding: 1rem 0;
  margin-bottom: 4rem;
}

.bottom-div-title {
  font-size: 1.25rem;
  margin-bottom: 1rem;
  color: #124c5f;
  font-weight: bold;
}

.bottom-div-lists {
  font-size: 1rem;
  margin-bottom: 1rem;
  color: #000;
  font-weight: bold;
}
</style>