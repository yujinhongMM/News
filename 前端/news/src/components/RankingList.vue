<template>
    <el-card class="box-card" style="text-align: left !important;">
        <div slot="header" class="clearfix title">
            <span><i class="icon iconfont iconfont0 icon-huo"></i>热点排行</span>
        </div>
        <div v-if="isShow">
            <div v-for='(news,index) in newsdata' class="text item">
                <i :class="iconicon4[index]"></i>
                <router-link :to="{
                    path:'NewsInfo',
                    query: {
                            NewsID: news.news_id, 
                        }
                    }" class="routerlink">{{news.news_title}}</router-link>
            </div>
        </div>
        <div v-else> 暂无排行 </div>
    </el-card>
</template>

<script>

export default {
    name: 'RankingList',
    mounted() {
        this.getAjaxNews();
        
    },
    data(){
        return{
            count:6,
            newsdata:[
            
            ],
            iconicon4:[
                "icon iconfont icon-icon41",
                "icon iconfont icon-icon42",
                "icon iconfont icon-icon43",
                "icon iconfont icon-icon44",
                "icon iconfont icon-icon45",
                "icon iconfont icon-icon46"
            ],
            isShow:false
        }
    },
    methods:{
        getAjaxNews:function(){
            
           // console.log(process.env.NODE_ENV, '环境')
            let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
            let data = { 
                'count': this.count,
                }
            var successCallback =  (response) => {
             //   console.log('服务器请求成功了')
                this.newsdata=response.data.newsRankingList;
                if(this.newsdata==null){
                    this.isShow=false;
                }else{
                    this.isShow=true;
                }
            //    console.log(this.newsdata)


            }
            var errorCallback =  (response) => {
                console.log('服务器请求出错了')
            }

            //console.log(id)
            this.$http.post( urlHost +'/NewsRankingListServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
        }
    }
}
</script>

<style scoped>
 .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .title{
      font-size: 25px;
  }
  .iconfont{
      font-size: 20px;
      color: rgb(226, 77, 77);
      font-weight:700;
  }
  .iconfont0{
      font-size: 34px;
      color: rgb(226, 77, 77);
  }
  .routerlink{
      text-decoration:none;
      color: black;
  }
  .routerlink:hover{
      text-decoration:underline;
  }
</style>