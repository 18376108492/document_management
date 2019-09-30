package com.itdan.test.document;

import com.itdan.document.domain.Disk;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.JsonUtils;
import com.itdan.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JsonTest extends BaseTest {

    @Autowired
    DocumentService documentService;

    @Test
    public void testJsonDemo01() throws Exception{

        //JSON测试
        //Obj TO Json
        Disk disk=new Disk();
        disk=documentService.getDiskById(105838662);
        String s=JsonUtils.objectToJson(disk);
        System.out.println(s);
    }

    @Test
    public void testJsonDemo02() throws Exception{
      //json to list

       List<Disk>  reslut=documentService.getDiskList();
        System.out.println(reslut.toString());
    }


}
