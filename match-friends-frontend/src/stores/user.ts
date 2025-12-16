import { defineStore } from 'pinia'
import { ref,computed } from 'vue'
import { getCurrentUser } from '../api/user.ts'
        
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
    async function fetchCurrentUser() {
        if(currentUser.value) {
            return currentUser.value
        }
        try {
            const res = await getCurrentUser()
            console.log("res:", res);
            console.log("res.data:", res.data);
            if(res.status === 200) {
                setUser(res.data.data)
                return res.data.data
            }
        } catch (error) {
            console.error('Error fetching current user:', error)
        }
    }

    return {
        currentUser,
        isLoggedIn,
        setUser,
        clearUser,
        fetchCurrentUser
    }
    
 })