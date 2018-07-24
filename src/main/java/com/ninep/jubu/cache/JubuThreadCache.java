package com.ninep.jubu.cache;

import com.ninep.jubu.domain.User;

import java.util.List;

public class JubuThreadCache {
    private static final ThreadLocal<ThreadContext> cache = new ThreadLocal<ThreadContext>() {
        protected ThreadContext initialValue() {
            return new ThreadContext();
        }
    };

    private static class ThreadContext {
        private User user;
        private List<Integer> roleIds;
        long startTime;
    }

    public static User getUser() {
        return cache.get().user;
    }

    public static void setUser(User user) {
        cache.get().user = user;
    }

    public static List<Integer> getRoles() {
        return cache.get().roleIds;
    }

    public static void setRoles(List<Integer> roles) {
        cache.get().roleIds = roles;
    }

    public static long getStartTime() {
        return cache.get().startTime;
    }

    public static void setStartTime() {
        cache.get().startTime = System.currentTimeMillis();

    }

    public static void clear() {
        cache.set(new ThreadContext());
    }

}