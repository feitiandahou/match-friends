<template>
    <div>
        count: {{ count }}<br>
        double: {{ double }}<br>
        <button @click="handleClick">increment</button>
        <van-button type="primary">Primary Button</van-button>
    </div>
</template>

<script setup lang="ts">
import { useCounterStore } from '../stores/counter'
import { storeToRefs } from 'pinia'
import { watch } from 'vue'
const counterStore = useCounterStore()
const {count, double} = storeToRefs(counterStore)

const handleClick = () => {
    useCounterStore().increment()
}
watch(count, (newCount) => {
    console.log('count changed', newCount)
})
</script>

<style scoped>

</style>