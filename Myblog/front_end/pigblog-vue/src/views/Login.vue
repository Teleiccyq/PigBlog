<template>
    <div class="mlogin">
        <el-container>
            <el-header>
                <img class="myLogo" src="http://cxycyq.gitee.io/my-blgo/images/1.png">
            </el-header>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <Vcode
                                :show="isShow"
                                @success="success"
                                @close="close"
                        />
                        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-link href="/regist" type="danger">
                            没有账号？点击注册吧~
                        </el-link>
                    </el-form-item>
                    <el-form-item>
                        <el-link href="/blogs" type="warning">
                            暂不登录
                        </el-link>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>


<script>
    import Vcode from "vue-puzzle-vcode";

    export default {
        name: "Login",
        data() {
            return {
                isShow: false, // 验证码模态框是否出现
                ruleForm: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'change'}
                    ]
                }
            };
        },
        components: {
            Vcode
        },
        methods: {
            submitForm(formName) {
                this.isShow = true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this
                        //将表单数据提交给后端，通过后端地址校验是否正确
                        //axios发起请求后，this表示为请求的this 需要在请求前预存原始的this
                        this.$axios.post("/login", this.ruleForm).then((res) => {
                            const jwt = res.headers['authorization']
                            const userInfo = res.data.data

                            //把数据共享拿出去
                            _this.$store.commit("SET_TOKEN", jwt)
                            _this.$store.commit("SET_USERINFO", userInfo)

                            //获取
                            console.log("获取"+_this.$store.getters.getUser)
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
            // 用户通过了验证
            success() {
                this.isShow = false; // 通过验证后，需要手动隐藏模态框
                //跳转
                this.$router.push("/blogs")
            },
            // 用户点击遮罩层，应该关闭模态框
            close() {
                this.isShow = false;
            }
        }
    }

</script>

<style scoped>
    .el-header, .el-footer {
        background-color: pink;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 200px;
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

    .myLogo {
        height: 120%;
    }

    .demo-ruleForm {
        max-width: 40%;
        margin: 0 auto;
    }

    .mlogin {
        margin-top: 100px;
        text-align: center;
    }
</style>