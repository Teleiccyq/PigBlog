<template>
    <div>
        <cephailc></cephailc>

        <div class="mblog">
            <h2>{{ blog.title }}</h2>
            <el-link icon="el-icon-edit" v-if="ownblog">
                <router-link :to="{name:'BlogEdit',params:{blogId : blog.id}}">
                    编辑
                </router-link>
            </el-link>
            <el-divider></el-divider>
            <div v-html="blog.content"></div>
        </div>


    </div>
</template>
<script>
    import 'github-markdown-css/github-markdown.css' // 然后添加样式markdown-body
    import cephailc from "../components/cephailc";

    export default {
        name: "BlogDetail",
        components: {
            cephailc
        },
        data() {
            return {
                blog: {
                    id: '',
                    title: '',
                    content: ''
                },
                ownblog:false
            }
        },
        created() {
            const blogId = this.$route.params.blogId
            const _this = this
            this.$axios.get("/blog/" + blogId).then(res => {
                const blog = res.data.data
                _this.blog.id = blog.id
                _this.blog.title = blog.title

                var markdownIt = require("markdown-it")
                var md = new markdownIt()

                var result = md.render(blog.content)

                _this.blog.content = result

                _this.ownblog = (blog.userId === _this.$store.getters.getUser.id)
            })
        },
        methods: {},

    }
</script>

<style scoped>
    .mblog {
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        width: 100%;
        height: 700px;
    }

</style>
