//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.photo;

import org.opencv.photo.Tonemap;



public class TonemapDrago extends Tonemap {

    protected TonemapDrago(long addr) { super(addr); }

    // internal usage only
    public static TonemapDrago __fromPtr__(long addr) { return new TonemapDrago(addr); }

    //
    // C++:  float cv::TonemapDrago::getBias()
    //

    public float getBias() {
        return getBias_0(nativeObj);
    }


    //
    // C++:  float cv::TonemapDrago::getSaturation()
    //

    public float getSaturation() {
        return getSaturation_0(nativeObj);
    }


    //
    // C++:  void cv::TonemapDrago::setBias(float bias)
    //

    public void setBias(float bias) {
        setBias_0(nativeObj, bias);
    }


    //
    // C++:  void cv::TonemapDrago::setSaturation(float saturation)
    //

    public void setSaturation(float saturation) {
        setSaturation_0(nativeObj, saturation);
    }


    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }



    // C++:  float cv::TonemapDrago::getBias()
    private static native float getBias_0(long nativeObj);

    // C++:  float cv::TonemapDrago::getSaturation()
    private static native float getSaturation_0(long nativeObj);

    // C++:  void cv::TonemapDrago::setBias(float bias)
    private static native void setBias_0(long nativeObj, float bias);

    // C++:  void cv::TonemapDrago::setSaturation(float saturation)
    private static native void setSaturation_0(long nativeObj, float saturation);

    // native support for java finalize()
    private static native void delete(long nativeObj);

}
