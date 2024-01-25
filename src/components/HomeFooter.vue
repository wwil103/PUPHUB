<template>
  <div class="main-footer"
       id="contact">
    <div class="footer-container">
      <div class="footer-top">
        <span>Join us with Affiliate program</span>
      </div>
      <div class="footer-email">
        <div class="email-bg">
          <div class="email-left">
            <i class="el-icon-message"></i>
            <span>Type your email address here</span>
          </div>
          <div class="email-right">
            <div>
              <span>Submit now</span>
              <i class="el-icon-arrow-right"></i>
            </div>
          </div>
        </div>
      </div>
      <div class="footer-contact">
        <div class="footer-left">
          <span class="contact-top-title">Follow on social</span>
          <div class="contact-left-bottom">
            <i class="el-icon-s-promotion"></i>
            <i class="el-icon-video-camera"></i>
          </div>
        </div>
        <div class="footer-right">
          <div>
            <span class="contact-top-title">Boarding & Day care</span>
            <span class="contact-tel">(616)-459-8622</span>
            <span class="contact-add">2125 Howard Street ,Grand Rapids, MI Michigan</span>
          </div>
          <div>
            <span class="contact-top-title">Health care</span>
            <span class="contact-tel">(616)-566-5896</span>
            <span class="contact-add">2125 Howard Street ,Grand Rapids, MI Michigan</span>
          </div>
        </div>
      </div>
    </div>
    <div class="footer-bottom">
      <div class="footer-content">
        <div class="footer-left">
          <span>© all rights reserved & design with love by bharat</span>
        </div>
        <div class="footer-right">
          <ul class="footer-nav clearfix">
            <li><a href="#">OUR SERVICES</a></li>
            <li><a href="#">ABOUT US</a></li>
            <li><a href="#">PRODUCTS</a></li>
            <li><a href="#">PET CARE</a></li>
            <li><a href="#">CONTACT</a></li>
          </ul>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "HomeFooter",
};
</script>

<style>
.main-footer {
  width: 100%;
  background-color: #f6faf9;
}

.footer-container,
.footer-content {
  width: 1190px;
  margin: 0 auto;
}

.footer-top {
  padding-top: 50px;
  height: 100px;
  text-align: center;
  line-height: 100px;
}

.footer-top > span {
  font-size: 2em;
  font-family: "Pangolin", "微软雅黑";
}

.email-bg {
  margin: 0 auto;
  background: white;
  width: 750px;
  height: 120px;
  border-radius: 5px;
  line-height: 120px;
  position: relative;
}

.email-bg img {
  margin-left: 20px;
}

.email-left {
  display: inline-block;
}

.email-right {
  display: inline-block;
  position: absolute;
  right: 0;
}

.email-left > .el-icon-message {
  font-size: 30px;
  margin: 0 20px 0 40px;
  vertical-align: middle;
  color: #d2cfcf;
}

.email-left > span {
  font-family: "Roboto", "微软雅黑";
  color: #414141;
  font-size: 19px;
}

.email-right > div {
  display: inline-block;
  width: 200px;
  height: 60px;
  margin-right: 40px;
  background-color: #124c5f;
  border-radius: 5px;
  line-height: 60px;
  text-align: center;
}

.email-right span {
  font-family: "Rubik", "微软雅黑";
  font-size: 17px;
  color: white;
  margin-right: 10px;
}

.footer-contact {
  margin-top: 80px;
  height: 150px;
}

.footer-left,
.footer-right {
  display: inline-block;
  width: 50%;
}

.contact-top-title {
  font-family: "Rubik", "微软雅黑";
  font-size: 14px;
  color: #000;
  font-weight: bold;
  margin-left: 10px;
}

.contact-tel {
  font-size: 22px;
  color: rgb(18, 76, 95);
  font-weight: bold;
  font-family: "Roboto", "微软雅黑";
  margin: 10px 0;
  margin-left: 10px;
}

.contact-add {
  font-size: 13px;
  color: rgb(117, 119, 119);
  font-family: "Roboto", "微软雅黑";
  margin-left: 10px;
}

.contact-left-bottom > i {
  background-color: rgb(196, 196, 196);
  font-size: 30px;
  border-radius: 100%;
  padding: 10px;
  margin: 20px 10px;
}

.footer-right > div {
  display: inline-block;
}

.footer-right span {
  display: block;
}

.footer-bottom {
  border-top: 1px solid rgb(199, 199, 199);
  height: 80px;
}

.footer-content span {
  line-height: 80px;
}
.footer-content li {
  float: left;
  margin-right: 30px;
}

.footer-content a {
  color: #000;
  font-family: "Glades Bold", "微软雅黑";
  font-size: 14px;
}
</style>