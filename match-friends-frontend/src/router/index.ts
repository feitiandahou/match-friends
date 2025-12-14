import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'

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
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})
export default router