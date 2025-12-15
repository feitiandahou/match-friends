import { defineStore } from 'pinia'
import { ref,computed } from 'vue'

export const useUserStore = defineStore('user', () => {
    const currentUser = ref<any>(null)
    
    const isLoggedIn = computed(() => {
        return currentUser.value !== null
    })

    const setUser = (user: any) => {
        currentUser.value = user
    }
    const clearUser = () => {
        setUser(null)
    }

    return {
        currentUser,
        isLoggedIn,
        setUser,
        clearUser
    }
    
 })