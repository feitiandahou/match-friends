export interface User {
    id: number
    username:string
    password:string
    email?:string
    avatarUrl?:string
    tags?:string[]
}