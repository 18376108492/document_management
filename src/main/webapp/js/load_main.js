    $(document).ready(function(){
        var str = "";
        $.ajax({
            type: "GET",
            url: "/disk/load_disk",
            dataType: "json",
            async:false,//是否开启异步
            data:{
            },
            success: function(data){
                alert("data信息:"+data);
                for (var i in data)
                {
                    if( i == 0 ){
                        str+="<li><a href='/disk/getDiskByName?diskName=" +data[i]['diskName']+
                            "'class='am-text-success'><span id='refsh'+ class='am-icon-btn am-icon-file-text'></span><br/>"+
                             data[i]['diskName']+
                            "<br/>已用空间:"+data[i]['freeSpace']+
                            "<br/>可用空间:"+data[i]['totalSpace']+"</a></li>";
                    }else if( i == 1){
                        str+="<li><a href='/disk/getDiskByName?diskName="+data[i]['diskName']  +
                            "'class='am-text-warning'><span class='am-icon-btn am-icon-briefcase'></span><br/>"+
                            data[i]['diskName']+
                            "<br/>已用空间:"+data[i]['freeSpace']+
                            "<br/>可用空间:"+data[i]['totalSpace']+"</a></li>";
                    }else if( i == 2){
                        str+="<li><a href='/disk/getDiskByName?diskName="+data[i]['diskName']  +
                            "'class='am-text-danger'><span class='am-icon-btn am-icon-recycle'></span><br/>"+
                            data[i]['diskName']+
                            "<br/>已用空间:"+data[i]['freeSpace']+
                            "<br/>可用空间:"+data[i]['totalSpace']+"</a></li>";
                    }else if(i==3){
                        str+="<li><a href='/disk/getDiskByName?diskName="+data[i]['diskName']  +
                            "'class='am-text-secondary'><span class='am-icon-btn am-icon-user-md'></span><br/>"+
                            data[i]['diskName']+
                            "<br/>已用空间:"+data[i]['freeSpace']+
                            "<br/>可用空间:"+data[i]['totalSpace']+"</a></li>";
                    }

                }
                alert("拼接信息为："+str);
                $("#load-disk").after(str);
            },
            error: function(data){

            }
        })
    });