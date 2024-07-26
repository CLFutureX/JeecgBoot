// 单点登录核心类
import { getToken } from '/@/utils/auth';
import { getUrlParam } from '/@/utils';
import { useGlobSetting } from '/@/hooks/setting';
import { validateCasLogin } from '/@/api/sys/user';
import { useUserStore } from '/@/store/modules/user';
const globSetting = useGlobSetting();
const openSso = globSetting.openSso;
export function useSso() {
  //update-begin---author:wangshuai---date:2024-01-03---for:【QQYUN-7805】SSO登录强制用http #957---
  let locationUrl = document.location.protocol +"//" + window.location.host + '/';
  //update-end---author:wangshuai---date:2024-01-03---for:【QQYUN-7805】SSO登录强制用http #957---

  /**
   * 单点登录
   */
  async function ssoLogin() {
    if (openSso == 'true') {
      let token = getToken();
      let ticket = getUrlParam('ticket');
      if (!token) {
        if (ticket) {
          // 校验门票
          await validateCasLogin({
            ticket: ticket,
            service: locationUrl,
          }).then((res) => {
            const userStore = useUserStore();
            // token缓存
            userStore.setToken(res.token);
            // 登录之后，完成页面跳转
            return userStore.afterLoginAction(true, {});
          });
        } else {
          // 校验失败，调整到登录页面？
          window.location.href = globSetting.casBaseUrl + '/login?service=' + encodeURIComponent(locationUrl);
        }
      }
    }
  }

  /**
   * 退出登录
   */
  async function ssoLoginOut() {
    window.location.href = globSetting.casBaseUrl + '/logout?service=' + encodeURIComponent(locationUrl);
  }
  return { ssoLogin, ssoLoginOut };
}
