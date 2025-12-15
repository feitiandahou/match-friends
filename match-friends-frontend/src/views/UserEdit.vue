<template>
    <div>
        <van-cell-group inset>
        <van-field v-model="user.username" label="用户名" />
        <div class="img">
            头像
            <van-image :src="user.avatarUrl" style="width: 100px; height: 100px;" />
            <van-button @click="handleUploadAvatar" plain hairline type="primary">上传头像</van-button>
        </div>
        <van-field v-model="user.userAccount" type="tel" label="账号" />
        <van-field v-model="user.password" type="password" label="密码" />
        <van-field v-model="user.gender"  label="性别" />
        <van-field v-model="user.age" type="digit" label="年龄" />
        <van-field v-model="user.tags" label="标签" />
        <van-button type="warning">注销用户</van-button>
        <van-button type="primary">确认修改</van-button>
        </van-cell-group>

    </div>
</template>
<script setup lang="ts">
import { useUserStore } from '../stores/user';
import { isRef, onMounted } from 'vue'
import router from '../router'

const userStore = useUserStore()
const user = userStore.currentUser
console.log(user,'user');
</script>

<style scoped>

</style>