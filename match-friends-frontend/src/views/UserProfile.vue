<template>
    <div v-if="currentUser">
        <van-cell-group inset>
            <van-cell title="当前用户" :value="currentUser.username" />
            <div class="img">
                <van-image :src="currentUser.avatarUrl" style="width: 100px; height: 100px;" />
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
import { storeToRefs } from 'pinia'
import { isRef, onMounted } from 'vue'
import router from '../router'

const userStore = useUserStore()
console.log("userStore:", userStore);
const { currentUser } = storeToRefs(userStore)

console.log("currentUser", currentUser);
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