<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>


<style>
    .backPic {
        background: url("http://cxycyq.gitee.io/my-blgo/images/pigblog.jpg") no-repeat 600px 0;
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
