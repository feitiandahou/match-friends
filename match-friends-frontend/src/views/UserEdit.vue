<template>
    <div>
        <van-cell-group inset>
        <van-field v-model="user.username" label="用户名" />
        <div class="img">
            头像
            <van-image :src="user.avatarUrl" style="width: 100px; height: 100px;" />
            <van-button plain hairline type="primary">上传头像(暂未实现)</van-button>
        </div>
        <van-field v-model="user.userAccount" type="tel" label="账号" />
        <van-field v-model="user.password" type="password" label="密码" />
        <van-field v-model="user.gender"  label="性别" />
        <van-field v-model="user.age" type="digit" label="年龄" />
        <van-field v-model="user.tags" label="标签" />
        <van-button type="warning" @click="handleLogout">注销用户</van-button>
        <van-button type="primary" @click="handleSubmit">确认修改</van-button>
        </van-cell-group>

    </div>
</template>
<script setup lang="ts">
import { useUserStore } from '../stores/user';
import { reactive, watch } from 'vue'
import { updateUser } from '../api/user.ts'
import { showConfirmDialog } from 'vant';
import router from '../router'

const userStore = useUserStore()
const user = userStore.currentUser
console.log(user,'user');
const form = reactive({
  id: '',
  username: '',
  userAccount: '',
  avatarUrl: '',
  password: '',
  gender: '',
  age: '',
  tags: '',
})
watch(
  () => userStore.currentUser,
  (user) => {
    if (!user) return
    Object.assign(form, {
      id: user.id,
      username: user.username,
      userAccount: user.userAccount,
      avatarUrl: user.avatarUrl,
      gender: user.gender,
      age: user.age,
      tags: user.tags,
      password: user.password,
    })
  },
  { immediate: true }
)
const handleSubmit = async () => {
    try{
        await updateUser(form)
    } catch (error) {
        console.error('更新用户信息失败:', error);
    }
}
const handleLogout = async () => {
    await showConfirmDialog({
        title: '确认注销',
        message: '确定要注销当前用户吗？',
    })
    userStore.clearUser()
    router.push({
        path: '/login',
    })
}


</script>

<style scoped>

</style>