import axios from "axios"

const myAxios = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
    withCredentials: true
})
//全局请求拦截器
myAxios.interceptors.request.use((config) => {
    console.log('Request:');
    return config
})
//全局响应拦截器
myAxios.interceptors.response.use((res) => {
    console.log('Response:');
    return res
}
)

export default myAxios
