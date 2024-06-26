package org.demo.oss.storage.enums;

import org.demo.oss.storage.StorageMode;
import org.demo.oss.storage.LocalMode;
import org.demo.oss.storage.MinioMode;
import org.demo.oss.storage.OssMode;
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

}
