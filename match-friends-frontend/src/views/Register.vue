<template>
    <div class="register">
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field
                v-model="userName"
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
                v-model="userPassword"
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
import api from '../api'
import router from '../router'
import { useUserStore } from '../stores/user';
const userStore = useUserStore()
const userAccount = ref('')
const userName = ref('')
const userPassword = ref('')
const confirmPassword = ref('')
const onSubmit = () => {
    if(userPassword.value !== confirmPassword.value){
        console.log("两次密码不一致");
        return
    }
    api.post('/register', { userAccount: userAccount.value, userName: userName.value, userPassword: userPassword.value })
    .then((registerData) => {
        console.log(registerData);
        console.log(registerData.data);
        console.log(registerData.status);
    })
    
    
    
}
</script>

<style scoped>

</style>