    $(document).ready(function(){
        var str = "";
        var strA = "";
        $.ajax({
            type: "GET",
            url: "/disk/load_disk",
            dataType: "json",
            async:false,//是否开启异步
            data:{},
            success: function(data){
                for (var i in data)
                {
                    if( i == 0 ){
                        str+="<li><a href='/disk/getDiskById?id='"+data[i]['id'] +
                            "class='am-text-success'><span id='refsh'+ class='am-icon-btn am-icon-file-text'></span><br/>"+
                             data[i]['disk_name']+
                            "<br/>已用空间:"+data[i]['free_space']+
                            "<br/>可用空间:"+data[i]['total_space']+"</a></li>";
                    }else if( i == 1){
                        str+="<li><a href='/disk/getDiskById?id='"+data[i]['id'] +
                            "class='am-text-warning'><span class='am-icon-btn am-icon-briefcase'></span><br/>"+
                            data[i]['disk_name']+
                            "<br/>已用空间:"+data[i]['free_space']+
                            "<br/>可用空间:"+data[i]['total_space']+"</a></li>";
                    }else if( i == 2){
                        str+="<li><a href='/disk/getDiskById?id='"+data[i]['id'] +
                            "class='am-text-danger'><span class='am-icon-btn am-icon-recycle'></span><br/>"+
                            data[i]['disk_name']+
                            "<br/>已用空间:"+data[i]['free_space']+
                            "<br/>可用空间:"+data[i]['total_space']+"</a></li>";
                    }else if(i==3){
                        str+="<li><a href='/disk/getDiskById?id='"+data[i]['id'] +
                            "class='am-text-secondary'><span class='am-icon-btn am-icon-user-md'></span><br/>"+
                            data[i]['disk_name']+
                            "<br/>已用空间:"+data[i]['free_space']+
                            "<br/>可用空间:"+data[i]['total_space']+"</a></li>";
                    }

                }
                $(".load-disk").after(str);
            },
            error: function(data){

            }
        })
    });