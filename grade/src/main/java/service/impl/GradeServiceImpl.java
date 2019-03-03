package service.impl;

import api.StudentService;
import api.bean.ResultVO;
import api.bean.Student;
import com.alibaba.fastjson.JSON;
import com.roadjava.swan.net.client.callback.AsyncListener;
import com.roadjava.swan.net.ctx.SwanContext;
import com.roadjava.swan.net.spring.anno.SwanConsumer;
import org.springframework.stereotype.Service;
import service.GradeService;

import java.util.Date;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    /*
    timeOut指定接口服务调用的超时时间，默认3000ms
    async：指定是否异步，当使用异步调用特性时，需要修改async=true.
     */
    @SwanConsumer(timeOut = 2000000L,async = true)
    private StudentService studentService;

    @Override
    public void testAdd() {
        Student student=new Student(1000L,"zhao",new Date());
        ResultVO add = studentService.add(student);
        System.out.println("add返回值:"+ JSON.toJSONString(add));

    }

    @Override
    public void testDelete() {
        ResultVO delete = studentService.delete(300L);
        System.out.println("delete返回值:"+JSON.toJSONString(delete));
    }

    @Override
    public void testQueryStudentById() {
        ResultVO<Student> resultVO = studentService.queryStudentById(3L);
        System.out.println("查询一个学生返回值:"+JSON.toJSONString(resultVO));
    }

    /**
     * swan框架支持两种方式来实现rpc的异步调用，方法一如下
     */
    @Override
    public void testAsync() {
      // 异步调用远程方法
        SwanContext.getContext().invokeAsync(studentService,"queryAllStudents"
        ,null,null);
        System.out.println("这里模拟客户端做其他逻辑");
        // 获取异步返回结果
        ResultVO<List<Student>> listResultVO = (ResultVO<List<Student>>) SwanContext.getContext().getResult();
        System.out.println("查询所有学生的结果:"+JSON.toJSONString(listResultVO));
    }

    /**
     * swan框架支持两种方式来实现rpc的异步调用，方法二为通过回调的方式获取异步结果
     */
    @Override
    public void testAsync2() {
        SwanContext.getContext().invokeAsync(studentService, "queryAllStudents"
                , null, null, new AsyncListener() {
                    @Override
                    public void onSuccess(Object result) {
                        ResultVO<List<Student>> listResultVO = (ResultVO<List<Student>>)result;
                        System.out.println("异步回调获取远程方法结果:"+JSON.toJSONString(listResultVO));
                    }

                    @Override
                    public void onException(Exception e) {
                        System.out.println("异步回调出现了异常:");
                        e.printStackTrace();
                    }
                });
    }
}
