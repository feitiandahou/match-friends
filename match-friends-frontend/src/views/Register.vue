<template>
    <div class="register">
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
                v-model="userAccount"
                name="账号"
                label="账号"
                placeholder="账号"
                :rules="[{ required: true, message: '请填写账号' }]"
                />
                <van-field
                v-model="password"
                type="password"
                name="密码"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]"
                />
                <van-field
                v-model="confirmPassword"
                type="password"
                name="确认密码"
                label="确认密码"
                placeholder="确认密码"
                :rules="[{ required: true, message: '请填写确认密码' }]"
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
import api from '../plugins/myAxios'
import router from '../router'
import { useUserStore } from '../stores/user';
import { showToast } from 'vant';

const userStore = useUserStore()
const userAccount = ref('')
const username = ref('')
const password = ref('')
const confirmPassword = ref('')
const onSubmit = async() => {
    if(password.value !== confirmPassword.value){
        console.log("两次密码不一致");
        return
    }
    const res = await api.post('/register', { userAccount: userAccount.value, username: username.value, password: password.value })
    console.log(res,'用户注册');
    console.log(res.data,"data");
    if(res.status === 200 && res.data.code === 0){
        showToast("注册成功");
        // 注册成功后，跳转到登录页面
        router.push('/login');
    }
    else{
        showToast("注册失败"+res.data.msg);
    }
}
</script>

<style scoped>

</style>