import axios from 'axios'

const service = axios.create({
    responseType: 'arraybuffer'
})
service.interceptors.request.use(config => {
    config.headers['Authorization'] = localStorage.getItem("authorization");
    return config
}, error => {
    console.log(error)
});

service.interceptors.response.use(
    resp => {
        const headers = resp.headers;
        let reg = RegExp(/application\/json/);
        if (headers['content-type'].match(reg)) {
            resp.data = uintToString(resp.data);
        } else {
            let fileDownload = require('js-file-download');
            let fileName = headers["content-disposition"].split(";")[1].split("filename=")[1];
            let contentType = headers["content-type"];
            fileName = decodeURIComponent(fileName);
            fileDownload(resp.data, fileName, contentType)
        }
    }, error => {
        console.log(error);
    }
);
let base = 'http://127.0.0.1:8282/HTPE';
export const downloadRequest = (url, params) => {
    return service({
        method: 'get',
        url: `${base}${url}`,
        params
    })
}

function uintToString(uintArray) {
    let encodedString = String.fromCharCode.apply(null, new Uint8Array(uintArray)),
        decodedString = decodeURIComponent(escape(encodedString));
    return JSON.parse(decodedString);
}
export default service