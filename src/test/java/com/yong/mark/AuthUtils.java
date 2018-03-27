package com.yong.mark;

import lombok.NoArgsConstructor;

/**
 * @acthor yong.a.liang
 * @date 2018/01/03
 */
@NoArgsConstructor
public final class AuthUtils {
    public static final AuthContext AUTH_CONTEXT = new AuthContext();

    public static String getName() {
        return AUTH_CONTEXT.get().getName();
    }

    public static String getRoles(){
        return AUTH_CONTEXT.get().getRoles();
    }

    public static void remove() {
        AUTH_CONTEXT.remove();
    }

    public static void setUser(User user){
        AUTH_CONTEXT.set(user);
    }

    static class AuthContext extends InheritableThreadLocal<User> {
        @Override
        protected User initialValue() {
            return new User();
        }
    }
}
