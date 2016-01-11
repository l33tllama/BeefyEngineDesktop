package com.beefyolegames.beefyengine.framework;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;

    public InputStream readFile(String fileName) throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;

    public FileReader readAssetFile(String fileName) throws IOException;
}
