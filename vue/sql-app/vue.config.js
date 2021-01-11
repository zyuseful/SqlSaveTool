module.exports = {
    configureWebpack: {
        devtool: 'source-map'
    },
    devServer: {
        host: '0.0.0.0',
        port: '8080',
        disableHostCheck: true,
        https: false,
        hotOnly: false,
        proxy: {
          '/api/': {
                target: 'http://localhost:8090',
                changeOrigin: true,
                secure: false,
                changeOrigin: true,
                pathRewrite: {
                    '^/api':''
                }
            }
        }
    }
}