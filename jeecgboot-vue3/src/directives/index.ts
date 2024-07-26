/**
 * Configure and register global directives
 */
import type { App } from 'vue';
import { setupPermissionDirective } from './permission';
import { setupLoadingDirective } from './loading';

/**
 * 注册指令： 类似v-bind,v-on 等指令，自定义的话，使用会更方面
 * @param app 
 */
export function setupGlobDirectives(app: App) {
  // 权限指令： 通过指令判断是否要进行校验检查
  setupPermissionDirective(app);
  setupLoadingDirective(app);
}
