<template>
    <div class="tab-bar">
        <van-tabbar v-model="active">
            <van-tabbar-item replace to="/" icon="home-o">标签</van-tabbar-item>
            <van-tabbar-item replace to="/search-team" icon="search">标签</van-tabbar-item>
            <van-tabbar-item replace to="/friends-list" icon="friends-o">标签</van-tabbar-item>
            <van-tabbar-item replace to="/user-profile" icon="setting-o">标签</van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
const active = ref(0)
</script>

<style scoped>

</style>