const {defineConfig} = require('@vue/cli-service')
const path = require('path')

const name = process.env.VUE_APP_TITLE || '打卡管理系统' // 网页标题

const port = process.env.port || process.env.npm_config_port || 9999 // 端口

function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = defineConfig({
    publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
    outputDir: 'dist',
    assetsDir: 'static',
    // 是否开启eslint保存检测，有效值：ture | false | 'error'
    lintOnSave: process.env.NODE_ENV === 'development',
    productionSourceMap: false,
    // webpack-dev-server 相关配置
    devServer: {
        host: '0.0.0.0',
        port: port,
        proxy: {
            [process.env.VUE_APP_BASE_API]: {
                target: `http://localhost:9999`,
                changeOrigin: true,
                pathRewrite: {
                    ['^' + process.env.VUE_APP_BASE_API]: ''
                }
            }
        },
    },
    css: {
        loaderOptions: {
            sass: {
                sassOptions: {outputStyle: "expanded"}
            }
        }
    },
    configureWebpack: {
        name: name,
        resolve: {
            alias: {
                '@': resolve('src')
            },
            fallback: {
                path: require.resolve('path-browserify')
            }
        }
    },
    chainWebpack(config) {
        config.plugins.delete('preload')
        config.plugins.delete('prefetch')

        config.module
            .rule('svg')
            .exclude.add(resolve('src/assets/icons'))
            .end()
        config.module
            .rule('icons')
            .test(/\.svg$/)
            .include.add(resolve('src/assets/icons'))
            .end()
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]'
            })
            .end()
    }
})
