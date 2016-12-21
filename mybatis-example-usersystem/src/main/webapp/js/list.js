$('#userList').datagrid({    
	url:"user/list",
	pagination:true,
	fitColumns:true,
	pageList:[5,10,15,25,30],
    columns:[[    
        {
		field : 'id',
		title : '编号',
		width : 100,
		align : 'center'
	}, {
		field : 'name',
		title : '姓名',
		width : 100,
		align : 'center'
	}, {
		field : 'brithday',
		title : '生日',
		width : 100,
		align : 'center'
	},    {
		field : 'gender',
		title : '性别',
		width : 100,
		align : 'center'
	},   {
		field : 'career',
		title : '职位',
		width : 100,
		align : 'center'
	},    {
		field : 'address',
		title : '家庭住址',
		width : 100,
		align : 'center'
	},     {
		field : 'mobile',
		title : '联系电话',
		width : 100,
		align : 'center'
	},    {
		field : 'picPath',
		title : '图片',
		width : 100,
		align : 'center',
		formatter: function(value,row,index){
			if(value == null){
				return "<img width='100' src='image/not_pic.jpg'/>"
			}else{
				return "<img width='100' src='"+value+"'/>"
			}
		}      
	},    {
		field : 'opr',
		title : '操作',
		width : 100,
		align : 'center',
		formatter: function(value,row,index){
				var oprStr = '<a class="detailBtn" href="javascript:void(0)" onclick="openDadail()">详情</a>&nnbsp;&nbsp; '+
				'<a class="modifyBtn" href="javascript:void(0)" onclick="openUpdate('+index+')">修改</a>'+
				'<script>$(".detailBtn").linkbutton({iconCls: "icon-search "});' +'$(".modifyBtn").linkbutton({iconCls: "icon-edit" });</script>';  
				return oprStr;
		}      
	}
    ]],
    data: [
   		{id:'1001', name:'aaaaa'},
   		{id:'1002', name:'bbbbb'}
   	]

});  

$("#modifyDiv").dialog({
	title:"用户修改",
	closable:false
});

$("#modifyDiv").dialog("close");

$('#modifyForm').form({    
    url:"user/modify",    
    success:function(data){    
        if(data.trim()=="true"){
        	$("#modifyDiv").dialog("close");
        	$('#userList').datagrid('reload');
        	alert("修改成功");
        }else{
        	$.messager.show({
        		title:'消息',
        		msg:'修改失败。',
        		showType:'show',
        		style:{
        			right:'',
        			top:document.body.scrollTop+document.documentElement.scrollTop,
        			bottom:''
        		}
        	});

        } 
    }    
});    


$(".closeBtn").linkbutton({    
    iconCls: 'icon-cancel' ,
    onClick: function(){
    	$("#modifyDiv").dialog("close");
    }
}); 

$(".updateBtn").linkbutton({
	iconCls: 'icon-ok',
	onClick: function(){
    	$("#modifyForm").submit();
    }
});

function openUpdate(index){
	$("#modifyDiv").dialog("open");
	var row=$('#userList').datagrid('getRows')[index];
	$("#uid").val(row.id);
	$("#uname").val(row.name);
	$("#ubirthday").val(row.birthday);
	$("#ugender").val(row.gender);
	$("#ucareer").val(row.career);
	$("#uaddress").val(row.address);
	$("#umobile").val(row.mobile);
	if(row.picPath){
		$("#pic").attr("src",row.picPath);
	}
};

function chgPic(obj){
	$("#pic").attr("src",window.URL.createObjectURL(obj.files[0]));
}





