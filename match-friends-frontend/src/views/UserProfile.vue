<template>
    <div v-if="user">
        <van-cell-group inset>
            <van-cell title="当前用户" :value="user.username" />
            <div class="img">
                <van-image :src="user.avatarUrl" style="width: 100px; height: 100px;" />
            </div>
            <van-cell title="我创建的队伍" value="暂无" />
            <van-cell title="我加入的队伍" value="暂无" />
            <div class="button">
                <van-button @click="handleUpdateUserInfo" plain hairline type="success">修改信息</van-button>
            </div>
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

const handleUpdateUserInfo = () => {
    console.log('修改用户信息');
    router.push({
        path: '/user/edit',
    })
}
</script>

<style scoped>
.button {
    display: flex;
    justify-content: center;
}
</style>