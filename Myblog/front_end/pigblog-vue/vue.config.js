module.exports = {


    // devServer Options don't belong into `configureWebpack`

    devServer: {
        open: process.platform === 'darwin',

        host: '0.0.0.0',

        port: 8081,

        public: '172.25.173.198:8081',

        hot: true,

        https: false,

        hotOnly: false,

        disableHostCheck: true,

        proxy: null,

    },

    productionSourceMap: false,


};