<template>

<div class="Home">
    <el-container>
        <el-header style="height: 46px !important;"><top></top></el-header>
        <el-container>
            <el-main>
                <TypeTab></TypeTab>
            </el-main>
            <el-aside style="width:320px;">
                <RankingList></RankingList>
            </el-aside>
        </el-container>
    </el-container>    
   
</div>

</template>

<script>
import Top from '@/components/Top'
import TypeTab from '@/components/TypeTab'
import RankingList from '@/components/RankingList'
export default {
    components:{
        Top,
        TypeTab,
        RankingList
    }
}

</script>

<style scoped>
.Home{
    width: 100%;
}
.Home>.el-container{
    width: 80%;
    margin: 0 auto;
}
.el-header{ 
    margin-top:40px;
    background-color: #B3C0D1;
    text-align: center;
    padding:0px;
    line-height: 36px;
}

.el-aside {
    text-align: center;
    background-color: white;
    box-shadow: 0px 0px 5px #333333;
}

.el-main {
    background-color: rgb(255, 255, 255);
    color: #333;
    text-align: center;
    height: 500px;
    padding:20px 5%;
}

</style>


