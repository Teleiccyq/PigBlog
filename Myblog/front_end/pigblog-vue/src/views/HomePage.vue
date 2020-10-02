<template>
    <div class="backPic">
        <span><el-link class="mblog" type="danger" @click="mblogs">Blog</el-link></span>
        <div class="block">
            <el-carousel height="250px" autoplay="true">
                <el-carousel-item v-for="item in imgurl">
                    <el-row>
                        <el-col :span="24"><img ref="imgHeight" :src="item.url" /></el-col>
                    </el-row>
                </el-carousel-item>
            </el-carousel>
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
            }
        },
        components: {
            aplayer
        },
        data() {
            return {
                imgurl: [
                    {
                        id:1,url:"http://cxycyq.gitee.io/my-blgo/images/item/item1.jpg"
                    },
                    {
                        id:2,url:"http://cxycyq.gitee.io/my-blgo/images/item/item2.jpg"
                    },
                    {
                        id:3,url:"http://cxycyq.gitee.io/my-blgo/images/item/item3.jpg"
                    },
                ]
            }
        }
    }
</script>


<style scoped>
    .block {
        max-width: 400px;
        margin-top: 90px;
        margin-left: 130px;
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
        font-size: 20px;
    }
</style>