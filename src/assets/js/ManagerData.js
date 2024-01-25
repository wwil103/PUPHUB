export default {
  data() {
    return {
      isCollapse: false, //菜单栏默认展开
      collapseBtnClass: 'el-icon-s-fold', //菜单栏icon
      sideWidth: 200, //菜单栏默认宽度
      pageNum: 1, //分页默认页数
      pageSize: 5, //分页默认大小
      total: 0, //默认显示总条数
      tableData: [{}], //主表数据
      form: {}, //弹窗表单数据
      multipleSelection: [], //表单多选id数据
      resetTableBtn: false, //重置按钮显示
      loading: true, //主表加载遮罩显示
      order: {
        //订单表单
        formDialog: {
          formTitle: '',
          formBtnValue: '',
          dialogFormVisible: false,
          formWidth: '40%',
          fullScreen: false,
          formItem: [
            {
              label: '订单编号',
              name: 'oId',
              type: 'text',
              placeholder: '请输入订单编号',
              value: '',
              show: true,
            },
            {
              label: '用户ID',
              name: 'oUid',
              type: 'text',
              placeholder: '请输入用户ID',
              value: '',
              show: true,
            },
            {
              label: '服务名称',
              name: 'oService',
              type: 'option',
              local: 'Service',
              options: 'service',
              show: true,
            },
            {
              label: '日期',
              name: 'oDate',
              type: 'date',
              value: '',
              show: true,
            },
            {
              label: '时间',
              name: 'oTime',
              type: 'option',
              local: 'Time',
              options: [
                { label: '早上', value: '1' },
                { label: '下午', value: '2' },
                { label: '晚上', value: '3' },
              ],
              show: true,
            },
            {
              label: '狗狗年龄',
              name: 'oAge',
              type: 'option',
              local: 'Age',
              options: [
                { label: '幼犬', value: '1' },
                { label: '成年犬', value: '2' },
                { label: '老年犬', value: '3' },
              ],
              show: true,
            },
            {
              label: '狗狗大小',
              name: 'oSize',
              type: 'option',
              local: 'Size',
              options: [
                { label: '小型犬', value: '1' },
                { label: '中型犬', value: '2' },
                { label: '大型犬', value: '3' },
              ],
              show: true,
            },
            {
              label: '地址',
              name: 'oAddress',
              type: 'address',
              placeholder: '请输入地址',
              value: '',
              show: true,
            },
            {
              label: '联系方式',
              name: 'oContact',
              type: 'text',
              placeholder: '请输入联系方式',
              value: '',
              show: true,
            },
            {
              label: '订单状态',
              name: 'oStatus',
              type: 'option',
              local: 'Status',
              options: [
                { label: '服务待开始', value: '0' },
                { label: '服务进行中', value: '1' },
                { label: '服务已完成', value: '2' },
              ],
              show: true,
            },
          ],
        },
      },
      user: {
        //用户表单
        formDialog: {
          formTitle: '',
          formBtnValue: '',
          dialogFormVisible: false,
          formWidth: '40%',
          fullScreen: false,
          formItem: [
            {
              label: 'ID',
              name: 'uId',
              type: 'text',
              placeholder: '请输入用户ID',
              value: '',
              show: true,
            },
            {
              label: '用户名',
              name: 'uName',
              type: 'text',
              placeholder: '请输入用户名',
              value: '',
              show: true,
            },
            {
              label: '性别',
              name: 'uGender',
              type: 'option',
              local: 'Gender',
              options: [
                { label: '未填写', value: '0' },
                { label: '男', value: '1' },
                { label: '女', value: '2' },
              ],
              show: true,
            },
            {
              label: '手机号',
              name: 'uTelephone',
              type: 'text',
              placeholder: '请输入手机号',
              value: '',
              show: true,
            },
            {
              label: '邮箱',
              name: 'uEmail',
              type: 'text',
              placeholder: '请输入邮箱',
              value: '',
              show: true,
            },
            {
              label: '出生日期',
              name: 'uBirth',
              type: 'date',
              value: '',
              show: true,
            },
            {
              label: '密码',
              name: 'uPassword',
              type: 'text',
              placeholder: '请输入密码',
              value: '',
              show: true,
            },
            {
              label: '地址',
              name: 'uAddress',
              type: 'address',
              placeholder: '请输入地址',
              value: '',
              show: true,
            },
          ],
        },
      },
      service: {
        //服务表单
        formDialog: {
          formTitle: '',
          formBtnValue: '',
          dialogFormVisible: false,
          formWidth: '30%',
          fullScreen: false,
          formItem: [
            {
              label: 'ID',
              name: 'sId',
              type: 'text',
              placeholder: '请输入服务ID',
              value: '',
              show: true,
            },
            {
              label: '服务名称',
              name: 'sName',
              type: 'text',
              placeholder: '请输入服务名称',
              value: '',
              show: true,
            },
            {
              label: '服务价格',
              name: 'sPrice',
              type: 'number',
              disabled: false,
              value: '0',
              show: true,
              placeholder: '请输入服务价格',
            },
            {
              label: '服务简介',
              name: 'sIntro',
              type: 'textarea',
              row: 4,
              placeholder: '请输入服务简介',
              value: '',
              show: true,
            },
            {
              label: '服务海报',
              name: 'sPoster',
              type: 'avatar',
              showFileList: false,
              value: '',
            },
          ],
        },
      },
    };
  },
  methods: {
    handleFormBtn() {}, //Dialog动态按钮
  },
};
