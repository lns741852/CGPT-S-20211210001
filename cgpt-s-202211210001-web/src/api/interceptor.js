/**
 * 生成基础axios对象，并对请求和响应做处理
 * 前后端约定接口返回解构规范
 * {
 *    code:0,
 *    data:"成功",
 *    Message:""
 * }
 */
import axios from 'axios'
import router from '../router'
import { ElMessage } from "element-plus"

// 創建一個實例
const service = axios.create({
    // 自帶URL
    baseURL: 'http://127.0.0.1:8282/HTPE',
    // baseURL: 'http://20.24.194.250:8081/HTPE',
    // 超時
    timeout: 5000,
    // 是否夾帶cookie
    withCredentials: false,
});

// 請求攔截器
service.interceptors.request.use(config => {

    if (config.options.voice) {
        config.baseURL = 'voice'
            //config.headers['Content-Type'] = 'text/html;charset=utf-8'
        return config;
    }

    if (config.options.isUpload) {
        config.headers['Content-Type'] = 'multipart/form-data'
    } else {
        config.headers['Content-Type'] = 'application/json;charset=utf-8'
    }

    if (localStorage.getItem("authorization") !== "reset") {
        config.headers['Authorization'] = localStorage.getItem("authorization");
    }
    return config;
});
// 返回結果攔截
service.interceptors.response.use((response) => {
    // 獲得資料
    const res = response;
    // 成功
    if (res.data.code === 200 && res.status === 200) {
        if (res.data.data === "") {
            ElMessage.success(res.data.msg)
        }
        return res;
    } else if (res.data.code === 403) {
        ElMessage.error(res.data.msg);
        //移除token
        localStorage.setItem("authorization", "reset");
        //跳轉
        router.push("/login")
            //等於window.location.href = '/#/login';
        return res;
    } else {
        ElMessage.error(res.data.msg);
        return res;
    }
}, () => {
    //ElMessage.error('網路異常');
});
export default service;