<template>
    <div class="box">
        <el-container>
            <el-header>
                <el-avatar :size="50" :src="user.avatar"></el-avatar>
            </el-header>
            <el-main>
                <el-form ref="form" :model="form" label-width="80px" class="detailform">
                    <el-form-item label="用户名">
                        <el-input v-model="form.username" :disabled="isable" @click="change"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱">
                        <el-input v-model="form.email" :disabled="isable"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="back">返回</el-button>
                        <el-button type="warning" @click="changeIsable" v-if="isable">修改</el-button>
                        <el-button type="warning" @click="changeInfo" v-if="!isable">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "UserDetail",
        data(){
            return{
                user:{
                    username:'请先登录',
                    avatar:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                },
                form:{
                    username:'',
                    email:'',
                    password:''
                },
                isable:'true'
            }
        },
        methods:{
            back(){
              this.$router.push("/blogs");
            },
            changeIsable(){
                this.isable = false;
                console.log(this.isable)
            },
            changeInfo(){
                const _this = this;
                var username = this.form.username
                var email = this.form.email
            }
        },
        created() {
            if (this.$store.getters.getUser.username){
                this.user.avatar = this.$store.getters.getUser.avatar
                this.form.username = this.$store.getters.getUser.username
                this.form.email = this.$store.getters.getUser.email
            }
        },
    }
</script>

<style scoped>
    .el-header, .el-footer {
        background-color: pink;
        color: #333;
        text-align: center;
        line-height: 100px;
        height: 300px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 300px;
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

    .box{
        margin-top: 50px;
    }

    .detailform{
        max-width: 50%;
        margin: 0 auto;
    }
</style>