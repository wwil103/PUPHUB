<template>
  <div id="PupHome">
    <OurServices />
    <PupTrain />
    <PupCare />
  </div>
</template>

<script>
import OurServices from "./home/OurServices";
import PupTrain from "./home/PupTrain";
import PupCare from "./home/PupCare";
export default {
  name: "PupHome",
  components: {
    OurServices,
    PupTrain,
    PupCare,
  },
};
</script>

<style>
</style>