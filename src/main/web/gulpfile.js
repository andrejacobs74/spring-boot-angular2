const gulp = require('gulp');
const ts = require('gulp-typescript');
const concat = require('gulp-concat');
const sass = require('gulp-sass');

var tsProject = ts.createProject('tsconfig.json', {
    typescript: require('typescript'),
    noExternalResolve: false,
    declarationFiles: true
});

gulp.task('compile', function () {
    var tsResult = gulp.src('./app/**/*.ts')
        .pipe(ts(tsProject));

    return tsResult.js.pipe(gulp.dest('../resources/static'));
});

gulp.task('app-bundle', function () {
    var tsProject = ts.createProject('tsconfig.json', {
        typescript: require('typescript'),
    });

    var jsres = gulp.src('app/**/*.js')
        .pipe(gulp.dest('../resources/static/app'));
});

gulp.task('template-bundle', function () {

    var jsres = gulp.src('app/**/*.html')
        .pipe(gulp.dest('../resources/static/app'));
});

gulp.task('app-sass', function(){
    var res = gulp.src('app/styles/sass/main.scss')
        .pipe(sass())
        .pipe(gulp.dest('app/styles/css'))

});

gulp.task('copy-css', function(){
    var jsres = gulp.src('app/**/*.css')
        .pipe(gulp.dest('../resources/static/app'));
});

gulp.task('vendor-bundle', function() {
    gulp.src([
            'node_modules/systemjs/**/*js',
            'node_modules/zone.js/**/*js',
            'node_modules/reflect-metadata/Reflect.js',
            'node_modules/rxjs/**/*js',
            'node_modules/@angular/**/*js',
            'node_modules/angular2-in-memory-web-api/**/*js',
            'node_modules/core-js/**/*js',
            'node_modules/ng2-bootstrap/**/*js',
            'node_modules/ng2-cookies/**/*js',
            'node_modules/moment/**/*js',
            'node_modules/bootstrap/**/*css'
        ],
        { base: "node_modules" })
        .pipe(gulp.dest('../resources/static/lib/'));
});

gulp.task('default', ['app-bundle', 'app-sass', 'copy-css', 'vendor-bundle', 'template-bundle']);