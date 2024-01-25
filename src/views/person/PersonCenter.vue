<template>
  <div class="PersonCenter">
    <div class="person-top">
      <div class="person-content">
        <div>
          <el-avatar class="person-avatar"
                     src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </div>
        <div class="person-content-right">
          <div class="person-name"><span>{{ user.name }}</span><span class="person-tag">PUP OWNER</span></div>
          <div class="person-address"><span>{{ user.address }}</span></div>
        </div>
      </div>
      <div class="person-setting-btn">
        <router-link to="/person/edit"><el-button type="info"
                     icon="el-icon-setting"></el-button></router-link>
        <router-link to="/admin"
                     v-if="isAdmin"><el-button type="info"
                     icon="el-icon-service"></el-button></router-link>
      </div>
    </div>
    <div class="person-order">
      <div><span>ORDER</span>
        <div class="title-decoration">
          <div class="title-spot"></div>
          <div class="title-hr"></div>
        </div>
      </div>
      <el-table :data="tableData"
                style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-steps :active="active(props.row.oStatus)">
              <el-step title="下单完成"
                       :description="props.row.oOrderTime"></el-step>
              <el-step :title="'服务进行中（' + formatterService(props.row) + '）'"
                       :description="props.row.oAddress"></el-step>
              <el-step title="服务完成"
                       description="..."></el-step>
            </el-steps>
          </template>
        </el-table-column>
        <el-table-column label="订单 ID"
                         prop="oId"
                         width="80">
        </el-table-column>
        <el-table-column label="服务名称"
                         prop="oService"
                         width="100"
                         :formatter="formatterService">
        </el-table-column>
        <el-table-column prop="oDate"
                         label="服务日期"
                         width="120"
                         sortable>
        </el-table-column>
        <el-table-column prop="oTime"
                         label="时间段"
                         width="80"
                         :formatter="formatterTime">
        </el-table-column>
        <el-table-column prop="oAge"
                         label="狗狗年龄"
                         width="90"
                         :formatter="formatterAge">
        </el-table-column>
        <el-table-column prop="oSize"
                         label="狗狗大小"
                         width="90"
                         :formatter="formatterSize">
        </el-table-column>
        <el-table-column prop="oContact"
                         width="150"
                         label="联系方式">
        </el-table-column>
        <el-table-column label="地址"
                         prop="oAddress">
        </el-table-column>
        <el-table-column label="下单时间"
                         prop="oOrderTime"
                         sortable>
        </el-table-column>
        <el-table-column prop="oStatus"
                         label="订单状态"
                         width="120"
                         sortable>
          <template slot-scope="props">
            <el-tag :type="formatStatusTag(props.row.oStatus)">{{ formatterStatus(props.row) }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <div v-show="isTableDate"
           class="subhead">还没有预约宠物服务？ <a href="/services">点我前往预约</a></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isTableDate: false,
      service: [],
      tableData: [],
      user: [],
      isAdmin: "",
    };
  },
  methods: {
    // 步骤条状态
    active(oStatus) {
      return parseInt(oStatus) + 1;
    },

    //表单数据格式化(服务)
    formatterService(row) {
      var a = "";
      this.service.forEach((element) => {
        if (row.oService == element.value) a = element.label;
      });
      return a;
    },

    //表单数据格式化(时间)
    formatterTime(row) {
      switch (row.oTime) {
        case "1":
          return "早上";
        case "2":
          return "下午";
        case "3":
          return "晚上";
        default:
          return "未填写";
      }
    },

    //表单数据格式化(狗狗年龄)
    formatterAge(row) {
      switch (row.oAge) {
        case "1":
          return "幼犬";
        case "2":
          return "成年犬";
        case "3":
          return "老年犬";
        default:
          return "未填写";
      }
    },

    //表单数据格式化(狗狗大小)
    formatterSize(row) {
      switch (row.oSize) {
        case "1":
          return "小型犬";
        case "2":
          return "中型犬";
        case "3":
          return "大型犬";
        default:
          return "未填写";
      }
    },

    //表单数据格式化(订单状态)
    formatterStatus(row) {
      switch (row.oStatus) {
        case "0":
          return "服务待开始";
        case "1":
          return "服务进行中";
        case "2":
          return "服务已完成";
        default:
          return "未填写";
      }
    },

    formatStatusTag(row) {
      switch (row) {
        case "1":
          return "";
        case "2":
          return "warning";
        case "3":
          return "success";
        default:
          return "未填写";
      }
    },

    userData() {
      const self = this;
      const uId = localStorage.getItem("user");
      self.$axios
        .get("/user/info/" + uId)
        .then((res) => {
          this.user = res.data;
          if (res.data.id === 1) {
            this.isAdmin = true;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //服务数据查询
    serviceData() {
      const self = this;
      self.$axios
        .get("/service/info/all")
        .then((res) => {
          res.data.forEach((element) => {
            let arr = {};
            arr.label = element.sName;
            arr.value = element.sId;
            self.service.push(arr);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    orderData() {
      const self = this;
      const uId = localStorage.getItem("user");
      self.$axios
        .get("/order/info/" + uId)
        .then((res) => {
          if (res.data.length == 0) this.isTableDate = true;
          this.tableData = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.serviceData(); //服务数据加载
    this.userData();
    this.orderData();
  },
};
</script>

<style scoped>
.PersonCenter {
  width: 1190px;
  margin: 0 auto;
  margin-top: 50px;
}

.person-top {
  display: flex;
}

.person-top > div {
  flex: 1;
}

.person-avatar {
  width: 128px;
  height: 128px;
  margin-left: 100px;
}

.person-content > div {
  display: inline-block;
}

.person-content-right {
  position: absolute;
  top: 20%;
  margin-left: 20px;
  line-height: 64px;
}

.person-name > span:first-child {
  vertical-align: middle;
  font-size: 2em;
  font-family: "Pangolin", "微软雅黑";
}

.person-name > span:last-child {
  font-family: "Glades Bold", "微软雅黑";
}

.person-tag {
  padding: 5px;
  background: #124c5f;
  color: #fff;
  border-radius: 20px;
  margin-left: 10px;
}

.person-setting-btn {
  margin: auto 0;
}

.person-setting-btn > button {
  font-size: 20px;
  margin-left: 100px;
}

.person-setting-btn > a {
  margin-left: 100px;
}

.person-order {
  margin-top: 40px;
  margin-bottom: 80px;
  margin-left: 100px;
  font-size: 20px;
  color: #000;
  font-family: "Pangolin", "微软雅黑";
}

.title-hr {
  background-color: #124c5f;
  width: 85px;
  height: 5px;
  border-radius: 5px;
}

.title-spot {
  background-color: #124c5f;
  width: 20px;
  height: 20px;
  border-radius: 50px;
  position: relative;
  bottom: -5px;
  left: -10px;
}
.title-decoration {
  position: relative;
  left: -15px;
  top: -20px;
}

.subhead {
  margin-top: 30px;
  text-align: center;
}

.subhead a {
  text-decoration: underline;
}
</style>