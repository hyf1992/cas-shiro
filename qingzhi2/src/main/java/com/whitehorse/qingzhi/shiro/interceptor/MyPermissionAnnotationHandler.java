package com.whitehorse.qingzhi.shiro.interceptor;

import java.lang.annotation.Annotation;

import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.subject.Subject;

/**
* @author hyf
* @date 2017年4月12日
* @description 
*/
public class MyPermissionAnnotationHandler extends AuthorizingAnnotationHandler{

	
    public MyPermissionAnnotationHandler() {
        super(RequiresPermissions.class);
    }

   
    protected String[] getAnnotationValue(Annotation a) {
        RequiresPermissions rpAnnotation = (RequiresPermissions) a;
        return rpAnnotation.value();
    }

    
    public void assertAuthorized(MethodInvocation mi) throws AuthorizationException {
    	RequiresPermissions methodAnnotation = mi.getMethod().getAnnotation(RequiresPermissions.class);    
    	String[] methodPerms = methodAnnotation.value();
        Subject subject = getSubject();
        //类注解权限参数
        /*RequiresPermissions classAnnotation = mi.getThis().getClass().getAnnotation(RequiresPermissions.class);  
        if(null != classAnnotation) {  
        	String[] classPerms = classAnnotation.value();
        	
        	if (classPerms.length == 1) {
                subject.checkPermission(classPerms[0]);
                
            }
            if (Logical.AND.equals(classAnnotation.logical())) {
                getSubject().checkPermissions(classPerms);
                
            }
            if (Logical.OR.equals(classAnnotation.logical())) {
                // Avoid processing exceptions unnecessarily - "delay" throwing the exception by calling hasRole first
                boolean hasAtLeastOnePermission = false;
                for (String permission : classPerms) if (getSubject().isPermitted(permission)) hasAtLeastOnePermission = true;
                // Cause the exception if none of the role match, note that the exception message will be a bit misleading
                if (!hasAtLeastOnePermission) getSubject().checkPermission(classPerms[0]);
                
            }
        }*/
        if (methodPerms.length == 1) {
            subject.checkPermission(methodPerms[0]);
            return;
        }
        if (Logical.AND.equals(methodAnnotation.logical())) {
            getSubject().checkPermissions(methodPerms);
            return;
        }
        if (Logical.OR.equals(methodAnnotation.logical())) {
            // Avoid processing exceptions unnecessarily - "delay" throwing the exception by calling hasRole first
            boolean hasAtLeastOnePermission = false;
            for (String permission : methodPerms) if (getSubject().isPermitted(permission)) hasAtLeastOnePermission = true;
            // Cause the exception if none of the role match, note that the exception message will be a bit misleading
            if (!hasAtLeastOnePermission) getSubject().checkPermission(methodPerms[0]);
            
        }
    }


    public void assertAuthorized(Annotation a) throws AuthorizationException {
        if (!(a instanceof RequiresPermissions)) return;

        RequiresPermissions rpAnnotation = (RequiresPermissions) a;
        String[] perms = getAnnotationValue(a);
        Subject subject = getSubject();

        if (perms.length == 1) {
            subject.checkPermission(perms[0]);
            return;
        }
        if (Logical.AND.equals(rpAnnotation.logical())) {
            getSubject().checkPermissions(perms);
            return;
        }
        if (Logical.OR.equals(rpAnnotation.logical())) {
            // Avoid processing exceptions unnecessarily - "delay" throwing the exception by calling hasRole first
            boolean hasAtLeastOnePermission = false;
            for (String permission : perms) if (getSubject().isPermitted(permission)) hasAtLeastOnePermission = true;
            // Cause the exception if none of the role match, note that the exception message will be a bit misleading
            if (!hasAtLeastOnePermission) getSubject().checkPermission(perms[0]);
            
        }
    }
}
