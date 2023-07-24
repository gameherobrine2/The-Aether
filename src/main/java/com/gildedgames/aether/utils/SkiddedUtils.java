package com.gildedgames.aether.utils;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/*Small test*/
public class SkiddedUtils
{
    public static File getFile(String fileName) throws IOException
    {
        ClassLoader classLoader = Minecraft.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null)
        {
            throw new IllegalArgumentException("file is not found!");
        }
        else
        {
            return new File(resource.getFile());
        }
    }
}
