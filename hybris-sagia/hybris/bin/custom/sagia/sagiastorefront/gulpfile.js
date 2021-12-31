// Load plugins
var gulp = require('gulp'),
    $   = require('gulp-load-plugins')(),
    browserSync = require('browser-sync');
//    pump = require('pump');
var rename = require("gulp-rename");
var LessPluginGlob = require('less-plugin-glob');
var clean = require('postcss-clean');
var rtl = require('postcss-rtl');
var autoprefixer = require('autoprefixer');
var babel = require('gulp-babel');
var uglify = require('gulp-uglify');


var autoprefixerConfig = {
        browsers: ['last 2 versions'],
        cascade: false
    };

var rtlOptions = {};

var postcssPlugings = [
    autoprefixer(autoprefixerConfig),
    clean(),
    rtl( rtlOptions )
];


var paths = {
    js: {
        src: './web/webroot/_ui/responsive/theme-alpha/js/**/*.js',
        target: './web/webroot/_ui/responsive/theme-alpha/min-js/',
        watch: [
            './web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/js/**/*.js'
        ]
    },
    fonts: {
        src: './web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/fonts/**/*',
        target: './web/webroot/_ui/responsive/theme-alpha/fonts/',
        watch: [
            './web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/fonts/**/*'
        ]
    },

    less: {
        src: './web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/less/style.less',
        target: './web/webroot/_ui/responsive/theme-alpha/css',
        watch: [
            './web/webroot/WEB-INF/_ui-src/**/*'
        ]
    },
    icons: {
        src: './web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/icons/**/*',
        target: './web/webroot/_ui/responsive/theme-alpha/icons',
        target2: './web/webroot/WEB-INF/tags/responsive/icons',
        watch: [
            './web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/icons/**/*'
        ]
    },
    all: {
        watch: [
            './web/webroot/_ui/responsive/theme-alpha/icons/*',
            './web/webroot/_ui/responsive/theme-alpha/img/*',
            './web/webroot/_ui/responsive/theme-alpha/fonts/*',
            './web/webroot/_ui/responsive/theme-alpha/js/*',
            './web/webroot/WEB-INF/views/**/*',
            './web/webroot/WEB-INF/tags/**/*',
        ],
        sourcemaps:  '../maps/',
    }
};


var lessError = $.notify.onError({
    title: "Less <%= error.name %> on Line <%= error.lineNumber %>",
    message:  "<%= error.fileName %> "

});


// js bablify
// only files inside _ui-src/responsive/theme-alpha/js get bablified
//gulp.task('bablify', function() {
//  gulp.src('./web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/js/sagia.profile.js')
//    .pipe(babel({ presets: ['babel-preset-es2015']}))
//    .pipe(gulp.dest('./web/webroot/WEB-INF/_ui-src/responsive/themes/alpha/js/babel/'));
//});


// task to compress js from _ui folder (not bablified)
gulp.task('compress-js', function() {
  gulp.src(paths.js.src)
    .pipe($.sourcemaps.init())
    .pipe(babel({ presets: ['babel-preset-es2015']}))
    .pipe(uglify())
    .pipe($.sourcemaps.write('maps'))
    .pipe(gulp.dest(paths.js.target));
});

// LESS Styles
gulp.task('less', function() {
  gulp.src(paths.less.src)
    .pipe($.plumber())
    .pipe($.less({plugins: [LessPluginGlob]}))
    .pipe($.postcss([
        rtl( rtlOptions )
    ]))
    .pipe($.postcss([
        autoprefixer(autoprefixerConfig),
        clean()
    ]))
    .pipe(gulp.dest(paths.less.target))
    .pipe(browserSync.reload({stream:true}));
});

gulp.task('dev-less', function() {
  gulp.src(paths.less.src)
    .pipe($.plumber({errorHandler: lessError}))
    .pipe($.sourcemaps.init())
    .pipe($.less({plugins: [LessPluginGlob]}))
    .pipe($.postcss([
        rtl( rtlOptions )
    ]))
    .pipe($.postcss([
        autoprefixer(autoprefixerConfig)
    ]))
    .pipe($.sourcemaps.write())
    .pipe(gulp.dest(paths.less.target))
    .pipe(browserSync.reload({stream:true}));
});


// copy icons
gulp.task('icons', function() {
  gulp.src(paths.icons.src)
    .pipe(gulp.dest(paths.icons.target))
    .pipe(rename({
        extname: ".tag"
    }))
    .pipe(gulp.dest(paths.icons.target2));
});

// copy fonts
gulp.task('fonts', function() {
  gulp.src(paths.fonts.src)
    .pipe(gulp.dest(paths.fonts.target));
});


// Default task
gulp.task('default', function() {
    gulp.start('less');
    gulp.start('icons');
    gulp.start('fonts');
    gulp.start('compress-js');
});

// Watch
gulp.task('watch', function() {
    browserSync.init({
        proxy: {
            target: 'https://localhost:9002/sagiastorefront/?site=sagia'
        },
        notify: false,
        logLevel: "info",
        open: false,
        ghostMode: false
    });

    $.watch(paths.less.watch,{read: false},function () {gulp.start('dev-less')});
    $.watch(paths.icons.watch,{read: false},function () {gulp.start('icons')});
    $.watch(paths.fonts.watch,{read: false},function () {gulp.start('fonts')});
    $.watch(paths.all.watch,{read: false},function () {browserSync.reload();});
});
