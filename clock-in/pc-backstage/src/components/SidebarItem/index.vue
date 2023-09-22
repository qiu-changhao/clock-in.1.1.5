<template>
  <div :class="{'has-logo':showLogo}"
       :style="{ backgroundColor: settings.sideTheme === 'theme-dark' ? '#304156' : variables.menuLightBackground }">
    <logo v-if="showLogo" :collapse="isCollapse"/>
    <el-scrollbar :class="settings.sideTheme" wrap-class="scrollbar-wrapper">
      <el-menu
          :default-active="activeMenu"
          :collapse="isCollapse"
          :background-color="settings.sideTheme === 'theme-dark' ? '#304156' : variables.menuLightBackground"
          :text-color="settings.sideTheme === 'theme-dark' ? '#bfcbd9' : variables.menuLightColor"
          :unique-opened="true"
          :active-text-color="settings.theme"
          :collapse-transition="false"
          mode="vertical"
      >
        <sidebar-item
            v-for="(route, index) in sidebarRouters"
            :key="route.path  + index"
            :item="route"
            :base-path="route.path"
        />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import SidebarItem from './SidebarItem'
import Logo from '@/components/Logo'
import variables from "@/assets/styles/variables.scss";
import {mapGetters, mapState} from "vuex";

export default {
  name: 'SidebarItemPanel',
  components: {
    SidebarItem,
    Logo
  },
  computed: {
    ...mapState(["settings"]),
    ...mapGetters(['sidebarRouters', "sidebar"]),
    activeMenu() {
      const route = this.$route
      const {meta, path} = route
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo;
    },
    variables() {
      return variables;
    },
    isCollapse() {
      return !this.sidebar.opened;
    }
  }
}
</script>

<style scoped>

</style>
