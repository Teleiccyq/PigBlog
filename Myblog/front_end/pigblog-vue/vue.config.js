module.exports = {

    publicPath: '/',
    // publicPath: process.env.NODE_ENV === 'production'
    //     ? '/vue-admin-web/'
    //     : '/',

    devServer: {
        open: process.platform === 'darwin',
        host: '0.0.0.0',
        port: 8281,
        public: '172.25.173.198:8281',
        hot: true,
        https: false,
        hotOnly: false,
        disableHostCheck: true,
        proxy: null,
    },

    configureWebpack:{

    },

    productionSourceMap: false,


};