module.exports = {
    assetsDir: 'public',
    publicPath: './',
    devServer: {
        // proxy: {
        //     '/api': {
        //         target: 'http://127.0.0.1:8181', //接口域名
        //         changeOrigin: true, //是否跨域
        //         ws: true, 
        //         secure: true, 
        //         pathRewrite: { //路径重置
        //             '^/api': ''
        //         }
        //     }
        // }
    },
    //引入less
    css: {
        loaderOptions: {
            less: {
                javascriptEnabled: true
            }
        }
    },
};