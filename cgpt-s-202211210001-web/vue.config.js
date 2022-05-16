module.exports = {
    assetsDir: 'public',
    publicPath: './',
    devServer: {
        proxy: {
            '/voice': {
                target: 'http://www.vaimaservice.com/wistron', //接口域名
                changeOrigin: true, //是否跨域
                ws: true,
                secure: true,
                pathRewrite: { //路径重置
                    '^/voice': ''
                }
            }
        }
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