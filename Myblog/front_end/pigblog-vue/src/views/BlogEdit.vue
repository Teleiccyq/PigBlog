<template class="homeBox">
    <div>
        <cephailc></cephailc>
        <div class="mTime">
            <p>{{gettime}}</p>
        </div>
        <div class="m-content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="摘要" prop="description">
                    <el-input type="textarea" v-model="ruleForm.description"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <mavon-editor v-model="ruleForm.content"
                                  :ishljs = "true"
                                  :placeholder="'Edit ···'"
                                  ref="md"
                                  @imgAdd="$imgAdd"
                                  @imgDel="$imgDel"
                                  @change="change"
                                  :toolbarsBackground="'#f9f9f9'"
                                  style="height: calc(100vh - 50px)"
                    ></mavon-editor>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import cephailc from "../components/cephailc";
    import 'mavon-editor/dist/css/index.css'
    import 'mavon-editor/dist/markdown/github-markdown.min.css'

    export default {
        name: "BlogEdit",
        components: {cephailc},
        data() {
            return {
                gettime: '',//当前时间
                ruleForm: {
                    id: '',
                    title: '',
                    description: '',
                    content: '',
                },

                rules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur'}
                    ],
                    description: [
                        {required: true, message: '请输入摘要', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '请输入内容', trigger: 'blur'}
                    ],
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this
                        this.$axios.post("/blog/edit", this.ruleForm, {
                            headers: {
                                "Authorization": localStorage.getItem("token")
                            }
                        }).then(res => {
                            console.log(res)
                            this.$alert('操作成功', '提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push("/blogs")
                                }
                            });
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
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
            //照片上传
            $imgAdd (pos, $file) {
                // 第一步.将图片上传到服务器.
                var formdata = new FormData()
                formdata.append('image', $file)
                this.img_file[pos] = $file
                this.$http({
                    url: '/api/edit/uploadimg',
                    method: 'post',
                    data: formdata,
                    headers: { 'Content-Type': 'multipart/form-data' }
                }).then((res) => {
                    let _res = res.data
                    // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
                    this.$refs.md.$img2Url(pos, _res.url)
                })
            },
            $imgDel (pos) {
                delete this.img_file[pos]
            },
            change (value, render) {
                this.html = render
            },
            // 提交
            submit () {
                console.log(this.content)
                console.log(this.html)
            }
        },
        created() {
            this.currentTime();
            const blogId = this.$route.params.blogId
            const _this = this
            if (blogId) {
                this.$axios.get("/blog/" + blogId).then(res => {
                    const blog = res.data.data
                    _this.ruleForm.id = blog.id
                    _this.ruleForm.title = blog.title
                    _this.ruleForm.description = blog.description
                    _this.ruleForm.content = blog.content
                })
            }
        }
    }
</script>

<style scoped>
    .m-content {
        text-align: center;
    }

    .mTime {
        float: left;
        margin-top: -180px;
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