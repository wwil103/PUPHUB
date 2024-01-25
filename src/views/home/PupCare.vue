<template>
  <div class="pet-care-customer">
    <div class="pet-care">
      <div class="pet-care-content">
        <span class="care-first-title">Dogs do speak, but only to those<br> who know how to listen.</span>
        <span class="care-second-title">Human–canine bonding is the relationship<br> between dogs and humans.</span>
        <router-link to="/services">
          <div class="care-btn">
            <span>EXPLORE MORE</span>
            <i class="el-icon-arrow-right"></i>
          </div>
        </router-link>
      </div>
    </div>
    <div class="pet-customer">
      <div class="pr-top">
        <div class="sc-title">Happy Customer</div>
        <div class="sc-hr"></div>
      </div>
      <div class="pet-customer-content">
        <el-row :gutter="20">
          <el-col :span="8"
                  v-for="pc in pcContent"
                  :key="pc.id">
            <div :style="{backgroundColor:pc.color}"
                 class="pc-show"><img class="customer-img"
                   :src="pc.img">
            </div>
            <div class="pc-content">
              <span class="pc-name">{{ pc.name }}</span>
              <span class="pc-intro">{{ pc.intro }}</span>
              <div>
                <span class="star">
                  <i class="fa"
                     :class="star"
                     v-for="(star,index) in starsItem(pc.mark)"
                     :key="index"></i>
                  {{ pc.mark+" / 5" }}</span>
              </div>
            </div>
          </el-col>
        </el-row>
        <!-- <el-button class="pc-btn"
                   type="primary">Explore More<i class="el-icon-arrow-right el-icon--right"></i></el-button> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PupCare",
  data() {
    return {
      pcContent: [
        {
          id: "01",
          img: require("@/assets/img/girl-with-dog1.png"),
          color: "#FFF1A6",
          name: "Anna & Tobby",
          intro: "Amazing Products & Delivery on time.",
          mark: 4.2,
        },
        {
          id: "02",
          img: require("@/assets/img/girl-with-dog2.png"),
          color: "#EBA8BF",
          name: "Christine & Tom",
          intro: "Love the overall Shpping experience!",
          mark: 5,
        },
        {
          id: "03",
          img: require("@/assets/img/girl-with-dog3.png"),
          color: "#DDC7ED",
          name: "Sindy & Kitch",
          intro: "Kitch is love food from the pup-hub ",
          mark: 5,
        },
      ],
    };
  },
  methods: {
    starsItem(stars) {
      this.stars > 5 ? (this.stars = 5) : this.stars;
      let resStar = []; //储存各种星星样式的数量
      let integer = Math.floor(stars); //判断整数
      for (let i = 0; i < integer; i++) {
        resStar.push("fa-star"); //实星 的数量
      }
      while (resStar.length < 5) {
        resStar.push("fa-star-o"); //用 空星 填充后面的
      }
      return resStar;
    },
  },
};
</script>

<style scoped>
.pet-care-customer {
  width: 1190px;
  margin: 0 auto;
}

.pet-care {
  width: 1500px;
  height: 681px;
  background: url("../../assets/img/pet-care.png") no-repeat;
  position: relative;
  left: -170px;
  margin-bottom: 40px;
}

.pet-care-content {
  position: absolute;
  left: 15%;
  top: 15%;
}

.pet-care-content > * {
  display: block;
}

.care-first-title {
  color: #000;
  font-family: "Pangolin", "微软雅黑";
  font-size: 34px;
  letter-spacing: 1px;
  line-height: 60px;
}

.care-second-title {
  margin-top: 10px;
  color: #181817;
  font-family: "Roboto", "微软雅黑";
  font-size: 19px;
  letter-spacing: 1px;
  line-height: 30px;
}

.care-btn {
  width: 200px;
  height: 60px;
  background-color: #124c5f;
  margin-top: 50px;
  border-radius: 5px;
  line-height: 60px;
  text-align: center;
  cursor: pointer;
}

.care-btn > span {
  font-family: "Glades Bold", "微软雅黑";
  font-size: 17px;
  color: white;
}

.pet-customer {
  margin-bottom: 100px;
}

.pet-customer-content {
  margin-top: 50px;
}

.pet-customer-content > div * {
  display: block;
  margin: 0 auto;
}

.pc-show {
  width: 360px;
  height: 270px;
  border-radius: 33px 33px 0 0;
  position: relative;
}

.customer-img {
  position: absolute;
  left: 5%;
  bottom: -15%;
}

.pc-content {
  width: 360px;
  height: 105px;
  background-color: #124c5f;
  position: relative;
  padding: 20px 0;
}

.pc-content span {
  text-align: center;
}

.pc-name {
  color: #ddca69;
  font-size: 28px;
  font-family: "Rubik", "微软雅黑";
}

.pc-intro {
  color: #ffffff;
  font-size: 14px;
  margin: 15px 0 !important;
  font-family: "Rubik", "微软雅黑";
}

.fa {
  width: 34px;
  height: 14px;
  display: inline-block !important;
  margin-right: 10px !important;
}

.star {
  color: #ffffff;
  font-size: 14px;
  font-family: "Rubik", "微软雅黑";
}

.fa-star {
  background: url("../../assets/img/mark-on.png") no-repeat;
}

.fa-star-o {
  background: url("../../assets/img/mark-off.png") no-repeat;
}

.pc-btn {
  width: 190px;
  height: 60px;
  display: block;
  margin: 85px auto;
  background-color: #124c5f;
}

.pc-btn span {
  color: 16px;
  font-family: "Rubik", "微软雅黑";
}
</style>