<template>
    <div>
        <div v-if="isLogin">
            <el-row v-if="user.power==2">
                <el-col :span="8">
                    <div class="grid-content bg-purple">
                        微微日报
                    </div>
                </el-col>
                <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
                <el-col :span="1"><div class="grid-content bg-purple"></div></el-col>
                <el-col :span="3">
                    <div class="grid-content bg-purple-light">
                        <router-link class="routerlink" to="/Login">
                            <el-badge :value="1" class="item" style="line-height: 10px;height:10px;">
                               <i class="icon iconfont icon-xiaoxi"></i>
                            </el-badge>
                        </router-link>
                    </div>
                </el-col>
                <el-col :span="5"><div class="grid-content bg-purple">欢迎你，{{user.username}}</div></el-col>
                <el-col :span="2"><div class="grid-content bg-purple-light logout" @click="logout()">注销</div></el-col>
                <el-col :span="1"><div class="grid-content bg-purple"></div></el-col>
            </el-row>
             <el-row v-else>
                <el-col :span="8">
                    <div class="grid-content bg-purple">
                        微微日报
                    </div>
                </el-col>
                <el-col :span="2"><div class="grid-content bg-purple-light"></div></el-col>
                <el-col :span="1"><div class="grid-content bg-purple"></div></el-col>
                <el-col :span="3">
                    <div class="grid-content bg-purple-light">
                        <router-link class="routerlink" to="/Login">
                            <el-badge :value="1" class="item" style="line-height: 10px;height:10px;">
                               <i class="icon iconfont icon-xiaoxi"></i>
                            </el-badge>
                        </router-link>
                    </div>
                </el-col>
                <el-col :span="4"><div class="grid-content bg-purple">欢迎你，{{user.username}}</div></el-col>
                <el-col :span="3">
                    <div class="grid-content bg-purple-light logout">
                        <router-link class="routerlink" to="/NewsManage">
                            我的新闻
                        </router-link>
                    </div>
                </el-col>
                <el-col :span="2"><div class="grid-content bg-purple logout" @click="logout()">注销</div></el-col>
                <el-col :span="1"><div class="grid-content bg-purple-light logout"></div></el-col>
            </el-row>  
        </div>
        <div v-else>
             

            <el-row>
                <el-col :span="8">
                    <div class="grid-content bg-purple">
                        微微日报
                    </div>
                </el-col>
                <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
                <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
                <el-col :span="2"><div class="grid-content bg-purple-light">
                <router-link class="routerlink" to="/Login">登录</router-link></div></el-col>
                <el-col :span="2"><div class="grid-content bg-purple">注册</div></el-col>
                <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
           
            </el-row>  
            

        </div>
        
    </div>

</template>


<script>

export default {
    name: 'Top',
    mounted() {
		this.getUser()
    },
    data:function(){
        return{
            isLogin:false,
            user:{

            }
        }
    },
    methods:{
        getUser:function () {
            var user=JSON.parse(sessionStorage.getItem("user"));
            //console.log(this.user)
            this.user=user;
            //console.log(user.username)
            if(user.username!=undefined&&user.username!=null){
                this.isLogin=true;
            }else{
                this.isLogin=false;
            }
        },
        logout:function(){
            // 从sessionStorage删除所有保存的数据
            
            this.isLogin=false;
            this.user=null;
            sessionStorage.clear();

            //console.log(sessionStorage.getItem("user"))
        }
    }
}
</script>

<style scoped>
.el-row {
    margin-bottom: 20px;
    color: white;
}
.bg-purple-dark {
    background: #4682d4;
}
.bg-purple {
    background: #1d62b1;
}
.bg-purple-light {
    background: #1d62b1;
}
.grid-content {
    min-height: 46px;
    text-align: center;
    line-height: 46px;
}
.row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
}
.routerlink{
    color: white;
    text-decoration: none;
}
.icon-xiaoxi{
    font-size: 25px;
}
.logout{
    cursor: pointer;
}

</style>
