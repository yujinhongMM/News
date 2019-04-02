<!-- 组件代码如下 -->
<template>
  <div id="ReleaseNews">

   <el-container>
      <el-header style="height: 46px !important;margin-top: 0px !important;"><top></top></el-header>
        <el-container class="main">
          <el-main>
            <el-breadcrumb separator-class="el-icon-arrow-right" class="box">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item :to="{ path: '/NewsManage' }">我的新闻</el-breadcrumb-item>
              <el-breadcrumb-item v-if="isModify">修改新闻</el-breadcrumb-item>
              <el-breadcrumb-item v-else>发布新新闻</el-breadcrumb-item>
            </el-breadcrumb> 
                <el-row class="warp">
                      <!--
                Form 组件提供了表单验证的功能，只需要通过 rule 属性传入约定的验证规则，并 Form-Item 的 prop 属性设置为需校验的字段名即可。具体可以参考官网：http://element.eleme.io/#/zh-CN/component/form
                -->
                  <el-col :span="24" class="warp-main">
                    <el-form ref="infoForm" :model="infoForm" :rules="rules" label-width="50px">
                      <el-form-item label="标题" prop="a_title">
                        <el-input v-model="infoForm.a_title"></el-input>
                      </el-form-item>
                  

                      <el-form-item label="类型" prop="a_title">
                        <el-select v-model="infoForm.a_type" placeholder="a_type">
                          <el-option
                            v-for="item in type"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                      <el-form-item label="图片" prop="imageUrl">
                      <el-upload
                          class="avatar-uploader"
                          :action="serverUrl"
                          :show-file-list="false"
                          :on-success="handleAvatarSuccess"
                          :before-upload="beforeAvatarUpload"
                          :on-error="uploadError">
                          <img v-if="infoForm.imageUrl" :src="infoForm.imageUrl" class="avatar">
                          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>

                      </el-form-item>


                      <!--使用编辑器-->
                      <el-form-item label="内容" prop="a_content">
                        <div class="edit_container">
                            <editor @getContent="getContent" :acontent="this.acontent"></editor>
                        </div>
                      </el-form-item>
                  

                      <el-form-item>
                        <el-button type="primary" @click="onSubmit">发布</el-button>
                      </el-form-item>
                    </el-form>
                  </el-col>
                
                
                </el-row>        
          </el-main> 
        </el-container>
    </el-container>
    

  </div>
  
</template>
<script>
import editor from '@/components/editor';
import Top from '@/components/Top';

export default {
    name:'NewsAddmodify',
    components:{
        editor,Top
    },
    mounted() {
        //判断是否是在修改新闻
        var user=JSON.parse(sessionStorage.getItem("user"));
        this.user=user;
        let id = this.$route.query.NewsID;
        //console.log(id)
        if(id!=undefined){
          this.isModify=true;
          this.addmodify="modify";
          this.getAjaxNewsInfo();
        }else{
          this.isModify=false;
          this.addmodify="add";
        }
    },
    data() {
      return {
        serverUrl: '/api/ImgUploadServlet',  // 这里写你要上传的图片服务器地址
        infoForm: {
          a_title: '',
          a_type:'军事',
          a_content:'',
          imageUrl: '',
        },
        isModify:false,
        addmodify:'add',
        type: [{
            value: '体育',
            label: '体育'
          }, {
            value: '军事',
            label: '军事'
          }, {
            value: '娱乐',
            label: '娱乐'
          }, {
            value: '科技',
            label: '科技'
          }, {
            value: '财经',
            label: '财经'
          }, {
            value: '生活',
            label: '生活'
          }]
        ,
          //表单验证
        rules: {
            a_title: [
                {required: true, message: '请将信息填写完整', trigger: 'blur'}
            ],
            a_type: [
                {required: true, message: '请选择类型', trigger: 'blur'}
            ],
            a_content: [
                {required: true, message: '请填写内容', trigger: 'blur'}
            ],
            imageUrl:[
                {required: true, message: '请上传图片', trigger: 'blur'} 
            ]
        },
        user:{

        },
        acontent:''
      }
    },
    methods: {
     
      onSubmit:function() {
        //提交
        //this.$refs.infoForm.validate，这是表单验证
        this.$refs.infoForm.validate((valid) => {
          if(valid) {
              // console.log(this.user.username)
              
              var user=JSON.parse(sessionStorage.getItem("user"));
              if(user){
                if(user.power!=1){
                    alert("你没有权限发表新闻！");
                    return;
                }
                this.getAjaxAddModifynews();
              }else{
                alert("登录后才能发表");
                return;
              }
              
          }
        });
      },
     
      //-----单张图片----
      handleAvatarSuccess(res, file) {
        // res为图片服务器返回的数据
        // 获取富文本组件实例
        //console.log(res.map);
        // 如果上传成功
        if (res.map.code == 200 ) {
          // 插入图片  res.url为服务器返回的图片地址
          this.infoForm.imageUrl=res.map.url;
        } else {
         alert('图片插入失败')
        }
      },
      beforeAvatarUpload(file) {
        // const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        // if (!isJPG) {
        //   this.$message.error('上传图片只能是 JPG 格式!');
        // }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        //return isJPG && isLt2M;
        return isLt2M;
      },
      getContent:function(content){
        this.infoForm.a_content=content;
      },
      getAjaxAddModifynews:function(){
//        console.log(process.env.NODE_ENV, '环境')
	      let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
        let UP
        if(this.addmodify=="add"){
             UP = { 
            'news_title':this.infoForm.a_title,
            'username':this.user.username, 
            'img_url':this.infoForm.imageUrl,
            'content':this.infoForm.a_content, 
            'type':this.infoForm.a_type,
            'addmodify':this.addmodify
            }
        }else{
            UP = { 
            'news_id':this.$route.query.NewsID,
            'news_title':this.infoForm.a_title,
            'username':this.user.username, 
            'img_url':this.infoForm.imageUrl,
            'content':this.infoForm.a_content, 
            'type':this.infoForm.a_type,
            'addmodify':this.addmodify
            }
        }
        
         // console.log("this.infoForm.imageUrl="+this.infoForm.a_content)
         var successCallback =  (response) => {
            console.log('服务器请求成功了');
            if(this.addmodify=="modify"){
              alert("修改成功！")
            }else{
              alert("发布成功！")
            }
            this.$router.push({ name: 'NewsManage'});
          }
          var errorCallback =  (response) => {
              console.log('服务器请求出错了')
          }
          this.$http.post(urlHost +'/NewsAddModifyServlet',UP,{emulateJSON: true}).then(successCallback,errorCallback);
      },
      getAjaxNewsInfo:function(){
            //获取新闻id
            let id = this.$route.query.NewsID;
            console.log(process.env.NODE_ENV, '环境')
	        let urlHost = process.env.API_HOST // 会自动判断当前是开发还是生产环境，然后自动匹配API_HOST
            let data = { 
                'news_id':id,
                }
            var successCallback =  (response) => {
                if(this.user.username!=response.data.newsInfo.username){
                  alert("你无权限修改新闻");
                  this.$router.push({ name: 'Home'});
                }
                this.infoForm.a_title=response.data.newsInfo.news_title;
                this.infoForm.imageUrl=response.data.newsInfo.img_url;
                this.acontent=response.data.newsInfo.news_content;
                this.infoForm.a_type=response.data.newsInfo.type;

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
<style>
#ReleaseNews>.el-container{
    width: 70%;
    margin: 0 auto;
}

#ReleaseNews .box{
    margin:20px 0 40px; 
    font-size: 15px;
}

#ReleaseNews .el-header{ 
    margin-top:40px;
    background-color: #B3C0D1;
    text-align: center;
    padding:0px;
    line-height: 36px;
}

#ReleaseNews .el-main {
    background-color: rgb(255, 255, 255);
    color: #333;
    text-align: center;
    min-height: 580px;
    height: auto;
    padding:20px 5%;
    box-sizing:border-box;
}
#ReleaseNews button{
  float: right;
}
#ReleaseNews .el-select {
  float: left;
}
#ReleaseNews .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    float: left;
  }
#ReleaseNews  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
#ReleaseNews  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
#ReleaseNews  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  
</style>