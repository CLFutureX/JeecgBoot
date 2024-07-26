/**
 * Global authority directive
 * Used for fine-grained control of component permissions
 * @Example v-auth="RoleEnum.TEST"
 */
import type { App, Directive, DirectiveBinding } from 'vue';

import { usePermission } from '/@/hooks/web/usePermission';

// vuex8 自定义auth指令： 传入元素el，binding 对象
function isAuth(el: Element, binding: any) {
  // update-begin--author:liaozhiyang---date:20240529---for【TV360X-460】basicForm支持v-auth指令(权限控制显隐)
  // 获取绑定的对象值 
  const value = binding.value;
  // 为false时直接返回-不做任何校验，可以认为是否要进行认证
  if (!value) return;
  // update-end--author:liaozhiyang---date:20240529---for【TV360X-460】basicForm支持v-auth指令(权限控制显隐)
  // 获取当前的权限信息
  const { hasPermission } = usePermission();
  // 判断用户是否具有这个权限，如果没有则移除，从而实现用户看不到这个元素
  if (!hasPermission(value)) {
    el.parentNode?.removeChild(el);
  }
}

const mounted = (el: Element, binding: DirectiveBinding<any>) => {
  isAuth(el, binding);
};

const authDirective: Directive = {
  mounted,
};

export function setupPermissionDirective(app: App) {
  app.directive('auth', authDirective);
}

export default authDirective;
