<template>
    <div id="app">
            <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                screenWidth: '',
                screenHeight: ''
            };
        },
        mounted() {
            this.screenWidth = document.body.clientWidth;
            this.screenHeight = document.body.clientHeight;
            window.onresize = () => {
                return (() => {
                    this.screenWidth = document.body.clientWidth;
                    this.screenHeight = document.body.clientHeight;
                })();
            };
        },
        created() {
            document.body.removeChild(document.getElementById('Loading'))   // 加载页面完后移除加载动画
        },
        methods:{
            reload(){
                this.isRouterAlive = false
                this.$nextTick(function(){
                    this.isRouterAlive = true
                })
            }
        },
    }

</script>
<style>
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
        font: normal 100% Arial, sans-serif;
        overflow-y: scroll;
    }

    #app {
        max-width: 960px;
        margin: 0 auto;
    }


</style>
