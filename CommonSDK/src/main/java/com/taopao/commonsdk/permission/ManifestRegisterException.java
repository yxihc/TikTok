package com.taopao.commonsdk.permission;

/**
 * @Author：淘跑
 * @Date: 2019/1/9 10:48
 * @Use： 动态申请的权限没有在清单文件中注册会抛出的异常
 */
final class ManifestRegisterException extends RuntimeException {

    ManifestRegisterException(String permission) {
        super(permission == null ?
                "No permissions are registered in the manifest file" :
                (permission + ": Permissions are not registered in the manifest file"));
    }
}
