<template>
    <div class="NewsManage">

        <el-container>
            <el-header style="height: 46px !important;margin-top: 0px !important;"><top></top></el-header>
            <el-container class="main">
                
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right" class="box">
                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>我的新闻</el-breadcrumb-item>
                    </el-breadcrumb> 
                    <el-row>
                         <el-button type="primary" style="float:right;" @click="ReleaseNews">+发布新新闻</el-button> 
                    </el-row>
                    <div v-if="isShow">
                        <el-table
                            :data="newslist"
                            style="width: 100%">
                             <el-table-column
                                prop="newslist.img_url"
                                label="缩略图"
                                >

                                <template slot-scope="scope">
                                    <div class="imgbox">
                                        <img :src="scope.row.img_url">
                                    </div>
                                </template>

                            </el-table-column>
                            
                            
                            <el-table-column
                                prop="news_title"
                                label="标题"
                                width="300">
                            </el-table-column>
                            
                            <el-table-column
                                label="操作">
                                <template slot-scope="scope">
                                    <el-button type="text" size="small" @click="newsInfo(scope.row)">查看</el-button>
                                    <el-button type="text" size="small" @click="ModifyNews(scope.row)">修改</el-button>
                                    <el-button type="text" size="small" @click="deleteNews(scope.row)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-pagination
                            layout="prev, pager, next"
                            :total="news.total"
                            :page-size="news.size"
                            :current-page="news.currentPage"
                            @current-change="handleCurrentChange">
                        </el-pagination>
                    </div>
                    <div v-else>
                        还没有发表过新闻！
                    </div>
                </el-main>
                
            </el-container>
        </el-container>
    </div>
</template>

<script>

import Top from '@/components/Top';

export default {
   components:{
        Top
    },
    mounted() {
        this.getAjaxMynewslistNum();
    },
    data() {
        return {
          newslist: [],
          news:{
              total:0,
              size:5,
              currentPage:1
          },
          isShow:false
        }
      },
      methods:{
            handleCurrentChange:function(val){
                this.news.currentPage = val;
			    this.getAjaxMynewslist(this.news.size,this.news.currentPage);
            },
            ReleaseNews:function(){
                this.$router.push({ name: 'NewsAddmodify'});
            },
            newsInfo:function(row) {
                console.log(row.news_id)
                this.$router.push({ name: 'NewsInfo',query:{NewsID:row.news_id}});
            },
            getAjaxMynewslist:function(pageSize,pageNow){
                var user=JSON.parse(sessionStorage.getItem("user"));
                // console.log(process.env.NODE_ENV, '环境')
                let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
                let data = { 
                    'username':user.username,
                    'pageSize':pageSize,
                    'pageNow':pageNow
                }
                var successCallback =  (response) => {
                //    console.log('服务器请求成功了')
                    this.newslist=response.data.newsListusername;
                //    console.log(this.newslist)
                }
                var errorCallback =  (response) => {
                    console.log('服务器请求出错了')
                }

                //console.log(id)
                this.$http.post( urlHost +'/NewslistByUserServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
            },
            getAjaxMynewslistNum:function(){
                var user=JSON.parse(sessionStorage.getItem("user"));
                // console.log(process.env.NODE_ENV, '环境')
                let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
                let data = { 
                    'username':user.username,
                }
                var successCallback =  (response) => {
                //    console.log('服务器请求成功了')
                    this.news.total=response.data.total;
                //    console.log( this.news.total)
                    if(this.news.total!=0){
                        this.isShow=true;
                        this.getAjaxMynewslist(this.news.size,this.news.currentPage);
                    }else{
                        this.isShow=false;
                    }
                //    console.log(this.newsdata)
                }
                var errorCallback =  (response) => {
                    console.log('服务器请求出错了')
                }

                //console.log(id)
                this.$http.post( urlHost +'/NewslistTotalByUserservlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
            },
            ModifyNews:function(row){
                this.$router.push({ name: 'NewsAddmodify',query:{NewsID:row.news_id}});
            },
            deleteNews:function(row){
                this.getAjaxDeleteNews(row)
            },
            getAjaxDeleteNews:function(row){
                let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
                let data = { 
                    'news_id':row.news_id,
                }
                var successCallback =  (response) => {
                    if(response.data.flag==true){
                        alert("删除成功！");
                        this.getAjaxMynewslistNum();
                    }
                }
                var errorCallback =  (response) => {
                    console.log('服务器请求出错了')
                }
                this.$http.post( urlHost +'/NewsDeleteServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
            },

      }
}
</script>

<style scoped>
.NewsManage>.el-container{
    width: 70%;
    margin: 0 auto;
}

.box{
    margin:10px 0; 
    font-size: 20px;
}

.el-header{ 
    margin-top:40px;
    background-color: #B3C0D1;
    text-align: center;
    padding:0px;
    line-height: 36px;
}

.NewsManage .el-main {
    background-color: rgb(255, 255, 255);
    color: #333;
    text-align: center;
    min-height: 580px;
    height: auto;
    padding:20px 5%;
    box-sizing:border-box;
}
.routerlink{
    text-decoration:none;
}
.routerlink:hover{
    text-decoration:underline;
}
img {
      display:block;
    }
  .imgbox {
      width:100px;
      border:solid 2px gray;
      height:80px;
      overflow:hidden;
      float: left;
      margin-right:20px; 
    }
  .imgbox img {
      width:100px;
      height:80px;
      position:relative; 
      transition:all 1s;
    }
  .imgbox:hover img{
      transform:scale(1.5,1.5);
    }
</style>