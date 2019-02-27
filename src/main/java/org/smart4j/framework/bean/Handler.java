package org.smart4j.framework.bean;

import jdk.internal.org.objectweb.asm.Handle;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * Created by lemoon on 19/2/16 下午9:58
 * @since 1.0.0
 */
public class Handler {

    /**
     * Controller类
     */
    private Class<?> controllerClass;

    /**
     * Action方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
