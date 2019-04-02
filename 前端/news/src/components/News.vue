<template>
    <el-card class="box-card">
        <div v-if="isClosed">
            <div v-for='news in newsdata' class="text item clearfix">
                
            <div class="imgbox">
                
                
                <router-link :to="{
                    path:'NewsInfo',
                    query: {
                        NewsID: news.news_id, 
                        }
                    }">
                        <img :src="news.img_url">
                </router-link>
            </div>
            <router-link class="title routerlink" :to="{
                    path:'NewsInfo',
                    query: {
                        NewsID: news.news_id, 
                        }
                    }">{{news.news_title}}</router-link>
            </div>
            <el-pagination
                background
                layout="prev, pager, next"
                :page-size="newspage.size"
                :total="newspage.total"
                :current-page="newspage.currentPage"
                @current-change="handleCurrentChange">
            </el-pagination>
        </div>
        <div v-else>暂无新闻</div>
    </el-card>
</template>

<script>

export default {
    name: 'News',
    props: ['type'],
    mounted() {
        this.getAjaxTotal(); 
    },
    data(){
        return{
        newsdata:[
            
        ],
        newspage:{
            total:0,
            size:2,
            currentPage:1
        },
        isClosed:false
    }
  },
  methods:{
      getAjaxNews:function(newspage){
        
        //console.log(process.env.NODE_ENV, '环境')
        let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
        let data = { 
            'type': this.type,
            'pageSize':newspage.size, 
            'pageNow':newspage.currentPage
            }
        var successCallback =  (response) => {
        //    console.log('服务器请求成功了')
            this.newsdata=response.data.newsTimeList;
          //  console.log(this.newsdata)


        }
        var errorCallback =  (response) => {
            console.log('服务器请求出错了')
        }

        //console.log(id)
        this.$http.post( urlHost +'/NewsTimeListServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
    },
    //页数改变时
    handleCurrentChange(val) {
        this.newspage.currentPage = val;
        this.getAjaxNews(this.newspage);
    },
    //获取新闻的总条数
    getAjaxTotal:function(){
        
       // console.log(process.env.NODE_ENV, '环境')
        let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
        let data = { 
            'type': this.type,
            }
        var successCallback =  (response) => {
        //    console.log('服务器请求成功了')
            this.newspage.total=response.data.newstypetotal;
            if(this.newspage.total!=0){
                this.isClosed=true;
                this.getAjaxNews(this.newspage);
            }


        }
        var errorCallback =  (response) => {
            console.log('服务器请求出错了')
        }

        //console.log(id)
        this.$http.post( urlHost +'/NewsTypeTotalServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
    }

  }
}
</script>

<style scoped>
 .text {
    font-size: 18px;
    text-align: left;
    }

  .item {
    padding: 18px 0;
    height:120px;
    }

  .box-card {
    width: 100%;
    }
   img {
      display:block;
    }
  .imgbox {
      width:160px;
      border:solid 2px gray;
      height:120px;
      overflow:hidden;
      float: left;
      margin-right:20px; 
    }
  .imgbox img {
      width:160px;
      height:120px;
      position:relative; 
      transition:all 1s;
    }
  .imgbox:hover img{
      transform:scale(1.5,1.5);
    }
  .routerlink{
      text-decoration:none;
      color: black;
  }
  .routerlink:hover{
      text-decoration:underline;
  }
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }
</style>