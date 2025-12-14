<template>
    <nav>
        <van-nav-bar
            title="标题"
            right-text="按钮"
            left-arrow
            background="linear-gradient(90deg, #D09D69 0%, #E0C1A1 100%)"
            text-light
            @click-left="onClickLeft"
            @click-right="onClickRight"
        />
        <div class="link">
            <router-link to="/">Home</router-link>
            <router-link to="/login">Login</router-link>
            <router-link to="/register">Register</router-link>
            <router-link to="/user-profile">User Profile</router-link>
        </div>
    </nav>
    
</template>

<script setup lang="ts">

</script>

<style scoped>
.link {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>