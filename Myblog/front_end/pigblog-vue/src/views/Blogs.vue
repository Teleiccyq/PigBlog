<template>
    <div class="m-container">
        <cephailc></cephailc>
        <div class="mTime">
            <p>{{gettime}}</p>
        </div>
        <div class="block">
            <el-timeline>
                <el-timeline-item v-bind:timestamp="blog.created" placement="top" v-for="blog in blogs">
                    <el-card>
                        <h4>
                            <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}">{{blog.title}}
                            </router-link>
                        </h4>
                        <p>{{blog.description}}</p>
                    </el-card>
                </el-timeline-item>
            </el-timeline>

        </div>
        <el-pagination class="mpage"
                       background
                       layout="prev, pager, next"
                       :current-page=currentPage
                       :page-size=pageSize
                       @current-change=page
                       :total="total">
        </el-pagination>
    </div>
</template>


<script>
    import cephailc from "../components/cephailc";

    export default {
        name: "Blogs",
        components: {cephailc},
        data() {
            return {
                gettime: '',//当前时间
                blogs: {},
                currentPage: 1,
                total: 0,
                pageSize: 5
            }
        },
        methods: {
            page(currentPage) {
                const _this = this
                this.$axios.get('http://localhost:8080/blogs?currentPage=' + currentPage).then((res) => {
                    console.log(res.data.data.records)
                    _this.blogs = res.data.data.records
                    _this.currentPage = res.data.data.current
                    _this.total = res.data.data.total
                    _this.pageSize = res.data.data.size
                })
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
        mounted() {
            this.page(1);
        },
        created() {
            this.currentTime();
        },
    }
</script>

<style scoped>
    .mpage {
        text-align: center;
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

</style>