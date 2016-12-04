package com.maelook.bluetooth;
import java.util.HashMap;

/**
 * This class includes 标准GATT属性
 */
public class BluetoothGattAttributes {
    private static HashMap<String, String> attributes = new HashMap();
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    static {
        // 示例服务
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "频率服务");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "设备信息服务");
        // 示例特性
        attributes.put(HEART_RATE_MEASUREMENT, "频率测量");
        attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "制造商名称字符串");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
