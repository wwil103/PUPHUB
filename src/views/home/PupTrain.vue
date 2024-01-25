<template>
  <div class="pet-train-product">
    <div class="pet-train">
      <div class="pet-train-content">
        <span class="train-first-title">Dogs do speak, but only to those<br> who know how to listen.</span>
        <span class="train-second-title">Human–canine bonding is the relationship<br> between dogs and humans.</span>
        <router-link to="/services">
          <div class="train-btn">
            <span>EXPLORE MORE</span>
            <i class="el-icon-arrow-right"></i>
          </div>
        </router-link>
      </div>
    </div>
    <div class="pet-product">
      <div class="pr-top">
        <div class="sc-title">Dog Nutrients & Food</div>
        <div class="sc-hr"></div>
      </div>
      <div class="pet-product-more">
        <span>25 % OFF all </span><span>Products</span>
        <el-button type="primary">View More<i class="el-icon-arrow-right el-icon--right"></i></el-button>
      </div>
      <div class="pet-product-content">
        <el-row :gutter="20">
          <el-col :span="8"
                  v-for="pp in ppContent"
                  :key="pp.id">
            <div><img class="product-img"
                   :src="pp.pImg"><img :src="pp.sImg"><span class="pp-title">{{ pp.title }}</span><span class="pp-content">{{ pp.content }}</span>
              <el-button class="pp-btn">Buy Now</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PupTrain",
  data() {
    return {
      ppContent: [
        {
          id: "01",
          sImg: "/img/product-show1.png",
          pImg: require("@/assets/img/pet-product1.png"),
          title: "Drools | 3KG",
          content: "Adult chicken and egg Egg, C",
        },
        {
          id: "02",
          sImg: "/img/product-show2.png",
          pImg: require("@/assets/img/pet-product2.png"),
          title: "Canine Creek 4 KG",
          content: "Adult chicken and egg Egg, C",
        },
        {
          id: "03",
          sImg: "/img/product-show3.png",
          pImg: require("@/assets/img/pet-product3.png"),
          title: "Biscork Biscuits",
          content: "Adult chicken and egg Egg, C",
        },
      ],
    };
  },
};
</script>

<style>
.pet-train-product {
  width: 1190px;
  margin: 0 auto;
}

.pet-train {
  width: 1312px;
  height: 758px;
  background: url("../../assets/img/pet-train.png") no-repeat;
  position: relative;
  left: -170px;
}

.pet-train-content {
  position: absolute;
  right: 5%;
  top: 15%;
}

.pet-train-content > * {
  display: block;
}

.train-first-title {
  color: #000;
  font-family: "Pangolin", "微软雅黑";
  font-size: 34px;
  letter-spacing: 1px;
  line-height: 60px;
}

.train-second-title {
  margin-top: 10px;
  color: #181817;
  font-family: "Roboto", "微软雅黑";
  font-size: 19px;
  letter-spacing: 1px;
  line-height: 30px;
}

.train-btn {
  width: 200px;
  height: 60px;
  background-color: #124c5f;
  margin-top: 50px;
  border-radius: 5px;
  line-height: 60px;
  text-align: center;
  cursor: pointer;
}

.train-btn > span {
  font-family: "Glades Bold", "微软雅黑";
  font-size: 17px;
  color: white;
}

.pet-product {
  width: 1190px;
  margin: 0 auto;
}

.pr-top {
  padding: 0 35px;
}

.pet-product-more {
  margin-top: 40px;
  padding: 0 35px;
  height: 60px;
  line-height: 60px;
}

.pet-product-more:nth-child(-n + 2) {
  font-size: 40px;
}

.pet-product-more > span:first-child {
  color: #000;
  font-family: "Glades Bold", "微软雅黑";
}

.pet-product-more:nth-child(2) {
  color: #000;
  font-family: "Pangolin", "微软雅黑";
}

.pet-product-more > button {
  float: right;
  text-align: center;
  height: 60px;
  width: 200px;
  font-size: 16px;
  font-family: "Rubik", "微软雅黑";
  background-color: #124c5f;
  margin-right: 25px;
}

.pet-product-content,
.pet-product-content > div > div:last-child {
  margin-top: -60px;
}

.pet-product-content > div {
  height: 700px;
  text-align: center;
}

.pet-product-content > div * {
  display: block;
  margin: 0 auto;
}

.pp-main {
  height: 450px;
}

.pp-title {
  font-size: 28px;
  font-family: "Rubik", "微软雅黑";
  margin-top: 20px !important;
}

.pp-content {
  font-size: 22px;
  font-family: "Rubik", "微软雅黑";
  color: #979697;
  margin-bottom: 40px !important;
}

.product-img {
  position: relative;
  bottom: -120px;
  height: 312px;
}

.pp-btn {
  width: 185px;
  border: 2px solid #124c5f !important;
}

.pp-btn span {
  color: #124c5f;
  font-size: 22px;
}
</style>