package io.github.minecraftchampions.dodoopenjava.configuration.file;

import io.github.minecraftchampions.dodoopenjava.configuration.Configuration;
import io.github.minecraftchampions.dodoopenjava.configuration.InvalidConfigurationException;
import io.github.minecraftchampions.dodoopenjava.configuration.MemoryConfiguration;
import io.github.minecraftchampions.dodoopenjava.utils.ConfigUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 这是一个实现了 Configuration 的配置文件的基类
 */
public abstract class FileConfiguration extends MemoryConfiguration {
    /**
     * 创建一个空的，没有值默认值的 FileConfiguration.
     */
    public FileConfiguration() {
        super();
    }

    /**
     * 创建一个空的 FileConfiguration 并且使用 Configuration 内的所有默认值创建它.
     *
     * @param defaults 为其创建提供缺省值的Configuration.
     */
    public FileConfiguration(Configuration defaults) {
        super(defaults);
    }

    /**
     * 以一个 FileConfiguration 调用该方法，将文件储存到指定位置.
     * 如果你指定储存的这个文件不存在,这个方法会帮你自动创建一个. 如果这个文件存在,那么该方法会把所有未保存的更改直接写入文件 并且直接覆盖原文件. 如果储存或者创建失败,将会抛出一个异常
     * <p>
     * 本方法会用系统默认的编码储存,不过也有可能用UTF-8出储存
     *
     * @param file 要储存的文件
     * @throws IOException              然后会给出无法创建或者保存的原因.
     * @throws IllegalArgumentException 如果文件为空，抛出该异常
     */
    public void save(File file) throws IOException {
        ConfigUtil.saveToFile(saveToString(), file);
    }

    /**
     * 以一个 FileConfiguration 调用该方法，将文件储存到指定位置.
     * 如果你指定储存的这个文件不存在,这个方法会帮你自动创建一个. 如果这个文件存在,那么该方法会把所有未保存的更改直接写入文件 并且直接覆盖原文件. 如果储存或者创建失败,将会抛出一个异常
     * <p>
     * 本方法会用系统默认的编码储存,不过也有可能用UTF-8出储存
     *
     * @param file 要储存的文件
     * @throws IOException              然后会给出无法创建或者保存的原因.
     * @throws IllegalArgumentException 如果文件为空，抛出该异常
     */
    public void save(String file) throws IOException {
        save(new File(file));
    }

    /**
     * 将这个 FileConfiguration 转化为String对象并且返回t.
     *
     * @return 这个FileConfiguration包含的所有String
     */
    public abstract String saveToString();

    /**
     * 从指定位置加载 FileConfiguration
     *
     * @param file 文件
     * @throws FileNotFoundException         无法打开给定文件时抛出
     * @throws IOException                   无法读取给定文件时抛出
     * @throws InvalidConfigurationException 当给定文件不是 Configuration 时抛出
     * @throws IllegalArgumentException      文件为空时抛出
     */
    public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        loadFromString(ConfigUtil.readFile(file));
    }

    /**
     * 从指定的读取器加载此文件配置。
     * <p>
     * 将删除此配置中包含的所有值，只保留设置和默认值，并且将从给定流加载新值。
     */
    @Deprecated
    public void load(InputStream stream) throws IOException, InvalidConfigurationException {
        load(new InputStreamReader(stream, StandardCharsets.UTF_8));
    }

    /**
     * 从指定的Reader加载此文件配置。
     * <p>
     * 将删除此配置中包含的所有值，只保留设置和默认值，并且将从给定流加载新值。
     *
     * @param reader Reader
     * @throws IOException                   当Reader引发IOException时引发
     * @throws InvalidConfigurationException 当Reader不是 Configuration 时引发
     * @throws IllegalArgumentException      Reader为空时引发
     */
    public void load(Reader reader) throws IOException, InvalidConfigurationException {
        BufferedReader input = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            input.close();
        }

        loadFromString(builder.toString());
    }

    /**
     * 从指定位置加载此文件配置。
     * <p>
     * 将删除此配置中包含的所有值，只保留设置和默认值，并且将从给定文件加载新值。
     * <p>
     * <p>
     * <p>
     * 如果由于任何原因无法加载文件，将引发异常。
     *
     * @param file 要从中加载的文件。
     * @throws FileNotFoundException         无法打开给定文件时引发。
     * @throws IOException                   无法读取给定文件时引发。
     * @throws InvalidConfigurationException 当给定文件不是 Configuration 时引发
     * @throws IllegalArgumentException      Thrown when file is null.
     */
    public void load(String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        load(new File(file));
    }

    /**
     * 从指定的字符串加载此文件配置，而不是从文件加载。
     * <p>
     * 将删除此配置中包含的所有值，只保留设置和默认值，并且将从给定字符串加载新值。
     * <p>
     * 如果字符串以任何方式无效，将引发异常。
     *
     * @param contents 字符串
     * @throws InvalidConfigurationException 如果指定的字符串无效，则抛出
     * @throws IllegalArgumentException      字符串为null时抛出
     */
    public abstract void loadFromString(String contents) throws InvalidConfigurationException;

    /**
     * 编译此 FileConfiguration 的 header 并返回结果
     *
     * @return Compiled header
     */
    protected abstract String buildHeader();

    @Override
    public FileConfigurationOptions options() {
        if (options == null) {
            options = new FileConfigurationOptions(this);
        }

        return (FileConfigurationOptions) options;
    }
}