var path = require('path');

module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        plugins: [
                            "@babel/plugin-proposal-class-properties"
                        ],
                        presets: [["@babel/preset-env", {
                            "targets": {
                                "esmodules": true
                            }
                        }],
                            "@babel/preset-react"
                        ]
                    }
                }]
            }
        ]
    }
};