package io.github.minecraftchampions.dodoopenjava.card.enums;

import lombok.Getter;

/**
 * 文本类型
 */
@Getter
public enum TextType {
    /**
     * MD文本
     */
    Markdown("dodo-md"),
    /**
     * 普通文本
     */
    PlainText("plain-text");

    TextType(String type) {
        this.type = type;
    }

    /**
     * 行数
     * -- GETTER --
     * 获取类型
     */
    private final String type;

}
