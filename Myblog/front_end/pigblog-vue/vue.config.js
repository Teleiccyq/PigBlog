module.exports = {

    configureWebpack: {

        // other webpack options to merge in ...

    },

    // devServer Options don't belong into `configureWebpack`

    devServer: {
        open: process.platform === 'darwin',

        port: 8082,

        public: '172.25.173.198:8082',

        hot: true,

        https: false,

        hotOnly: false,

        disableHostCheck: true,

        proxy: null,
    }



};