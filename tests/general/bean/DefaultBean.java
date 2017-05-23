package general.bean;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.service.Dependency;

import java.util.HashMap;
import java.util.Map;

public class DefaultBean {

    public static User getUser() throws BeanException {
        User user = new User();
        Map<String, String> beanFields = new HashMap<String, String>(){
            {
                put("id", "1");
                put("mail", "zhdan@gmail.com");
                put("login", "zhdan");
                put("password", "vzhdan");
                put("phone", "+375445129521");
                put("name", "Vladimir");
                put("last_name", "Zhdan");
                put("type", "USER");
            }
        };
        user.setFields(beanFields);

        return user;
    }

    public static Dependency getDependency() {
        Dependency dependency = new Dependency("orders", "user_id", "1");
        return dependency;
    }
}
