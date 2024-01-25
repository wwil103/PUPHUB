<template>
  <div class="AboutView">
    <div class="title">关于我们</div>
    <div class="subhead">PH成立于2017年，是由宠物主人以高标准创建的服务于宠物主人的市场平台，让宠物主人动动手指就可以找到满意的宠物保姆。PH提供定制化的服务；为动物提供无笼式的家庭托管环境并让宠物主人安心。<br><br>PH宠物保姆社区由动物爱好者注册，让他们在帮助宠物主人的同时还可以获得额外的收入。所有的宠物保姆必须具备动物照顾经验，完成面试过程，并经过认证才能成为正式的宠物保姆。<br><br>PH秉持可靠和安全第一的经营理念，将成为您最好的新朋友。</div>
    <el-image :src="src"></el-image>
  </div>
</template>
    
<script>
export default {
  data() {
    return {
      src: "https://puphub.oss-cn-fuzhou.aliyuncs.com/serviceContent/SL8%20copy.jpeg",
    };
  },
};
</script>

<style scoped>
.AboutView {
  width: 1190px;
  margin: 0 auto;
  margin-top: 50px;
}

.title {
  font-size: 2em;
  font-weight: bold;
  margin: 1rem 0;
}
.subhead {
  margin: 1rem 0;
  font-size: 1rem;
  line-height: 1.5;
}
</style>