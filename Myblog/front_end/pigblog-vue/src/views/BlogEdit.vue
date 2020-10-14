<template>
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
                                  :toolbars="toolbars"
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
                    toolbars: {
                        bold: true, // 粗体
                        italic: true, // 斜体
                        header: true, // 标题
                        underline: true, // 下划线
                        strikethrough: true, // 中划线
                        mark: true, // 标记
                        superscript: true, // 上角标
                        subscript: true, // 下角标
                        quote: true, // 引用
                        ol: true, // 有序列表
                        ul: true, // 无序列表
                        link: true, // 链接
                        imagelink: true, // 图片链接
                        code: true, // code
                        table: true, // 表格
                        fullscreen: false, // 全屏编辑
                        readmodel: false, // 沉浸式阅读
                        htmlcode: true, // 展示html源码
                        help: true, // 帮助
                        /* 1.3.5 */
                        undo: true, // 上一步
                        redo: true, // 下一步
                        trash: true, // 清空
                        save: false, // 保存（触发events中的save事件）
                        /* 1.4.2 */
                        navigation: true, // 导航目录
                        /* 2.1.8 */
                        alignleft: true, // 左对齐
                        aligncenter: true, // 居中
                        alignright: true, // 右对齐
                        /* 2.2.1 */
                        subfield: true, // 单双栏模式
                        preview: false // 预览
                    }
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
            // 绑定@imgAdd event
            $imgAdd(pos, $file){
                // 第一步.将图片上传到服务器.
                var formdata = new FormData();
                formdata.append('image', $file);
                axios({
                    url: 'server url',
                    method: 'post',
                    data: formdata,
                    headers: { 'Content-Type': 'multipart/form-data' },
                }).then((url) => {
                    // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
                    /**
                     * $vm 指为mavonEditor实例，可以通过如下两种方式获取
                     * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
                     * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
                     */
                    $vm.$img2Url(pos, url);
                })
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