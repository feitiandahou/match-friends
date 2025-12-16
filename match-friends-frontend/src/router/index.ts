import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'
import { useUserStore } from '../stores/user'

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    },
    {
        path: '/search-team',
        name: 'search-team',
        component: () => import('@/views/SearchTeam.vue')
    },
    {
        path: '/user-profile',
        name: 'user-profile',
        component: () => import('@/views/UserProfile.vue')  
    },
    {
        path: '/friends-list',
        name: 'friends-list',
        component: () => import('@/views/FriendsList.vue')
    },
    {
        path: '/user/edit',
        name: 'user-edit',
        component: () => import('@/views/UserEdit.vue')
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})
router.beforeEach(async (to, from, next) => {
    const userStore = useUserStore()
    const isPublicRoute = ['login', 'register'].includes(to.name as string)
    if(isPublicRoute) {
        next()
        return
    }
    if(userStore.currentUser === null) {
        await userStore.fetchCurrentUser()
    }
    if(userStore.isLoggedIn) {
        next()
    } else {
        console.log('login拦截');
        next({ name: 'login' })
    }
})

export default router