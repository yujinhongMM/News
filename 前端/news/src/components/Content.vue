<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right" class="box">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{newsdata.type}}新闻详情</el-breadcrumb-item>

           
        </el-breadcrumb>  
        <div class="time">作者：{{newsdata.username}}&nbsp;&nbsp;发布时间：{{newsdata.news_time}}</div>
        <div class="title">{{newsdata.news_title}}</div>
            <div id="newContent">

            </div>       
        </div>
</template>
<script>
export default {
    name:'Content',
    props:['NewsID'],
    mounted() {
        this.getAjaxNewsInfo();
    },
     data:function(){
        return{
            newsdata:{
                
            }
        }
    } ,
    methods:{
        getAjaxNewsInfo:function(){
            //获取新闻id
            let id = this.$route.query.NewsID;
            console.log(process.env.NODE_ENV, '环境')
	        let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
            let data = { 
                'news_id':id,
                }
            var successCallback =  (response) => {
            //    console.log('服务器请求成功了')
                this.newsdata=response.data.newsInfo;
            //    console.log(this.newsdata)
                document.getElementById("newContent").innerHTML=this.newsdata.news_content;
            }
            var errorCallback =  (response) => {
                console.log('服务器请求出错了')
            }
            
            //console.log(id)
            this.$http.post( urlHost +'/NewsInfoServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
        }
    }
}
</script>
<style scoped>
.box{
    margin:10px 0; 
    font-size: 20px;
}

.content{
    text-align: left;
}
.clearfix:before,.clearfix:after {
    display: table;
    content: "";
}
.clearfix:after {
    clear: both
}
#newContent{
    width: 80%;
    height: auto;
    margin: 0px auto 20px;
    text-align: left;
}
.title{
    width: 80%;
    height: auto;
    margin: 20px auto 0px;
    font-weight:bold;
    font-size: 30px;
}
.time{
    width: 80%;
    height: auto;
    margin: 5px auto;
    text-align: left;
}
</style>