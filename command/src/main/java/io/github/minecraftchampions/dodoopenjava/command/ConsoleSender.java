package io.github.minecraftchampions.dodoopenjava.command;

/**
 * 控制台发送者
 */
public class ConsoleSender extends CommandSender {
    /**
     * 回复发送者发送的消息
     *
     * @param message 消息
     */
    @Override
    public void referencedMessage(String message) {
        System.out.println(message);
    }

    /**
     * 判断是否有权限
     *
     * @param permission 权限
     * @return true成功，false失败
     */
    @Override
    public boolean hasPermission(String permission) {
        return true;
    }
}
