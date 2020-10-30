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


    #app {
        max-width: 960px;
        margin: 0 auto;
    }


</style>
