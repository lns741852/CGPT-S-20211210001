/**
 * 全局配置
 */
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from "element-plus";
import "element-plus/theme-chalk/index.css";
import "./assets/css/global.css"
import axios from './api/axios'
import locale from 'element-plus/lib/locale/lang/zh-tw';
import * as ElIconModules from '@element-plus/icons-vue'
import { downloadRequest } from './api/download';
import X2JS from 'x2js';
import Print from 'vue3-print-nb'


const app = createApp(App)
app.config.productionTip = false
app.config.globalProperties.$axios = axios //配置成全局組件
app.config.globalProperties.$msgbox = ElementPlus.ElMessageBox
app.config.globalProperties.$downloadRequest = downloadRequest;
app.config.globalProperties.$x2js = new X2JS();

for (let iconName in ElIconModules) {
    app.component(iconName, ElIconModules[iconName])
}

app.use(store).use(router).use(Print).use(ElementPlus, { locale }).mount('#app')