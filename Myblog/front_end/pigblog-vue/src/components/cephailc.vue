<!--博客页面的头部信息，Header与html组件重名-->
<template>
    <div class="m-content">
        <h3>Pig的Blog</h3>
        <div class="block">
            <el-avatar :size="50" :src="user.avatar"></el-avatar>
            <div>{{user.username}}</div>
        </div>
        <div class="maction">
            <span><el-link type="info" href="/">主页</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link type="success" icon="el-icon-edit" href="/blog/add">编写博客</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span v-show="!hasLogin"><el-link type="primary" href="/login">登录</el-link></span>
            <span v-show="hasLogin"><el-link type="warning" @click="logout">退出</el-link></span>
        </div>

    </div>
</template>

<script>
    export default {
        name: "cephailc",
        data(){
            return{
                hasLogin : false,
                user:{
                    username:'请先登录',
                    avatar:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"

                },
                blogs:{},
                currentPages: 1,
                total:0
            }
        },
        methods:{
            logout(){
                const _this = this
                _this.$axios.get("http://localhost:8080/logout",{
                    headers:{
                        "Authorization" : localStorage.getItem("token")
                    }
                }).then(res =>{
                    _this.$store.commit("REMOVE_INFO")
                    _this.$router.push("/login")
                });
            }
        },
        created(){
            if (this.$store.getters.getUser.username){
                this.user.username = this.$store.getters.getUser.username
                this.user.avatar = this.$store.getters.getUser.avatar
                this.hasLogin = true
            }
        }
    }

</script>

<style scoped>
    .m-content{
        max-width: 960px;
        margin: 0 auto;
        text-align: center;
    }
    .maction{
        margin: 10px 0;
    }

</style>