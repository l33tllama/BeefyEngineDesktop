package com.beefyolegames.beefyengine.impl;

import com.beefyolegames.beefyengine.framework.FileIO;

import java.io.*;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public class DesktopFileIO implements FileIO{
    String assetsPath;

    public DesktopFileIO() {
        this.assetsPath = "res" + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return new FileInputStream(assetsPath + fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(fileName);
    }

    @Override
    public FileReader readAssetFile(String fileName) {
        try {
            return new FileReader(assetsPath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
