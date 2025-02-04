package io.github.minecraftchampions.dodoopenjava.card.enums;

import lombok.Getter;

/**
 * 输入框高度
 */
@Getter
public enum Rows {
    /**
     * 1
     */
    one(1),
    /**
     * 2
     */
    two(2),
    /**
     * 3
     */
    three(3),
    /**
     * 4
     */
    four(4);

    Rows(int row) {
        this.row = row;
    }

    /**
     * 行数
     * -- GETTER --
     * 获取行数
     */
    private final int row;

}
