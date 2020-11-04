<template>
    <div>
        <cephailc></cephailc>
        <div class="mTime">
            <p>{{gettime}}</p>
        </div>
        <div class="mblog">
            <h2>{{ blog.title }}</h2>
            <div class="backIcon">
                <el-link icon="el-icon-error" href="/blogs" type="success">返回</el-link>

            </div>

            <el-link class="el-icon-edit" type="primary" v-if="ownblog">
                <router-link :to="{name:'BlogEdit',params:{blogId : blog.id}}" type="primary">
                    编辑
                </router-link>
            </el-link>
            <div>
                <el-link class="el-icon-delete" type="warning" v-if="ownblog" @click="deleteBlogs">
                    删除
                </el-link>
            </div>
            <el-divider></el-divider>
            <div class="markdown-body" v-html="blog.content">
            </div>
        </div>
    </div>
</template>
<script>
    import 'github-markdown-css/github-markdown.css'
    import cephailc from "../components/cephailc";

    export default {
        name: "BlogDetail",
        components: {cephailc},
        data() {
            return {
                gettime: '',//当前时间
                blog: {
                    id: '',
                    title: '',
                    content: ''
                },
                ownblog: false,
                handbook: "",
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
            deleteBlogs() {
                const blogId = this.$route.params.blogId
                const _this = this

                this.$alert('删除成功', '警告', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$axios.get("blog/delete/" + blogId)
                        _this.$router.push("/blogs")
                    }
                });
            }
        },

    }
</script>

<style scoped>
    .mblog {
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0);
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
        line-height: 30px;
    }

    /* markdown 高亮 */

    .hljs-name{

        color: #22863a;

    }

    .hljs-attr{

        color: #6f42c1;

    }

    .hljs-keyword{

        color: red;

    }

    .hljs-string{

        color: #032f62;

    }
</style>
