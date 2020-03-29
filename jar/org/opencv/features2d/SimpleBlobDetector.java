//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.features2d;

import org.opencv.features2d.Feature2D;
import org.opencv.features2d.SimpleBlobDetector;



public class SimpleBlobDetector extends Feature2D {

    protected SimpleBlobDetector(long addr) { super(addr); }

    // internal usage only
    public static SimpleBlobDetector __fromPtr__(long addr) { return new SimpleBlobDetector(addr); }

    //
    // C++: static Ptr_SimpleBlobDetector cv::SimpleBlobDetector::create(SimpleBlobDetector_Params parameters = SimpleBlobDetector::Params())
    //

    public static SimpleBlobDetector create() {
        return SimpleBlobDetector.__fromPtr__(create_0());
    }


    //
    // C++:  String cv::SimpleBlobDetector::getDefaultName()
    //

    public String getDefaultName() {
        return getDefaultName_0(nativeObj);
    }


    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }



    // C++: static Ptr_SimpleBlobDetector cv::SimpleBlobDetector::create(SimpleBlobDetector_Params parameters = SimpleBlobDetector::Params())
    private static native long create_0();

    // C++:  String cv::SimpleBlobDetector::getDefaultName()
    private static native String getDefaultName_0(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

}
