<template>
  <div class="AdminView">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card shadow="hover"
                 style="width: 100%;">
          <div id="total"
               style="height: 300px;"></div>
        </el-card>
      </el-col>
    </el-row>
    <div class="main-bottom">
      <el-row :gutter="20">
        <el-col :span="14">
          <el-row>
            <el-col :span="12"
                    v-for="(item,index) in showData"
                    :key="index">
              <el-card class="item-card"
                       shadow="hover"
                       style="width: 100%;">
                <div class="data-item">
                  <el-statistic group-separator=","
                                :value="item.value"
                                :title="item.title">
                    <template slot="prefix">
                      <i :class="item.class"
                         style="color: #FFAD0A"></i>
                    </template>
                  </el-statistic>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="10">
          <el-card shadow="hover"
                   style="width: 100%;">
            <div id="order"
                 style="height: 280px;"></div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  data() {
    return {
      showData: [
        {
          title: "进行中订单",
          value: 0,
          precision: "0",
          class: "el-icon-star-on",
        },
        {
          title: "用户总数",
          value: 0,
          precision: "0",
          class: "el-icon-s-custom",
        },
        {
          title: "订单总数",
          value: 0,
          precision: "0",
          class: "el-icon-s-order",
        },
        {
          title: "服务总数",
          value: 0,
          precision: "0",
          class: "el-icon-s-ticket",
        },
      ],
      //饼图数据
      orderOption: {
        backgroundColor: "#fff",
        title: {
          text: "订单状态占比",
          left: "center",
          top: 20,
          textStyle: {
            color: "#ccc",
          },
        },
        tooltip: {
          trigger: "item",
        },
        visualMap: {
          show: false,
          min: 0,
          max: 600,
          inRange: {
            colorLightness: [0, 1],
          },
        },
        series: [
          {
            name: "订单状态",
            type: "pie",
            radius: "55%",
            center: ["50%", "50%"],
            data: [
              { value: 335, name: "服务待开始" },
              { value: 310, name: "服务进行中" },
              { value: 274, name: "服务已完成" },
            ].sort(function (a, b) {
              return a.value - b.value;
            }),
            roseType: "radius",
            label: {
              color: "rgba(255, 255, 255, 0.3)",
            },
            labelLine: {
              lineStyle: {
                color: "rgba(255, 255, 255, 0.3)",
              },
              smooth: 0.2,
              length: 10,
              length2: 20,
            },
            itemStyle: {
              color: "#FFD9A1",
              shadowBlur: 200,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
            animationType: "scale",
            animationEasing: "elasticOut",
          },
        ],
      },
      //折线图数据
      totalOption: {
        color: ["#FFC16A", "#FFAA40", "#FA8B15"],
        title: {
          text: "",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#FFAA00",
            },
          },
        },
        legend: {
          data: ["服务数", "用户数", "订单数"],
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
          },
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              // 隐藏标签
              show: false,
            },
          },
        ],
        series: [
          {
            name: "服务数",
            type: "line",
            stack: "Total",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(255, 232, 188)",
                },
                {
                  offset: 1,
                  color: "rgb(255, 193, 106)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [120, 232, 101, 264, 90, 340, 250],
          },
          {
            name: "用户数",
            type: "line",
            stack: "Total",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(254, 213, 147)",
                },
                {
                  offset: 1,
                  color: "rgb(255, 170, 64)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [120, 282, 111, 234, 220, 340, 310],
          },
          {
            name: "订单数",
            type: "line",
            stack: "Total",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(255, 193, 106)",
                },
                {
                  offset: 1,
                  color: "rgb(250, 139, 21)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [320, 132, 201, 334, 190, 130, 220],
          },
        ],
      },
    };
  },
  methods: {
    getNearly7Day() {
      let days = [];
      var date = new Date();
      for (let i = 0; i <= 24 * 6; i += 24) {
        //今天加上前6天
        let dateItem = new Date(date.getTime() - i * 60 * 60 * 1000); //使用当天时间戳减去以前的时间毫秒（小时*分*秒*毫秒）
        let y = dateItem.getFullYear(); //获取年份
        let m = dateItem.getMonth() + 1; //获取月份js月份从0开始，需要+1
        let d = dateItem.getDate(); //获取日期
        m = addDate0(m); //给为单数的月份补零
        d = addDate0(d); //给为单数的日期补零
        let valueItem = y + "-" + m + "-" + d; //组合
        days.unshift(valueItem); //添加至数组
      }
      //给日期加0
      function addDate0(time) {
        if (time.toString().length == 1) {
          time = "0" + time.toString();
        }
        return time;
      }
      return days;
    },

    orderData() {
      const self = this;
      self.$axios
        .get("/order/info/data")
        .then((res) => {
          self.showData[2].value = res.data.orderTotal;
          self.showData[0].value = res.data.orderActive;
          self.orderOption.series[0].data[0].value = res.data.orderEnd * 100;
          self.orderOption.series[0].data[1].value = res.data.orderMiddle * 100;
          self.orderOption.series[0].data[2].value = res.data.orderStart * 100;

          // 渲染订单时间数据
          self.totalOption.xAxis[0].data = self.getNearly7Day();
          let arr = [];
          self.totalOption.xAxis[0].data.forEach((element) => {
            let num = res.data.orderTime[element] * 100;
            arr.push(num);
          });
          self.totalOption.series[2].data = arr;
          // 饼图
          var orderDom = document.getElementById("order");
          var orderChart = echarts.init(orderDom);
          orderChart.setOption(this.orderOption);
          //折线图
          var totalDom = document.getElementById("total");
          var totalChart = echarts.init(totalDom);
          totalChart.setOption(this.totalOption);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    userData() {
      const self = this;
      self.$axios
        .get("/user/info/all")
        .then((res) => {
          self.showData[1].value = res.data.length - 1;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    userTimeData() {
      const self = this;
      self.$axios
        .get("/user/info/data")
        .then((res) => {
          // 渲染用户时间数据
          self.totalOption.xAxis[0].data = self.getNearly7Day();
          let arr = [];
          self.totalOption.xAxis[0].data.forEach((element) => {
            let num = res.data.userTime[element] * 100;
            arr.push(num);
          });
          self.totalOption.series[1].data = arr;

          //折线图
          var totalDom = document.getElementById("total");
          var totalChart = echarts.init(totalDom);
          totalChart.setOption(this.totalOption);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    serviceTimeData() {
      const self = this;
      self.$axios
        .get("/service/info/data")
        .then((res) => {
          // 渲染服务时间数据
          self.totalOption.xAxis[0].data = self.getNearly7Day();
          let arr = [];
          self.totalOption.xAxis[0].data.forEach((element) => {
            let num = res.data.serviceTime[element] * 100;
            arr.push(num);
          });
          self.totalOption.series[0].data = arr;

          //折线图
          var totalDom = document.getElementById("total");
          var totalChart = echarts.init(totalDom);
          totalChart.setOption(this.totalOption);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    serviceData() {
      const self = this;
      self.$axios
        .get("/service/info/all")
        .then((res) => {
          self.showData[3].value = res.data.length;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted() {
    this.orderData();
    this.userData();
    this.serviceData();
    this.userTimeData();
    this.serviceTimeData();
  },
};
</script>

<style scoped>
.main-bottom {
  margin-top: 10px;
}

.item-card {
  height: 159px;
  text-align: center;
  line-height: 60px;
}

::v-deep .title {
  font-weight: bold;
  font-size: 1.25rem;
}

::v-deep .number {
  font-weight: bold;
  font-family: "Pangolin", "微软雅黑";
}
</style>