<template>
  <div id="app">
    <vue-particles
        color="#fff"
        :particleOpacity="0.7"
        :particlesNumber="60"
        shapeType="polygon"
        :particleSize="4"
        linesColor="#fff"
        :linesWidth="1"
        :lineLinked="true"
        :lineOpacity="0.4"
        :linesDistance="150"
        :moveSpeed="2"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="true"
        clickMode="push"
        class="particles-js"
      >
      </vue-particles>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted:function(){//在页面第一次加载的时候确定页面的最小宽度
    var pageWitdth=window.clientWidth;
    if(typeof pageWitdth !="number"){
      if(document.compatMode=="CSS1Compat"){
        pageWitdth=document.documentElement.clientWidth;
      }else{
        pageWitdth=document.body.clientWidth;
      }
    }
    if(pageWitdth>1100){
      document.getElementById("app").style.minWidth=0.8*pageWitdth+'px';
    }else{
      document.getElementById("app").style.minWidth=880+'px';
    }
    
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
*{
  margin: 0px;
  padding:0px;
}
.particles-js{
  width: 100%;
	height: 100%;
	position: fixed;
  z-index: -1;
	background-image: url(../src/assets/bg.jpg);
	background-position: 50% 50%;
	background-size: cover;
	background-repeat: no-repeat;
	margin-left: auto;
	margin-right: auto;
}
</style>
