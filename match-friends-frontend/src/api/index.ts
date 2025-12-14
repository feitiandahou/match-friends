import axios from "axios"

const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
})
//全局请求拦截器
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token')
    if(token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})
//全局响应拦截器
api.interceptors.response.use((response) => response,
(error) => {
    if(error.response?.status === 401) {
        //token 失效，跳转登录
        localStorage.removeItem('token')
        window.location.href = '/login'
    }
    return Promise.reject(error)
})

export default api