import { TOKEN_KEY, ROLES_KEY, USER_INFO_KEY, DB_DICT_DATA_KEY, TENANT_ID, LOGIN_INFO_KEY, PROJ_CFG_KEY } from '/@/enums/cacheEnum';
import { omit } from 'lodash-es';

export interface Cache<V = any> {
  value?: V;
  timeoutId?: ReturnType<typeof setTimeout>;
  time?: number;
  alive?: number;
}

const NOT_ALIVE = 0;
// vuex6 自己定义的内存对象结构
export class Memory<T = any, V = any> {
  // key in keyof T  js中索引签名写法，代表通过T类型的key获取到对应的Cache
  // 上面我们将Cache定义了对象结构， ={} 初始化一个空对象，类似Map<key,Value>
  private cache: { [key in keyof T]?: Cache<V> } = {};
  private alive: number;

  constructor(alive = NOT_ALIVE) {
    // Unit second
    this.alive = alive * 1000;
  }

  get getCache() {
    return this.cache;
  }

  setCache(cache) {
    this.cache = cache;
  }

  // get<K extends keyof T>(key: K) {
  //   const item = this.getItem(key);
  //   const time = item?.time;
  //   if (!isNullOrUnDef(time) && time < new Date().getTime()) {
  //     this.remove(key);
  //   }
  //   return item?.value ?? undefined;
  // }

  get<K extends keyof T>(key: K) {
    return this.cache[key];
  }

  /**
   * 判断是否存在旧的，存在则
   * @param key 
   * @param value 
   * @param expires 
   * @returns 
   */
  set<K extends keyof T>(key: K, value: V, expires?: number) {
    // let 当前作用域，且可以被修改
    let item = this.get(key);

    if (!expires || (expires as number) <= 0) {
      expires = this.alive;
    }
    if (item) {
      if (item.timeoutId) {
        clearTimeout(item.timeoutId);
        item.timeoutId = undefined;
      }
      // 更新value
      item.value = value;
    } else {
      // 新的键值对，则创建新对象，存入
      item = { value, alive: expires };
      this.cache[key] = item;
    }
    // 没有过期时间，则直接 返回，不用设置过期删除任务
    if (!expires) {
      return value;
    }
    // 有过期时间，则设置延迟任务进行删除，延迟删除 ，这不就是时间轮的原理
    const now = new Date().getTime();
    item.time = now + this.alive;
    item.timeoutId = setTimeout(
      () => {
        this.remove(key);
      },
      expires > now ? expires - now : expires
    );

    return value;
  }

  remove<K extends keyof T>(key: K) {
    const item = this.get(key);
    Reflect.deleteProperty(this.cache, key);
    if (item) {
      clearTimeout(item.timeoutId!);
      return item.value;
    }
  }
 // vuex5.1 重置cache，重置的应该是有效期时间
  resetCache(cache: { [K in keyof T]: Cache }) {
    Object.keys(cache).forEach((key) => {
      const k = key as any as keyof T;
      const item = cache[k];
      if (item && item.time) {
        const now = new Date().getTime();
        const expire = item.time;
        if (expire > now) {
          this.set(k, item.value, expire);
        }
      }
    });
  }

  clear() {
    console.log('------clear------进入clear方法');
    Object.keys(this.cache).forEach((key) => {
      const item = this.cache[key];
      item.timeoutId && clearTimeout(item.timeoutId);
    });
    //update-begin---author:liusq  Date:20220108  for：不删除登录用户的租户id，其他缓存信息都清除----
    this.cache = {
      ...omit(this.cache, [TOKEN_KEY, USER_INFO_KEY, ROLES_KEY, DB_DICT_DATA_KEY, TENANT_ID, LOGIN_INFO_KEY, PROJ_CFG_KEY]),
    };
    //update-end---author:liusq  Date:20220108  for：不删除登录用户的租户id，其他缓存信息都清除----
  }
}
