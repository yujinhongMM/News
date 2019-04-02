<template>
    <div class="NewsInfo">
        <el-container>
            <el-header style="height: 46px !important;margin-top: 0px !important;"><top></top></el-header>
            <el-container class="main">
                 <el-main>
                        <Content></Content>
                        <Comment></Comment>
                </el-main> 
            </el-container>
        </el-container>
    </div>
</template>

<script>

import Top from '@/components/Top';
import Content from '@/components/Content';
import Comment from '@/components/comment';

export default {
   props:['NewsID'],
   components:{
        Top,Content,Comment
    }
}
</script>

<style scoped>
.NewsInfo>.el-container{
    width: 70%;
    margin: 0 auto;
}


.el-header{ 
    margin-top:40px;
    background-color: #B3C0D1;
    text-align: center;
    padding:0px;
    line-height: 36px;
}

.el-main {
    background-color: rgb(255, 255, 255);
    color: #333;
    text-align: center;
    height: auto;
    padding:20px 5%;
    box-sizing:border-box;
}


</style>

