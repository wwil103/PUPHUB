<template>
  <div class="ContactView">
    <div class="title">有疑问？ <a href="/help">我们在此提供帮助！</a></div>
    <div class="subhead">还有疑问？</div>
    <div class="subhead">添加我们的微信</div>
    <el-image class="img"
              :src="src"></el-image>
    <div class="subhead">请关注我们官方微信公众号，获取最新的宠物资讯、近期宠物活动以及更多的萌宠动态！</div>
    <el-image class="img"
              style="width: 300px;height:300px"
              :src="srcTo"></el-image>
  </div>
</template>

<script>
export default {
  data() {
    return {
      src: "https://puphub.oss-cn-fuzhou.aliyuncs.com/20231127003720.jpg",
      srcTo: "https://puphub.oss-cn-fuzhou.aliyuncs.com/20231127003726.jpg",
    };
  },
};
</script>

<style scoped>
.ContactView {
  width: 1190px;
  margin: 0 auto;
  margin-top: 50px;
  text-align: center;
}

.title {
  font-size: 1.5em;
  font-weight: bold;
  margin: 1rem 0;
}

.subhead {
  font-size: 1.5em;
  margin: 3rem 0;
}

a {
  text-decoration: underline;
}

.img {
  width: 300px;
  height: 400px;
}
</style>