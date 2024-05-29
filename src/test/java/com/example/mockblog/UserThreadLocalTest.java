package com.example.mockblog;

import com.example.mockblog.pojo.SysUser;
import com.example.mockblog.utils.MockUtils;
import com.example.mockblog.utils.UserThreadLocal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class UserThreadLocalTest {
    @Test
    public void testUserThreadLocal(){
        SysUser user = MockUtils.mockSysUser();
        UserThreadLocal.put(user);
        SysUser getUser = UserThreadLocal.get();
        Assertions.assertEquals(user, getUser);
        UserThreadLocal.remove();
        Assertions.assertNull(UserThreadLocal.get());
    }
}
