function loadTable() {
    console.log("!!!!!")
    $('#table').bootstrapTable({
        url: 'salary/showAll1',
        method: 'GET',
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        // pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        // pageSize: rows,                     //每页的记录行数（*）
        // pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        columns: [
            {
                "field":"id",
                "title":"月份"
            },
            {
                "field":"total_salary",
                "title":"本月工资"
            },
            {
                "field":"real_salary",
                "title":"实发工资"
            },
            {
                "field":"pre_tax_add",
                "title":"税前补款"
            },
            {
                "field":"pre_tax_reduce",
                "title":"税前扣款"
            },
            {
                "field":"kaoqin_reduce",
                "title":"考勤扣款"
            },
            {
                "field":"shebao_reduce",
                "title":"社保公积金个人扣款"
            },
            {
                "field":"pre_reduce",
                "title":"本期预扣预缴税额"
            },
            {
                "field":"after_tax_add",
                "title":"税后补款"
            },
            {
                "field":"after_tax_reduce",
                "title":"税后扣款"
            }
        ]
    });
}