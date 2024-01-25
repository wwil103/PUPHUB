<template>
  <div class="os-container">
    <div class="hero">
      <div>
        <div class="hero-position">
          <div class="hero-content">
            <span class="hero-first-title">TAkING CARE<br>FOR YOUR SMART DOG !</span>
            <span class="hero-second-title">Human–canine bonding is the relationship<br> between dogs and humans.</span>
            <router-link to="/services">
              <div class="hero-btn">
                <span>EXPLORE MORE</span>
                <i class="el-icon-arrow-right"></i>
              </div>
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <div class="sc-top">
      <div class="sc-title">Services Category</div>
      <div class="sc-hr"></div>
    </div>
    <div class="services-category">
      <el-row class="sc-content"
              :gutter="20">
        <el-col :span="4"
                v-for="sc in scContent"
                :key="sc.id">
          <div>
            <div class="sc-main"><img :style="{width:sc.iwidth}"
                   :src="sc.img"><span>{{ sc.title }}</span></div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "OurServices",
  data() {
    return {
      scContent: [
        {
          id: "01",
          img: "/img/services1.png",
          iwidth: "85px",
          title: "Grooming",
        },
        {
          id: "02",
          img: "/img/services2.png",
          iwidth: "85px",
          title: "Healthcare",
        },
        {
          id: "03",
          img: "/img/services3.png",
          iwidth: "105px",
          title: "Daycare",
        },
        {
          id: "04",
          img: "/img/services4.png",
          iwidth: "95px",
          title: "Training",
        },
        {
          id: "05",
          img: "/img/services5.png",
          iwidth: "95px",
          title: "Hyginic care",
        },
      ],
    };
  },
};
</script>

<style>
/* 版心 */
.os-container {
  width: 1190px;
  margin: 0 auto;
  position: relative;
}

/* MAIN */
.hero {
  width: 1710px;
  height: 800px;
  background: url("@/assets/img/hero-bg.png") no-repeat;
  position: relative;
  left: -385px;
  top: -15px;
}

.hero > div {
  width: 1190px;
  height: 800px;
  margin: 0 auto;
  position: relative;
  left: 200px;
  top: 40px;
}

.hero-position {
  width: 500px;
  height: 100%;
}

.hero-content {
  position: absolute;
  top: 20%;
}

.hero-content > span {
  display: block;
  margin: 10px 0;
}

.hero-first-title {
  color: #000;
  font-family: "Glades Bold", "微软雅黑";
  font-size: 44px;
  letter-spacing: 1px;
  line-height: 60px;
}

.hero-second-title {
  margin-top: 10px;
  color: #181817;
  font-family: "Roboto", "微软雅黑";
  font-size: 19px;
  letter-spacing: 1px;
  line-height: 30px;
}

.hero-btn {
  width: 200px;
  height: 60px;
  background-color: #124c5f;
  margin-top: 30px;
  border-radius: 5px;
  line-height: 60px;
  text-align: center;
  cursor: pointer;
}

.hero-btn > span {
  font-family: "Glades Bold", "微软雅黑";
  font-size: 17px;
  color: white;
}

i {
  margin-left: 10px;
  font-size: 16px;
  font-weight: bold;
  color: white;
}

/* 服务类别 */
.services-category {
  width: 1120px;
  height: 270px;
  margin: 0 auto;
}

.sc-top {
  position: absolute;
  top: 65%;
  left: 3%;
}

.sc-title {
  color: #000;
  font-family: "Pangolin", "微软雅黑";
  font-size: 28px;
}

.sc-hr {
  margin-top: 5px;
  background-color: #3a8da8;
  width: 115px;
  height: 5px;
  border-radius: 5px;
}

.sc-content {
  margin-top: 20px;
}

.sc-content > div {
  display: inline-flex;
  width: 202px;
  height: 225px;
  margin-right: 25px;
  border: 1px solid #ffee94;
  background-image: linear-gradient(rgb(255, 238, 148, 0.3), white);
  border-radius: 25px;
  text-align: center;
  justify-content: center;
  align-items: center;
}

.sc-content > div:last-child {
  margin-right: 0px;
}

.sc-content > div:nth-child(odd) {
  border: 1px solid #98f2e1;
  background-image: linear-gradient(rgb(152, 242, 225, 0.3), white);
}

.sc-main {
  width: 115px;
  height: 135px;
}

.sc-content img {
  height: 95px;
  margin-bottom: 15px;
}

.sc-content span {
  color: #000;
  font-family: "Rubik", "微软雅黑";
  font-size: 20px;
}

.sc-content > div:last-child span {
  white-space: nowrap;
}
</style>