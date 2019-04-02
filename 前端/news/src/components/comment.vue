<template>
                    
    <div id="comment">
        <comment-content 
        v-bind:commentReply="commentReply" 
        @getAjaxCR="getAjaxCR"
        v-bind:commentpage="commentpage">
        </comment-content>
        <!-- <comment-textarea v-bind:type="type" v-bind:name="oldComment" v-on:submit="addComment" v-on:canel="canelCommit"></comment-textarea> -->
    </div>

</template>

<script>


import commentContent from '@/components/comment-content';
import commentTextarea from '@/components/comment-textarea';
export default {
    name:'comment',
    props:['NewsID'],
    components:{
        commentContent,commentTextarea
    },
    mounted() {
        this.getAjaxTotal();
        this.getAjaxCR(this.commentpage);
    },
    data:function(){
        return{
             commenter: "session",   //评论人，这里会从session拿
             type: 0,                //0为评论作者1为评论别人的评论
             oldComment: null,       //久评论者的名字
             chosedIndex: -1,        //被选中的评论的index
             article: {
             title: "当归泡水喝的九大功效",
             time: "2016-07-12",
             read:50,
             content: ""
             },
            commentReply: [
               
            ],
            commentpage:{
				total:0,
				size:2,
				currentPage:1
            },
            replypage:{
                total:0,
				size:5,
				currentPage:1
            }
        }
    },
    methods:{
        getAjaxCR:function (commentpage) {
            //获取新闻id
            let id = this.$route.query.NewsID;
            console.log(process.env.NODE_ENV, '环境')
	        let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
            let data = { 
                'NewsID':id,
                'commentsize':commentpage.size, 
                'commentcurrentPage':commentpage.currentPage,
                'replypagesize':this.replypage.size,
                'replypagecurrentPage':this.replypage.currentPage
                }
            var successCallback =  (response) => {
             //   console.log('服务器请求成功了')
                this.commentReply=response.data.commentReply;
            //    console.log(this.commentReply)
            }
            var errorCallback =  (response) => {
                console.log('服务器请求出错了')
            }
            
            //console.log(id)
            this.$http.post( urlHost +'/CommentReplyServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
        },
        getAjaxTotal:function(){
            let id = this.$route.query.NewsID;
            console.log(process.env.NODE_ENV, '环境')
            let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
            let data = { 
                'NewsID':id
            }
            var successCallback =  (response) => {
             //   console.log('服务器请求成功了')
                this.commentpage.total=response.data.total;
            }
            var errorCallback =  (response) => {
                console.log('服务器请求出错了')
            }
            this.$http.post( urlHost +'/CommentTotalServlet',data,{emulateJSON: true}).then(successCallback,errorCallback);
        }
        
        
    }

    
}
</script>

<style>

</style>

