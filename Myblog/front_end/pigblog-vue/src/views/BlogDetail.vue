<template>
    <div>
        <cephailc></cephailc>
        <div class="mTime">
            <p>{{gettime}}</p>
        </div>
        <div class="mblog">
            <h2>{{ blog.title }}</h2>
            <el-link icon="el-icon-edit" v-if="ownblog">
                <router-link :to="{name:'BlogEdit',params:{blogId : blog.id}}">
                    编辑
                </router-link>
            </el-link>
            <div class="backIcon">
                <el-link icon="el-icon-error" href="/blogs">返回</el-link>
            </div>
            <el-divider></el-divider>
                <div class="markdown-body " v-html="blog.content"></div>
        </div>


    </div>
</template>
<script>
    import 'github-markdown-css/github-markdown.css'
    // import 'github-markdown-css'
    import cephailc from "../components/cephailc";

    export default {
        name: "BlogDetail",
        components: {
            cephailc
        },
        data() {
            return {
                gettime: '',//当前时间
                blog: {
                    id: '',
                    title: '',
                    content: ''
                },
                ownblog: false
            }
        },
        created() {
            this.currentTime();
            const blogId = this.$route.params.blogId
            const _this = this
            this.$axios.get("/blog/" + blogId).then(res => {
                const blog = res.data.data
                _this.blog.id = blog.id
                _this.blog.title = blog.title

                var markdownIt = require('markdown-it')
                var md = new markdownIt()

                var result = md.render(blog.content)

                _this.blog.content = result

                _this.ownblog = (blog.userId === _this.$store.getters.getUser.id)
            })
        },
        methods: {
            getTime() {
                var _this = this;
                let yy = new Date().getFullYear();
                let mm = new Date().getMonth() + 1;
                let dd = new Date().getDate();
                let hh = new Date().getHours();
                let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes() : new Date().getMinutes();
                let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds() : new Date().getSeconds();
                _this.gettime = hh + ':' + mf + ':' + ss;
            },
            currentTime() {
                setInterval(this.getTime, 500)
            },
        },

    }
</script>

<style scoped>
    .mblog {
        box-shadow: 0 2px 12px 0 rgba(0,0,0,0);
        width: 100%;
        height: 700px;
    }

    .mTime {
        float: left;
        margin-top: -150px;
        margin-left: -200px;
    }

    .mTime p {
        font-size: 50px;
        color: #e838ff;
        font-style: italic;
        font-weight: bold;
        opacity: 0.6;
        font-family: 微软雅黑, 宋体;
    }

    .backIcon {
        float: right;
        font-size: 25px;
    }

</style>
