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
    title: '操作人',
    align:"center",
    dataIndex: 'userName'
   },
   {
    title: '操作类型',
    align:"center",
    dataIndex: 'operatorType_dictText'
   },
   {
    title: '操作前数据',
    align:"center",
    dataIndex: 'operatorBefore'
   },
   {
    title: '操作后数据',
    align:"center",
    dataIndex: 'operatorAfter'
   },
   {
    title: '数据类型',
    align:"center",
    dataIndex: 'type'
   },
   {
    title: "创建日期",
    align:"center",
    dataIndex: 'createTime'
    //colProps: {span: 6},
 }
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
      label: "所属部门",
      field: 'sysOrgCode',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "操作人",
      field: 'userName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "操作类型",
      field: 'operatorType',
      component: 'JDictSelectTag',
      componentProps:{
      },
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
    label: '操作人',
    field: 'userName',
    component: 'Input',
  },
  {
    label: '操作类型',
    field: 'operatorType',
    component: 'JCheckbox',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '操作前数据',
    field: 'operatorBefore',
    component: 'Input',
  },
  {
    label: '操作后数据',
    field: 'operatorAfter',
    component: 'Input',
  },
  {
    label: '数据类型',
    field: 'type',
    component: 'InputNumber',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
  {
    label: "创建日期",
    field: 'createTime',
    component: 'DatePicker',
    componentProps: {
       showTime:true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
    //colProps: {span: 6},
 }
];

// 高级查询数据
export const superQuerySchema = {
  sysOrgCode: {title: '所属部门',order: 1,view: 'text', type: 'string',},
  userName: {title: '操作人',order: 2,view: 'text', type: 'string',},
  operatorType: {title: '操作类型',order: 3,view: 'checkbox', type: 'string',},
  operatorBefore: {title: '操作前数据',order: 4,view: 'text', type: 'string',},
  operatorAfter: {title: '操作后数据',order: 5,view: 'text', type: 'string',},
  type: {title: '数据类型',order: 6,view: 'number', type: 'number',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}