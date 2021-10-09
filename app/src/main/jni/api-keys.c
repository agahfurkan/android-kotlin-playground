#include <jni.h>

//For first API key
JNIEXPORT jstring

JNICALL

Java_com_agah_furkan_androidplayground_ui_login_LoginFragment_getAPIKey(JNIEnv *env,
                                                                        jobject instance) {

    return (*env)->NewStringUTF(env, "some-api-key");

}