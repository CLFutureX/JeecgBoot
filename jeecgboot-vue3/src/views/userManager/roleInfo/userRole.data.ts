import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '角色名称',
    align:"center",
    dataIndex: 'roleName'
   },
   {
    title: '角色编码',
    align:"center",
    dataIndex: 'roleCode'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "创建日期",
      field: 'createTime',
      component: 'DatePicker',
      componentProps: {
         showTime:true,
         valueFormat: 'YYYY-MM-DD HH:mm:ss'
       },
      //colProps: {span: 6},
 	},
	{
      label: "角色名称",
      field: 'roleName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "角色编码",
      field: 'roleCode',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '角色名称',
    field: 'roleName',
    component: 'Input',
  },
  {
    label: '角色编码',
    field: 'roleCode',
    component: 'Input',
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
  roleName: {title: '角色名称',order: 1,view: 'text', type: 'string',},
  roleCode: {title: '角色编码',order: 2,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}