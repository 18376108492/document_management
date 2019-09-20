/**
 * Created by huzhihui on 2016/9/30.
 */

/**
 * 全局列表json
 * @type {{}}
 */
var TAB_JSON = {
    list: [
        {
            listName: '首页',/*tab名称*/
            listUrl: 'disk/main',/*页面路径*/
            listUuid: 'uuid-main',/*tab唯一标识*/
            listIcon: 'am-icon-home',/*左侧显示图标*/
            listRightIcon: '',/*右侧显示图标*/
            listNumber: '',/*右侧显示数字数据*/
            listNumberColor: 'am-badge am-badge-secondary',/*右侧显示数字数据样式*/
            isHtml: true/*是否是页面，false为折叠tab*/
        },
        {
            listName: '功能模版',
            listUrl: '',
            listUuid: 'uuid-moban',
            listIcon: 'am-icon-file',
            listRightIcon: 'am-icon-angle-right',
            listNumber: '',
            listNumberColor: '',
            isHtml: false,
            subList: [
                {
                    listName: '邮箱管理',
                    listUrl: 'data/personal-data',
                    listUuid: 'uuid-personal-data',
                    listIcon: 'am-icon-comment',
                    listRightIcon: 'am-icon-star',
                    listNumber: '',
                    listNumberColor: 'admin-icon-yellow',
                    isHtml: true
                },
                {
                    listName: '磁盘资源',
                    listUrl: 'html/main.jsp',
                    listUuid: 'uuid-help',
                    listIcon: 'am-icon-folder',
                    listRightIcon: '',
                    listNumber: '',
                    listNumberColor: 'am-badge am-badge-secondary',
                    isHtml: true
                },
                {
                    listName: '历史记录',
                    listUrl: 'document/histroy',
                    listUuid: 'uuid-help',
                    listIcon: 'am-icon-clock-o',
                    listRightIcon: '',
                    listNumber: '',
                    listNumberColor: 'am-badge am-badge-secondary',
                    isHtml: true
                },
                 {
                    listName: '资料备份',
                    listUrl: 'document/backups.jsp',
                    listUuid: 'uuid-help',
                    listIcon: 'am-icon-arrow-down',
                    listRightIcon: '',
                    listNumber: '',
                    listNumberColor: 'am-badge am-badge-secondary',
                    isHtml: true
                },
            ]
        }
    ]
};