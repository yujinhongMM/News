<template>
    <div>
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="首页" name="first">
                <News type="军事"></News>
            </el-tab-pane>
            <el-tab-pane label="体育" name="second">
                <News type="体育"></News>
            </el-tab-pane>
            <el-tab-pane label="军事" name="third">
                <News type="军事"></News>
            </el-tab-pane>
            <el-tab-pane label="娱乐" name="fourth">
                <News type="娱乐"></News>
            </el-tab-pane>
            <el-tab-pane label="科技" name="five">
                <News type="科技"></News>
            </el-tab-pane>

            <el-tab-pane label="财经" name="six">
                <News type="财经"></News>
            </el-tab-pane>

            <el-tab-pane label="生活" name="seven">
                <News type="生活"></News>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
import News from '@/components/News'
export default {
    name: 'TypeTab',
    components:{
        News
    },
    data() {
      return {
        activeName: 'first'
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
}
</script>


<style scoped>

</style>
