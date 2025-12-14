<template>
    <div class="login">
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field
                v-model="username"
                name="用户名"
                label="用户名"
                placeholder="用户名"
                :rules="[{ required: true, message: '请填写用户名' }]"
                />
                <van-field
                v-model="password"
                type="password"
                name="密码"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                提交
                </van-button>
            </div>
        </van-form>
    </div>
</template> 

<script setup lang="ts">
import { ref } from 'vue'
import { useUserStore } from '../stores/user';
import router from '../router'
import api from '../api'
const userStore = useUserStore()

const username = ref('')
const password = ref('')
const onSubmit = () => {
    console.log("提交"+username.value+","+password.value);
    api.post('/register', { username: username.value, password: password.value })
    .then((data) => console.log('注册成功', data));
    // 注册成功后，跳转到登录页面
    router.push('/');
}

</script>

<style scoped>

</style>