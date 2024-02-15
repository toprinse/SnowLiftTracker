# Build & install directories
BUILD_DIR=build
BUILD_BIN_DIR=${BUILD_DIR}/bin
BUILD_LIB_DIR=${BUILD_DIR}/lib

# Project files & dependencies
LIB_DIR=lib/
SRC_DIR=src/
APP_DIR=app/

# Compiler, archiver & linker options
COMPILER=${CXX}
COMPILER_FLAGS= -O2 -Wall  -std=c++11 
LD_FLAGS= #-L${LIB_INSTALL_DIR} -l${LIB_NAME} #-libzmq -libczmq
LIB_INCLUDES=-I${LIB_DIR}

# Executable
APP_NAME := formattermain
APP_EXEC := ${BUILD_BIN_DIR}/${APP_NAME}
APP_LD_FLAGS= -L${BUILD_LIB_DIR}/ -l${LIB_NAME}

# Shared library
LIB_NAME := sltformatter
LIB_FILE := ${BUILD_LIB_DIR}/lib${LIB_NAME}.so

all: dir shared_library main_exec

# This line uses the find command to locate all `.cpp` files in the `$(SRC_DIR)` directory and its subdirectories.
# The result is assigned to the variable `SRC_LIST`, which is a list of source files for the library.
SRC_LIST=$(shell find ${SRC_DIR} -type f -name *.cpp)

# Here, the patsubst function is used to replace the `${SRC_DIR}/` prefix with `${BUILD_OBJ_DIR}/` and the `.cpp` extension with `.o`. 
# This creates a list of object files with the new path and extension.
# These object files will be used to build the shared library.
SHARED_LIB_OBJS=$(patsubst ${SRC_DIR}/%.cpp,${BUILD_OBJ_DIR}/%.o,${SRC_LIST})

# This foreach loop iterates over each object file in $(SHARED_LIB_OBJS).
# For each object file, it uses the eval function to dynamically generate a rule with a prerequisite on the directory where the object file should be located. 
# This is achieved using the | $(dir $(OBJECT)) syntax.
# The | symbol in a rule specifies order-only prerequisites, which means that the listed directories are created if they do not exist, but their modification times are not checked for determining whether the target needs to be rebuilt.
$(foreach OBJECT,$(SHARED_LIB_OBJS),$(eval $(OBJECT): | $(dir $(OBJECT))))


# rule to build shared library
shared_library: ${LIB_FILE}

# The line ${LIB_FILE}: ${SHARED_LIB_OBJS} | $(dir ${LIB_FILE}) states that the shared library depends on its object files (${SHARED_LIB_OBJS}).
# The | symbol is used to specify that the directory where the shared library resides ($(dir ${LIB_FILE})) is an order-only prerequisite.
# This ensures that the directory is created if it doesn't exist, but the modification time of the directory is not considered for rebuilding.
# -o $@ specifies the output file, which is the shared library being created.
# $^ refers to all the prerequisites (object files)
${LIB_FILE}: ${SHARED_LIB_OBJS} | $(dir ${LIB_FILE})
	${COMPILER} ${COMPILER_FLAGS} -shared -fPIC $(LIB_INCLUDES) ${LD_FLAGS} $^ -o $@ 

# This part of the code specifies a pattern rule for building each object file (${SHARED_LIB_OBJS}). 
# The rule is applied to each source file (${SRC_DIR}/%.cpp) in the ${SRC_DIR} directory.
# -o $@ specifies the output file, which is the object file being created.
# -c $< indicates that only compilation (not linking) should be performed, and $< represents the source file.
$(SHARED_LIB_OBJS): ${BUILD_OBJ_DIR}/%.o: ${SRC_DIR}/%.cpp 
	${COMPILER} ${COMPILER_FLAGS} -fPIC $(LIB_INCLUDES) -o $@ -c $<

main_exec: ${APP_EXEC}

${APP_EXEC}: ${APP_DIR}/%.cpp: | $(dir ${APP_EXEC})
	${COMPILER} ${COMPILER_FLAGS} $(LIB_INCLUDES) ${LD_FLAGS} ${APP_DIR}main.cpp -o $@ ${APP_LD_FLAGS}

# Rule to create directories
dir:
	mkdir -p ${BUILD_BIN_DIR} ${BUILD_LIB_DIR} ${BUILD_OBJ_DIR}

# Rule to clean 
clean:
	rm -rf ${BUILD_DIR}

.PHONY: all dir shared_library main_exec clean
