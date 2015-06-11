LOCAL_PATH := $(call my-dir)

# Build ImageProcessing library

include $(CLEAR_VARS)

LOCAL_MODULE    := NeuralNet
LOCAL_SRC_FILES := NeuralNet.c
LOCAL_LDLIBS +=  -llog -ldl

include $(BUILD_SHARED_LIBRARY)
