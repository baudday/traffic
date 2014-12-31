package com.willem.tools;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.willem.tHelpers.AssetLoader;

/**
 * Created by wellis on 12/23/2014.
 */
public class MyPacker {
    public static void main(String[] args) throws Exception {
        TexturePacker.process(AssetLoader.prefix + "ui", AssetLoader.prefix + "ui", "uiskin");
    }
}
