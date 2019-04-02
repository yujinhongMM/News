<template>

<div>        
	<ul id="pn">
		<h3>评论</h3>
		<p v-if="commentReply.length==0">暂无评论，我来发表第一篇评论！</p>
			<!--循环获取评论内容-->
		<li class="list0"  v-else v-for="(item,index) in commentReply" v-bind:index="index">
			<!--评论者头像 这里通过评论者的用户名获取的头像-->
			<div class="head">
				<img :src="item.comment_head_portrait_url" :alt="item.comment.responder">
			</div>
				<!--评论者内容板块-->
			<div class="content">
				<p class="text">
						<!--评论者姓名-->
					<span class="name">{{item.comment.responder}}</span>
						<!--评论者内容-->
					{{item.comment.content}}
				</p>
					<!--评论者时间点赞板块-->
				<div class="good">
					<!--评论者时间-->
					<span class="date">{{item.comment.comment_time}}</span>
					<!--为评论者点赞地方-->
					<a class="dzan" href="javascript:;"><i class="icon iconfont icon-ai-like"></i></a>    
				</div>
					<!--评论者获得的点赞数-->
				<div class="people">{{item.comment.likeNumber}}人觉得很赞</div>
					<!--循环获取回复者-->
				<div class="comment-list">
					<div class="comment" user="self" v-for="(reply,index) in item.replys">
						<!--回复者头像 这里通过回复者的用户名获取的头像-->
						<div class="comment-left"><img :src="item.replys_head_portrait_url[index]" :alt="reply.responder"></div>
						<div class="comment-right">
							<div class="comment-text">
								<!--回复者用户名-->
								<span class="user">{{reply.responder}}</span>
								<!--判断回复者是不是直接回复的评论者-->
								<span v-if="reply.reviewers==item.comment.responder">：</span>
								
								<span v-else>&nbsp;&nbsp;<span style="color:black;">回复</span>&nbsp;&nbsp;{{reply.reviewers}}：</span>
									{{reply.content}}
							</div>
							<div class="comment-date">
									<!--回复者时间-->
									{{reply.reply_time}}
									<!--回复者赞-->
								<a class="comment-zan" href="javascript:;">
									{{reply.likeNumber}}
									<i class="icon iconfont icon-ai-like"></i>
								</a> 
									<!--判断该回复者是不是自己 是的话就可以删除-->
								<!-- <a class="comment-dele" href="javascript:;" v-if="reply.responder==null">删除</a>  -->
								<a class="comment-dele" href="javascript:;"  @click="operateReply">回复</a> 
							</div>

						</div>
					</div>
				</div>
				<!--评论框-->
				<div class="hf">
					<textarea type="text" class="hf-text" autocomplete="off" maxlength="100">评论…</textarea>
					<button class="hf-btn" @click="reply">回复</button>
					<span class="hf-nub">0/100</span> 
				</div>
			</div>
		</li>
	</ul>
	<div class="block">
		<el-pagination
			layout="prev, pager, next"
			:total="commentpage.total"
			:page-size="commentpage.size"
			:current-page="commentpage.currentPage"
			@current-change="handleCurrentChange">
		</el-pagination>
	</div>
</div>
        
</template>


<script>
//被评论人
var R_reviewer;
//type=1是评论，type=2是回复
var type=1;

export default {
    name:'commentContent',
	props: ['commentReply','commentpage'],
	updated() {
		this.getCommentReply();
	},
	data:function(){
		return{
			user:{

			}
		}
	},
     methods:{
        //回复评论
        reply:function(event){
			var obox=event.target.parentNode.parentNode.parentNode;
            //获取评论框
            var textarea = obox.getElementsByTagName("textarea")[0];
            //获取包含所有评论的容器
			var comment = obox.getElementsByClassName("comment-list")[0];
				console.log(comment)
            //创建新的评论div
            var div = document.createElement("div");
            //赋类名
            div.className = "comment";
            //设置属性
            div.setAttribute("user", "self");
			//获取每条评论的innerHTML结构
			if(type==1){
				var html = '<div class="comment-left">' + '<img src="" alt=""/>' + '</div>' +
                '<div class="comment-right">' +
                '<div class="comment-text"><span>我：</span>' + textarea.value + '</div>' +
                '<div class="comment-date">' +
                this.getTime() +
                '<a class="comment-zan" href="javascript:;" total="0" my="0">赞</a>' +
                '<a class="comment-dele" @click="operateReply" href="javascript:;">删除</a>' +
                '</div>' +
                '</div>';
			}else{
				var html = '<div class="comment-left">' + '<img src="" alt=""/>' + '</div>' +
                '<div class="comment-right">' +
                '<div class="comment-text"><span>我</span>'+' 回复 '+'<span>'+R_reviewer+'：</span>'+ textarea.value + '</div>' +
                '<div class="comment-date">' +
                this.getTime() +
                '<a class="comment-zan" href="javascript:;" total="0" my="0">赞</a>' +
                '<a class="comment-dele" @click="operateReply" href="javascript:;">删除</a>' +
                '</div>' +
				'</div>';
				type=1;
			}
            
            //插入到新建的评论div
            div.innerHTML = html;
			//把新评论插入到评论列表
			console.log(comment)
            comment.appendChild(div);
            //评论后初始化textarea输入框
			textarea.value = "评论…";
			textarea.placeholder="";
			textarea.parentNode.className = "hf";
			textarea.parentNode.children[2].innerHTML = "0/100";
		},
		//操作回复
     	operateReply:function (event) {
			//每条评论
			var comment = event.target.parentNode.parentNode.parentNode;
			//整个状态
			var box = comment.parentNode.parentNode.parentNode;
			//评论框
			var textarea = box.getElementsByTagName("textarea")[0];
			//名字
			var user = comment.getElementsByClassName("user")[0];
			//点击的innerHTML
			var txt = event.target.innerHTML;
			//判断当前点击的是否为回复
			if (txt == "回复") {
				//评论框触发焦点事件 也就是变高
				textarea.onfocus();
				//内容变为回复+当前人的名字
				textarea.placeholder = "回复@" +user.innerHTML+":";
				//被评论人
				R_reviewer=user.innerHTML;
				//回复
				type=2;
				//调用键盘事件
				textarea.onkeyup();
			} else {
				//否则就是删除节点
				remove(comment);
			}
		},
		getCommentReply:function(){
			var oli = document.getElementById("pn").getElementsByTagName("li"),i;
			for(i=0;i<oli.length;i++){
				var tt=oli[i].getElementsByClassName("hf-text")[0];
				//焦点事件
				tt.onfocus = function() {
					this.parentNode.className = 'hf hf-on';
					this.value = this.value == '评论…' ? '' : this.value;
				}
				//失焦事件
				tt.onblur = function() {
					if (this.value == '') {
						this.parentNode.className = 'hf';
						this.value = '评论…';
					}
				}
				//键盘事件
				tt.onkeyup = function() {
					var len = this.value.length;
					var textParentNode = this.parentNode;
					var textBtn = textParentNode.children[1];
					var textNub = textParentNode.children[2];
					if (len == 0 /*|| len>100*/ ) {
						textBtn.className = "hf-btn";
					} else {
						textBtn.className = "hf-btn hf-btn-on";
						/*this.style.color="#333"; */
					}
					textNub.innerHTML = len + "/100";
				}
			}
		},
		//获取当前时间回复评论时调用
		getTime:function () {
			var t = new Date();
			var y = t.getFullYear();
			var m = t.getMonth() + 1;
			var d = t.getDate();
			var h = t.getHours();
			var mi = t.getMinutes();
			m = m < 10 ? "0" + m : m;
			d = d < 10 ? "0" + d : d;
			h = h < 10 ? "0" + h : h;
			mi = mi < 10 ? "0" + mi : mi;
			return y + "-" + m + "-" + d + "&nbsp;" + h + ":" + mi;
		},
		//页数改变时
		handleCurrentChange(val) {
			this.commentpage.currentPage = val;
			this.getAjaxCR();
		},
		getAjaxCR:function(){
			this.$emit('getAjaxCR',this.commentpage)
		}
		//判断用户是否登录
        
		
	}
}
    
</script>
<style>
#pn h1,#pn h2,
#pn h3,#pn h4,
#pn h5,#pn h6,
#pn hr,#pn p,
#pn blockquote,#pn dl,
#pn dt,#pn dd,
#pn ul,#pn ol,#pn li,#pn pre,#pn form,#pn fieldset,
#pn legend,#pn button,#pn input,#pn textarea,#pn th,#pn td {
	margin:0;
	padding:0;
	border:none;
	outline:none;
	text-align: left;
}
#pn h1,#pn h2,#pn h3,#pn h4,#pn h5,#pn h6 {
	font-size:100%;
}
#pn address,#pn cite,#pn dfn,#pn em,#pn var {
	font-style:normal;
}
#pn code,#pn kbd,#pn pre,#pn samp {
	font-family:courier new,courier,monospace;
}
ul,ol {
	list-style:none;
}
#pn a {
	text-decoration:none;
}
#pn a:hover,#pn a:visited,#pn a:link,#pn a:active {
	text-decoration:none;
}
#pn sup {
	vertical-align:text-top;
}
#pn sub {
	vertical-align:text-bottom;
}
#pn legend {
	color:#000;
}
#pn fieldset,#pn img {
	border:0;
}
#pn button,#pn input,#pn select,#pn textarea {
	text-align: left;
	font-size:100%;
}
#pn table {
	border-collapse:collapse;
	border-spacing:0;
}
.clear {
	clear:both;
	float:none;
	height:0;
	overflow:hidden;
}
#pn {
	width:550px;
	height:auto;
	background:#fff;
	margin:0 auto;
	padding:20px;
}
#pn .list0 {
	padding:20px 0;
	position:relative;
	border-top:1px solid #eee;
}
#pn .head {
	width:60px;
	float:left;
}
#pn .head img {
	width:60px;
	height:60px;
}
#pn .close {
	width:20px;
	height:20px;
	position:absolute;
	top:0;
	right:0;
	color:#696e78;
	font-size:14px;
	text-align:center;
	line-height:20px;
}
#pn .close:hover {
	color:#eb7350;
}
#pn .content {
	width:450px;
	line-height:20px;
	font-size:14px;
	margin-left:70px;
}
#pn .name {
	color:#eb7350;
}
#pn .pic {
	margin:5px 0;
}
#pn .good:after {
	clear:both;
	content:'';
	display:block;
	width:0;
	height:0;
	visibility:hidden;
}
#pn .good {
	*zoom:1;
}
#pn .date {
	float:left;
	color:#808080;
}
#pn .good a {
	float:right;
	color:#808080;
}
#pn .people {
	background:#F7F7F7;
	height:28px;
	line-height:28px;
	padding-left:10px;
	margin:5px 0;
}
#pn .comment:after {
	clear:both;
	content:'';
	display:block;
	width:0;
	height:0;
	visibility:hidden;
}
#pn .comment {
	*zoom:1;
	padding:10px 0;
	border-top:1px solid #eee;
}
#pn .comment-left {
	display:inline;
	margin-right:10px;
}
#pn .comment-left img {
	float:left;
	width:30px;
	height:30px;
}
#pn .comment-right {
	float:left;
	width:410px;
}
#pn .comment-text {
	line-height:18px;
}
#pn .comment-text span {
	color:#eb7350;
}
#pn .comment-date {
	font-size:12px;
	line-height:14px;
	color:#ccc;
	position:relative;
}
#pn .comment-zan {
	position:absolute;
	right:40px;
	bottom:0;
	color:#808080;
}
#pn .comment-dele {
	position:absolute;
	right:0;
	bottom:0;
	color:#808080;
}
#pn .hf:after {
	clear:both;
	content:'';
	display:block;
	width:0;
	height:0;
	visibility:hidden;
}
#pn .hf {
	*zoom:1;
	text-align: left;
}
#pn .hf-text {
	text-align: left;
	border:1px solid #eee;
	display:block;
	height:15px;
	width:438px;
	padding:5px;
	resize:none;
	color:#ccc;
	font-size:12px;
}
#pn .hf-on .hf-text {
	height:60px;
	color:#333;
	border:1px solid #ff8140;
}
#pn .hf-btn {
	float:right;
	text-align: center;
	width:65px;
	height:26px;
	line-height: 26px;
	background:#f7f7f7;
	color:#ccc;
	font-size:12px;
	display:none;
}
#pn .hf-btn-on {
	background:#ff8140;
	color:#fff;
}
#pn .hf-nub {
	float:right;
	padding:3px 5px;
	color:#666;
	display:none;
}
#pn .hf-on .hf-btn {
	display:inline;
}
#pn .hf-on .hf-nub {
	display:inline;
}
</style>