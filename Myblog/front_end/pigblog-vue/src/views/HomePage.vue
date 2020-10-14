<template>
    <div class="backPic">
        <span><el-link class="mblog" type="danger" @click="mblogs">Blog</el-link></span>
        <div class="mTime">
            <p>{{gettime}}</p>
        </div>
    </div>
</template>
<script>
    import aplayer from 'vue-aplayer'

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
            }
        },
        created() {
            this.currentTime();
        },
    }
</script>


<style scoped>
    .mTime {
        text-align: center;
        width: 220px;
        height: 60px;
        border-radius: 50px;
        /*background-color: #fbc4c4;*/
        margin-left: 120px;
        margin-top: 140px;
    }

    .mTime p {
        font-size: 50px;
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
</style>