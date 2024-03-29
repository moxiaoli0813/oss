package org.demo.oss.config.enums;

import org.demo.oss.storage.StorageMode;
import org.demo.oss.storage.impl.LocalMode;
import org.demo.oss.storage.impl.MinioMode;
import org.demo.oss.storage.impl.OssMode;
import org.demo.oss.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 存储类型枚举类
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum StorageType {

//    local("local"),
//    minio("minio"),
//    oss("oss");

    local("local", LocalMode.class),
    minio("minio", MinioMode.class),
    aliyun("oss", OssMode.class);

    private final String type;

    private final Class<? extends StorageMode> storageMode;

    /**
     * 获取存储方式
     * @param type 存储方式
     * @return {@link StorageMode} 存储方式
     */
    public static StorageMode getStorageMode(String type){
        if(StringUtils.isBlank(type)){
            throw new RuntimeException("存储方式不能为空");
        }
        try {
            // 遍历枚举类
            for (StorageType storageType : StorageType.values()) {
                if (storageType.getType().equals(type)){
                    return storageType.getStorageMode().newInstance();
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("获取存储方式失败：{}",e.getMessage());
        }
        throw new RuntimeException("获取存储方式失败");
    }

//    /**
//     * 获取存储方式
//     * @param type 存储方式
//     * @return {@link StorageMode} 存储方式
//     */
//    public static StorageMode getStorageMode(String type){
//        if(StringUtils.isBlank(type)){
//            throw new RuntimeException("存储方式不能为空");
//        }
//        String storage = StorageType.valueOf(type).getType();
//        if (StringUtils.isBlank(storage)){
//            throw new RuntimeException("存储方式不存在");
//        }
//        // 将存储方式的首字母转换成大写
//        String mode = storage.substring(0,1).toUpperCase() + storage.substring(1);
//        try {
//            return (StorageMode) Class.forName("org.demo.oss.storage.impl."+ mode +"Mode").newInstance();
//        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//            log.error("获取存储方式失败：{}",e.getMessage());
//        }
//        throw new RuntimeException("获取存储方式失败");
//    }
}
