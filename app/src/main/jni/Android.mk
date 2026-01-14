LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := api-keys
LOCAL_SRC_FILES := api-keys.c

# Add 16KB alignment flag for compatibility with 16KB page size devices
LOCAL_LDFLAGS := -Wl,-z,max-page-size=16384

include $(BUILD_SHARED_LIBRARY)