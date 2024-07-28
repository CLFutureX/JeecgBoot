import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
import {statusConfig,checkConfig,groupConfig} from './config/customConfig'
import { options } from '../monitor/mynews/XssWhiteList';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '客户名称',
    align:"center",
    dataIndex: 'customName'
   },
   {
    title: '客户联系方式',
    align:"center",
    dataIndex: 'customTel'
   },
   {
    title: '客户地址',
    align:"center",
    dataIndex: 'customAddr',
   },
   {
    title: '业务员名称',
    align:"center",
    dataIndex: 'businessName'
   },
   {
    title: '类别/系别',
    align:"center",
    dataIndex: 'type_dictText'
   },
   {
    title: '状态',
    align:"center",
    dataIndex: 'status_dictText'
   },
   {
    title: '检查项',
    align:"center",
    dataIndex: 'checkItem_dictText'
   },
   {
    title: '是否建群',
    align:"center",
    dataIndex: 'createGroup_dictText'
   },
   {
    title: '清洗情况',
    align:"center",
    dataIndex: 'clearType_dictText'
   },
   {
    title: '维修情况',
    align:"center",
    dataIndex: 'repairType_dictText'
   },
   {
    title: '安装情况',
    align:"center",
    dataIndex: 'installType_dictText'
   },
   {
    title: '安装单位',
    align:"center",
    dataIndex: 'installCompany_dictText'
   },
   {
    title: '项目经理',
    align:"center",
    dataIndex: 'projectManager'
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
      label: "所属部门",
      field: 'orgCode',
      component: 'JDictSelectTag',
      componentProps:{
        options: groupConfig
      },
      //colProps: {span: 6},
 	},
	{
      label: "客户名称",
      field: 'customName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "客户联系方式",
      field: 'customTel',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "客户地址",
      field: 'customAddr',
      component: 'JAreaLinkage',
      componentProps: {
        saveCode: 'region',
      },
      //colProps: {span: 6},
 	},
	{
      label: "业务员名称",
      field: 'businessName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "类别/系别",
      field: 'type',
      component: 'JDictSelectTag',
      componentProps:{
      },
      //colProps: {span: 6},
 	},
	{
      label: "状态",
      field: 'status',
      component: 'JDictSelectTag',
      componentProps:{
        options: statusConfig
      },
      //colProps: {span: 6},
 	},
	{
      label: "检查项",
      field: 'checkItem',
      component: 'JDictSelectTag',
      componentProps:{
        options: checkConfig
      },
      //colProps: {span: 6},
 	},
	{
      label: "是否建群",
      field: 'createGroup',
      component: 'JDictSelectTag',
      componentProps:{
      },
      //colProps: {span: 6},
 	},
	{
      label: "清洗情况",
      field: 'clearType',
      component: 'JDictSelectTag',
      componentProps:{
      },
      //colProps: {span: 6},
 	},
	{
      label: "维修情况",
      field: 'repairType',
      component: 'JDictSelectTag',
      componentProps:{
      },
      //colProps: {span: 6},
 	},
	{
      label: "安装情况",
      field: 'installType',
      component: 'JDictSelectTag',
      componentProps:{
      },
      //colProps: {span: 6},
 	},
	{
      label: "安装单位",
      field: 'installCompany',
      component: 'JDictSelectTag',
      componentProps:{
      },
      //colProps: {span: 6},
 	},
	{
      label: "项目经理",
      field: 'projectManager',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '客户名称',
    field: 'customName',
    component: 'Input',
  },
  {
    label: '客户联系方式',
    field: 'customTel',
    component: 'Input',
  },
  {
    label: '客户地址',
    field: 'customAddr',
    component: 'JAreaLinkage',
    componentProps: {
      saveCode: 'region',
    },
  },
  {
    label: '业务员名称',
    field: 'businessName',
    component: 'Input',
  },
  {
    label: "所属部门",
    field: 'orgCode',
    component: 'JDictSelectTag',
    componentProps:{
      options: groupConfig
    },
    //colProps: {span: 6},
 },
  {
    label: '类别/系别',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps:{
     },
  },
  {
    label: '状态',
    field: 'status',
    component: 'JCheckbox',
    componentProps:{
        options: statusConfig
     },
  },
  {
    label: '检查项',
    field: 'checkItem',
    component: 'JCheckbox',
    componentProps:{
        options: checkConfig
     },
  },
  {
    label: '是否建群',
    field: 'createGroup',
    component: 'JCheckbox',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '清洗情况',
    field: 'clearType',
    component: 'JCheckbox',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '维修情况',
    field: 'repairType',
    component: 'JCheckbox',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '安装情况',
    field: 'installType',
    component: 'JCheckbox',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '安装单位',
    field: 'installCompany',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"",
        type: "radio"
     },
  },
  {
    label: '项目经理',
    field: 'projectManager',
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
  customName: {title: '客户名称',order: 2,view: 'text', type: 'string',},
  customTel: {title: '客户联系方式',order: 3,view: 'text', type: 'string',},
  customAddr: {title: '客户地址',order: 4,view: 'pca', type: 'string',},
  businessName: {title: '业务员名称',order: 5,view: 'text', type: 'string',},
  orgCode: {title:'所属部门', order: 5.5, view: 'text', type:'string',},
  type: {title: '类别/系别',order: 6,view: 'number', type: 'number'},
  status: {title: '状态',order: 7,view: 'number', type: 'number'},
  checkItem: {title: '检查项',order: 8,view: 'number', type: 'number'},
  createGroup: {title: '是否建群',order: 9,view: 'number', type: 'number'},
  clearType: {title: '清洗情况',order: 10,view: 'number', type: 'number'},
  repairType: {title: '维修情况',order: 11,view: 'number', type: 'number'},
  installType: {title: '安装情况',order: 12,view: 'number', type: 'number'},
  installCompany: {title: '安装单位',order: 13,view: 'radio', type: 'string'},
  projectManager: {title: '项目经理',order: 14,view: 'text', type: 'string'},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}