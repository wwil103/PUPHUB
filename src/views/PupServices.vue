<template>
  <div class="petServices">
    <div class="services-page-title">
      PH认证宠物保姆为您服务•••
    </div>
    <!-- <div class="services-page-button">
      <el-button plain>所有服务</el-button>
      <div v-for="ps in psContent"
           :key="ps.serviceId">
        <el-button plain>{{ ps.service }}</el-button>
      </div>
    </div> -->
    <div class="services-page-content">
      <el-row class="ps-content"
              :gutter="20">
        <el-col :span="8"
                v-for="ps in psContent"
                :key="ps.sId">
          <el-card shadow="always"
                   class="ps-card">
            <a class="service">
              <div class="service-top">
                <img :src="ps.sPoster"
                     class="service-icon">
                <div class="service-name">
                  {{ ps.sName }}
                </div>
                <div class="service-price">
                  {{ ps.sPrice + '￥ 起' }}
                </div>
              </div>
              <div class="service-description">
                {{ ps.sIntro }}
              </div>
              <div class="service-link">
                <el-button @click="clickQuery(ps.sId)"
                           class="black-button"
                           plain>了解详情</el-button>
              </div>
            </a>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "PupServices",
  data() {
    return {
      psContent: [],
    };
  },
  methods: {
    // 服务数据查询
    serviceData() {
      const self = this;
      self.$axios
        .get("/service/info/all")
        .then((res) => {
          self.psContent = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    clickQuery(num) {
      num = num - 1;
      this.$router.push({
        name: "ServiceContent",
        query: {
          active: num,
        },
      });
    },
  },
  created() {
    this.serviceData();
  },
};
</script>

<style scoped>
.petServices {
  width: 990px;
  margin: 20px auto;
  margin-bottom: 50px;
}

.services-page-title {
  display: inline-block;
  font-size: 32px;
  font-weight: bold;
  margin: 20px 0;
  text-align: center;
  width: 100%;
}

/* .services-page-button {
  text-align: center;
}

.services-page-button * {
  display: inline-block;
}

.services-page-button button {
  width: 190px;
  height: 60px;
  margin: 10px 30px;
  background: #ffffff;
  color: #ffaa00;
  font-size: 20px;
  border-radius: 10px;
  border: 1px solid #ffaa00;
}

.services-page-button > button:first-child {
  background: #ffaa00;
  color: #ffffff;
  border: 1px solid #ffaa00;
} */

.ps-content > div {
  margin: 10px 0;
}

.ps-card {
  position: relative;
  height: 250px;
}

.service-top {
  height: 100px;
  margin-bottom: 20px;
}

.service-icon {
  max-width: 200px;
  position: absolute;
  right: -15%;
  top: -5%;
}

.service-name {
  font-size: 25px;
  color: #124c5f;
  font-weight: bold;
}

.service-price {
  font-size: 15px;
  color: #124c5f;
  font-weight: bold;
  margin: 20px 0;
  margin-bottom: 50px;
}

.service-description {
  font-size: 13px;
  color: black;
  opacity: 0.5;
}

.service-link {
  margin-top: 10px;
  text-align: center;
}

.black-button {
  width: 100%;
}
</style>