package io.github.minecraftchampions.dodoopenjava.command;

import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import okio.ByteString;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * 命令系统的相关方法
 */
public class Command {
    public static HashSet<CommandExecutor> commands = new HashSet<>();

    public static String Authorization;

    public static boolean initialized = false;

    /**
     * 初始化命令系统
     *
     * @param Class    命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token    机器人Token
     */
    public static void registerCommand(CommandExecutor Class, String clientId, String token) {
        commands.add(Class);
        if (!initialized) {
            EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token));
            initialized = true;
        }
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * 初始化命令系统
     *
     * @param Class    命令处理所在的类
     * @param clientId 机器人唯一标示
     * @param token    机器人Token
     */
    public static void registerCommand(String clientId, String token, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        if (!initialized) {
            EventManage.registerEvents(new CommandTrigger(), BaseUtil.Authorization(clientId, token));
            initialized = true;
        }
        Authorization = BaseUtil.Authorization(clientId, token);
    }

    /**
     * 初始化命令系统
     *
     * @param Class         命令处理所在的类
     * @param authorization authorization
     */
    public static void registerCommand(CommandExecutor Class, String authorization) {
        commands.add(Class);
        if (!initialized) {
            EventManage.registerEvents(new CommandTrigger(), authorization);
            initialized = true;
        }
        Authorization = authorization;
    }

    /**
     * 初始化命令系统
     *
     * @param Class         命令处理所在的类
     * @param authorization authorization
     */
    public static void registerCommand(String authorization, CommandExecutor... Class) {
        commands.addAll(List.of(Class));
        if (!initialized) {
            EventManage.registerEvents(new CommandTrigger(), authorization);
            initialized = true;
        }
        Authorization = authorization;
    }

    /**
     * 触发命令
     *
     * @param sender          发送者
     * @param mainCommandName 命令名
     * @param args            命令参数
     */
    @SuppressWarnings("UnusedReturnValue")
    public static Boolean trigger(CommandSender sender, String mainCommandName, String... args) {
        boolean hasCommand = false;
        for (CommandExecutor command : commands) {
            if (Objects.equals(command.getMainCommand(), ByteString.encodeUtf8((mainCommandName)).utf8())) {
                if (command.getPermission() == null || command.getPermission().isEmpty() || sender.hasPermission(command.getPermission())) {
                    command.onCommand(sender, args);
                    hasCommand = true;
                    break;
                }
            }
        }
        return hasCommand;
    }
}