<template>
  <div class="main-wrapper" :class="[mainWrapperClass]">
    <!---Navigation-->
    <Navbar :topbarColor="topbarColor" :logo="require('@/assets/images/logo/logo-light-icon.png')" :title="logotitle"/>
    <!---Sidebar-->
    <SideBar parent=".main-wrapper" :sidebarLinks="sidebarLinks"/>
    <!---Page Container-->
    <div class="main-container-fluid">
      <transition name="router-anim" enter-active-class="fade-enter-active fade-enter"
                  leave-active-class="fade-leave-active fade-enter">
        <router-view></router-view>
      </transition>
      <!---Customizer / Settings-->
      <Customizer @updateTopbarColor="updateTopbarColor"/>

    </div>
  </div>
</template>

<script>

import Navbar from '@/layout/full/header/Navbar.vue';
import sidebarlinks from "@/layout/full/sidebar/sidebarlinks";
import SideBar from '@/layout/full/sidebar/SideBar.vue';
import themeConfig from '@/../themeConfig.js';

export default {
  name: "MainContainer",
  components: {
    //barra menu
    Navbar,
    //barra avatar y dashboards
    SideBar,
  },
  data: () => ({
    topbarColor: themeConfig.topbarColor,
    logotitle: themeConfig.logotitle,
    sidebarLinks: sidebarlinks,
  }),
  methods: {
    updateTopbarColor(val) {
      this.topbarColor = val;
    }
  },
  computed: {
    sidebarWidth: function () {
      return this.$store.state.sidebarWidth;
    },
    //resolucion de pantalla
    mainWrapperClass: function () {
      if (this.sidebarWidth == "default") {
        return "main-wrapper-default"
      } else if (this.sidebarWidth == "mini") {
        return "main-wrapper-mini"
      } else if (this.sidebarWidth) {
        return "main-wrapper-full"
      }
      return "default"
    },

  },

}
</script>