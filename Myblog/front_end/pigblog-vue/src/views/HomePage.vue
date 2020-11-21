<template>
    <div class="backPic">
        <span><el-link class="mblog" type="danger" @click="mblogs">Blog</el-link></span>
        <div class="mTime">
<!--            <p>{{gettime}}</p>-->
                <p>{{motto}}</p>
        </div>
    </div>
</template>
<script>

    export default {
        name: "Index",
        methods: {
            mblogs() {
                this.$router.push("/blogs")
            },
            getTime() {
                var _this = this;
                let yy = new Date().getFullYear();
                let mm = new Date().getMonth() + 1;
                let dd = new Date().getDate();
                let hh = new Date().getHours();
                let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes() : new Date().getMinutes();
                let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds() : new Date().getSeconds();
                // _this.gettime = yy+'-'+mm+'-'+dd+' '+hh+':'+mf+':'+ss;
                _this.gettime = hh + ':' + mf + ':' + ss;
            },
            currentTime() {
                setInterval(this.getTime, 500)
            },
        },
        data() {
            return {
                gettime: '',//当前时间
                motto:'',//展示的一条格言语句
                mottos:[//格言数组
                    '前天看到了小兔，昨天是小鹿，今天是你。',
                    'Fear can hold you prisoner.Hope can set you free',
                    '海纳百川，有容乃大;壁立千仞，无欲则刚',
                    '那些刻在椅子背后的爱情，会不会像水泥上的花朵，开出没有风的，寂寞的森林',
                    '当我们爱别人的时候，生活是美好、快乐的',
                ],
            }
        },
        created() {
            this.currentTime();
            this.motto = this.mottos[Math.floor((Math.random() * this.mottos.length))]
        },
    }
</script>


<style scoped>
    .mTime {
        text-align: center;
        width: 500px;
        height: 60px;
        margin-left: 10px;
        margin-top: 140px;
    }

    .mTime p {
        font-size: 20px;
        color: #e838ff;
        font-style: italic;
        font-weight: bold;
        opacity: 0.6;
        font-family: 微软雅黑, 宋体;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
    }

    .el-header, .el-footer {
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .el-dropdown {
        vertical-align: top;
    }

    .el-dropdown + .el-dropdown {
        margin-left: 15px;
    }

    .el-icon-arrow-down {
        font-size: 12px;
    }

    .mheader {
        float: left;
    }

    .mblog {
        margin-left: 100px;
        margin-top: 20px;
        font-size: 30px;
    }

    .backPic {
        background: url("http://cxycyq.gitee.io/my-blgo/images/pigblog.jpg") no-repeat 550px 0;
        background-size: contain;
        background-attachment: fixed;
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        overflow: hidden;
        font: normal 100% Arial, sans-serif;
        overflow-y: scroll;
    }
</style>