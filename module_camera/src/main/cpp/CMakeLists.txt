# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.22.1)

# Declares and names the project.

project("camera")

include_directories(
        utils
        jni
        core
        entity
        camera
        filter
        include
        egl
        video
)

file(GLOB src-files

        VideoRecordController.cpp
        VideoRecordJni.cpp

        ${CMAKE_SOURCE_DIR}/*.cpp
        ${CMAKE_SOURCE_DIR}/*/*.cpp
        ${CMAKE_SOURCE_DIR}/*/*/*.cpp
        ${CMAKE_SOURCE_DIR}/*/*/*/*.cpp
        ${CMAKE_SOURCE_DIR}/*/*/*/*/*.cpp
        )

add_library( # Sets the name of the library.
        camera

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${src-files})


find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        camera

        EGL
        GLESv3

        android
        jnigraphics
        ${log-lib})