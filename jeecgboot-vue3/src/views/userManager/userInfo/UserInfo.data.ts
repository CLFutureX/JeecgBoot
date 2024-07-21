import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '所属部门',
    align:"center",
    dataIndex: 'sysOrgCode'
   },
   {
    title: '用户账号',
    align:"center",
    dataIndex: 'userAccount'
   },
   {
    title: '用户姓名',
    align:"center",
    dataIndex: 'userName'
   },
   {
    title: '用户头像',
    align:"center",
    dataIndex: 'userImg',
    customRender:render.renderImage,
   },
   {
    title: '用户性别',
    align:"center",
    dataIndex: 'userSex'
   },
   {
    title: '用户手机号',
    align:"center",
    dataIndex: 'userTel'
   },
   {
    title: '用户密码',
    align:"center",
    dataIndex: 'userPassword'
   },
   {
    title: '用户角色',
    align:"center",
    dataIndex: 'userRole'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "所属部门",
      field: 'sysOrgCode',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "用户账号",
      field: 'userAccount',
      component: 'Input',
      //colProps: {span: 6},
 	},
     {
      label: "用户姓名",
      field: "userName",
      component: 'Input', //TODO 范围查询
      //colProps: {span: 6},
	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '所属部门',
    field: 'sysOrgCode',
    component: 'Input',
  },
  {
    label: '用户账号',
    field: 'userAccount',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入用户账号!'},
          ];
     },
  },
  {
    label: '用户姓名',
    field: 'userName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入用户姓名!'},
          ];
     },
  },
  {
    label: '用户头像',
    field: 'userImg',
     component: 'JImageUpload',
     componentProps:{
        fileMax: 0
      },
  },
  {
    label: '用户性别',
    field: 'userSex',
    component: 'InputNumber',
  },
  {
    label: '用户手机号',
    field: 'userTel',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入用户手机号!'},
          ];
     },
  },
  {
    label: '用户密码',
    field: 'userPassword',
    component: 'Input',
  },
  {
    label: '用户角色',
    field: 'userRole',
    component: 'InputNumber',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  sysOrgCode: {title: '所属部门',order: 0,view: 'text', type: 'string',},
  userAccount: {title: '用户账号',order: 1,view: 'text', type: 'string',},
  userName: {title: '用户姓名',order: 2,view: 'text', type: 'string',},
  userImg: {title: '用户头像',order: 3,view: 'image', type: 'string',},
  userSex: {title: '用户性别',order: 4,view: 'number', type: 'number',},
  userTel: {title: '用户手机号',order: 5,view: 'text', type: 'string',},
  userPassword: {title: '用户密码',order: 6,view: 'text', type: 'string',},
  userRole: {title: '用户角色',order: 7,view: 'number', type: 'number',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}