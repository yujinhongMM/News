<template>
    <div>
        <div class="commentBox">
        <h3>发表评论</h3>
        <b v-if="type">你回复&nbsp;{{name}}</b>
        <textarea name="" value="请填写评论内容" v-model="commentText"></textarea>
        <button class="btn" @click="addComment">发表</button>
        <button class="btn" @click="canelComment">取消</button>
        </div>               
    </div>
</template>
<script>
export default {
    name:'commentTextarea',
    props: ['type','name'],
    data: function(){
        return {commentText:""}
    },
    methods: {
        addComment: function() {
            this.$emit("submit",this.commentText);
            this.commentText = "";
        },
        canelComment: function() {
            this.$emit("canel");
            this.commentText = "";
        }
    }
}
</script>
<style scoped> 
.commentBox {text-align: left;margin:20px;}
.commentBox h3 {color: #634322; background: #e9e5df; padding: 8px 15px; text-align: left; font-size: 16px;}
.noComment {display:none;}
.comment {margin-bottom:15px;}
.comment + .comment, .reply + .reply{border-top:1px solid #ccc;}
.comment b{color:rgb(42, 35, 143); font-size:16px; display:block; margin:5px 0;}
.comment b span {color:#333; font-size:14px; margin-left:20px;}
.comment p {font-size:16px; color:#333;}
.commentBox textarea {overflow: auto; width: 100%; height: 95px; outline: none; resize: none;}
.commentBox button {float:right; margin-left:20px; padding:5px 30px; background:rgb(26, 9, 172); border-radius:5px; color:#fff; font-size:16px;}
.commentBox button:hover {color:#fff; background:#040678}
.reply {margin:15px 0 15px 50px;}

</style>