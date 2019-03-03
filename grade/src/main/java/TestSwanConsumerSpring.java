import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.GradeService;

public class TestSwanConsumerSpring {
    @Test
    public void testAdd(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("consumer-beans.xml");
        GradeService bean = ac.getBean(GradeService.class);
        bean.testAdd();
    }

    @Test
    public void testDelete(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("consumer-beans.xml");
        GradeService bean = ac.getBean(GradeService.class);
        bean.testDelete();
    }


    @Test
    public void testQueryStudentById(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("consumer-beans.xml");
        GradeService bean = ac.getBean(GradeService.class);
        bean.testQueryStudentById();
    }

    @Test
    public void testAsync(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("consumer-beans.xml");
        GradeService bean = ac.getBean(GradeService.class);
        bean.testAsync();
    }

    @Test
    public void testAsync2(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("consumer-beans.xml");
        GradeService bean = ac.getBean(GradeService.class);
        bean.testAsync2();
    }
}
