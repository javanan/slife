package com.slife.editor.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: felixu.
 * @createTime: 2017/11/26.
 */
public class FilterServletOutputStream extends ServletOutputStream {

    private DataOutputStream stream;
    private WriteListener writeListener;

    public FilterServletOutputStream(OutputStream output) {
        stream = new DataOutputStream(output);
    }

    @Override
    public void write(int b) throws IOException {
        stream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        stream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        stream.write(b, off, len);
    }

    public void setWriteListener(WriteListener writeListener) {
        this.writeListener = writeListener;
    }

    public boolean isReady() {
        return true;
    }
}