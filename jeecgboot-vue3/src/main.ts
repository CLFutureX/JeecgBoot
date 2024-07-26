import 'uno.css';
import '/@/design/index.less';
import 'ant-design-vue/dist/reset.css';
// 注册图标
import 'virtual:svg-icons-register';

import App from './App.vue';
import { createApp } from 'vue';
import { initAppConfigStore } from '/@/logics/initAppConfig';
import { setupErrorHandle } from '/@/logics/error-handle';
import { router, setupRouter } from '/@/router';
import { setupRouterGuard } from '/@/router/guard';
import { setupStore } from '/@/store';
import { setupGlobDirectives } from '/@/directives';
import { setupI18n } from '/@/locales/setupI18n';
import { registerGlobComp } from '/@/components/registerGlobComp';
import { registerThirdComp } from '/@/settings/registerThirdComp';
import { useSso } from '/@/hooks/web/useSso';
// 注册online模块lib
import { registerPackages } from '/@/utils/monorepo/registerPackages';
 

async function bootstrap() {
  // 创建应用实例 其实就是一个app实例，
  const app = createApp(App);
  // 【QQYUN-6329】  app 根实例
  window.appRootInstance = app;
  // 多语言配置,异步情况:语言文件可以从服务器端获得
  // vuex3 等待语言设置？ 类似java中的阻塞等待机制一样。
  await setupI18n(app);

  // 配置存储
  setupStore(app);

  // 初始化内部系统配置
  initAppConfigStore();

  // 注册外部模块路由(注册online模块lib)
  registerPackages(app);

  // 注册全局组件
  registerGlobComp(app);

  //CAS单点登录- 打开了则进行登录，登录成功，则就会跳转到主页
  await useSso().ssoLogin();

  // 配置路由
  setupRouter(app);

  // 路由保护
  setupRouterGuard(router);

  // 注册全局指令
  setupGlobDirectives(app);
 
  // 配置全局错误处理
  setupErrorHandle(app);

  // 注册第三方组件
  await registerThirdComp(app);

  // 当路由准备好时再执行挂载( https://next.router.vuejs.org/api/#isready)
  await router.isReady();

  // 挂载应用
  app.mount('#app', true);

  console.log(" vue3 app 加载完成！")
}

// vuex2 加载该main.ts脚本
bootstrap();
