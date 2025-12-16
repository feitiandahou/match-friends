<template>
    <div class="login">
        <van-form @submit="onSubmit">
            <van-cell-group inset>
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
import api from '../plugins/myAxios'
import { showToast } from 'vant';

const userStore = useUserStore()

const userAccount = ref('')
const password = ref('')
const onSubmit = async () => {
    const res = await api.post('/login', { userAccount: userAccount.value, password: password.value })
    if(res.status === 200 && res.data.code === 0){
        showToast("登录成功");
        // 登录成功后，将用户信息存储到 Pinia 中
        console.log(res.data.data,'res.data.data');
        userStore.setUser(res.data.data)
        // 登录成功后，跳转到首页
        router.push('/');
    }
    else{
        showToast("登录失败"+res.data.msg);
    }
}

</script>

<style scoped>

</style>