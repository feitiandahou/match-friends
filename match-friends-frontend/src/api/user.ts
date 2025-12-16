import myAxios from "../plugins/myAxios.ts";

export function getCurrentUser() {
    return myAxios.get('/current')
}
export function updateUser(user: any) {
    return myAxios.post('/update', user)
}